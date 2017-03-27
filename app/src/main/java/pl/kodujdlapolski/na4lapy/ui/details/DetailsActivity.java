/*
 *	Copyright 2017 Stowarzyszenie Na4Łapy
 *
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *	http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */

package pl.kodujdlapolski.na4lapy.ui.details;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Years;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.ColorFilterTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pl.kodujdlapolski.na4lapy.Na4LapyApp;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.service.repository.RepositoryService;
import pl.kodujdlapolski.na4lapy.service.system.SystemService;
import pl.kodujdlapolski.na4lapy.service.user.UserService;
import pl.kodujdlapolski.na4lapy.ui.CommonUI;
import pl.kodujdlapolski.na4lapy.ui.compliance_level.ComplianceLevelDialog;
import pl.kodujdlapolski.na4lapy.utils.AnimalUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//TODO utworzyć presentera dla widoku
public class DetailsActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ANIMAL = 1;
    public static final String EXTRA_ANIMAL_ID = "extraAnimalId";

    @Inject
    RepositoryService repositoryService;
    @Inject
    UserService userService;
    @Inject
    SystemService systemService;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.background_picture)
    ImageView background;
    @BindView(R.id.profile_pic_on_details)
    ImageView profilePic;
    @BindView(R.id.matching_lvl_image)
    ImageView matchingLvl;
    @BindView(R.id.add_to_fav_fab)
    FloatingActionButton addToFavFab;
    @BindView(R.id.details_container)
    NestedScrollView detailsContainer;

    private Long id;
    private Animal animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        ((Na4LapyApp) getApplication()).getComponent().inject(this);
        if (isIdAvailable(savedInstanceState)) {
            getAnimal();
        } else {
            // as we cannot do anything without an animal
            finish();
        }
    }

    private boolean isIdAvailable(Bundle savedInstanceState) {
        if (getIntent() != null && id == null) {
            id = getIntent().getExtras().getLong(EXTRA_ANIMAL_ID);
        }
        if (id == null && savedInstanceState != null) {
            id = savedInstanceState.getLong(EXTRA_ANIMAL_ID);
        }
        return id != null;
    }

    private void getAnimal() {
        repositoryService.getAnimal(id).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onAnimalAvailable,  throwable -> CommonUI.onServerError(throwable, this));
    }

    private void onAnimalAvailable(Animal downloadedAnimal) {
        animal = downloadedAnimal;
        initContent();

    }

    private void initContent() {
        initToolbar();
        initToolbarImages();
        ContentDetailsView contentDetailsView = new ContentDetailsView(this, animal);
        detailsContainer.addView(contentDetailsView.getView());
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            String title = animal.getBirthDate() != null ?
                    getString(R.string.animal_details_title, animal.getName(), getAgeTextShort(this, animal.getBirthDate())) :
                    animal.getName();
            getSupportActionBar().setTitle(title);
        }
    }

    private void initToolbarImages() {
        String profilePicUrl = animal.getProfilePicUrl();
        if (profilePicUrl != null) {
            Picasso.with(this)
                    .load(profilePicUrl)

                    .transform(new BlurTransformation(this, 2))
                    .transform(new ColorFilterTransformation(ContextCompat.getColor(this, R.color.colorPrimaryDark50opacity)))
                    .into(background);

            Picasso.with(this)
                    .load(profilePicUrl)

                    .transform(new CropCircleTransformation())
                    .into(profilePic);
        }
        matchingLvl.setImageLevel(userService.getPreferencesComplianceLevel(animal));
        matchingLvl.setOnClickListener(v -> {
            onComplianceLevelClick();
        });
        animal.setFavourite(userService.isFavourite(animal));
        addToFavFab.setImageResource(AnimalUtils.getAddToFavFabImage(animal));
        addToFavFab.setOnClickListener(v -> {
            if (Boolean.TRUE.equals(animal.getFavourite())) {
                userService.removeFromFavourite(animal);
            } else {
                userService.addToFavourite(animal);
            }
            updateAnimal();
            addToFavFab.setImageResource(AnimalUtils.getAddToFavFabImage(animal));
        });
    }

    private void onComplianceLevelClick() {
        if(userService.isComplianceLevelAvailable())
            ComplianceLevelDialog.showComplianceLevelInfoDialog(this);
        else
            ComplianceLevelDialog.showNoComplianceLevelYetDialog(this);
    }

    private void updateAnimal() {
        animal.setFavourite(userService.isFavourite(animal));
        Intent returnIntent = new Intent();
        returnIntent.putExtra(EXTRA_ANIMAL_ID, id);
        setResult(Activity.RESULT_OK, returnIntent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(EXTRA_ANIMAL_ID, id);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        MenuItem shareMenuItem = menu.findItem(R.id.menu_item_share);
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareMenuItem);
        shareActionProvider.setShareIntent(systemService.getShareIntent(animal));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //TODO przenieść do presentera kiedy powstawnie
    public static String getAgeTextShort(Context context, @NonNull LocalDate date) {
        int age = Years.yearsBetween(date, LocalDate.now()).getYears();
        int fromStringRes = R.plurals.years_short;
        if (age == 0) {
            age = Months.monthsBetween(date, LocalDate.now()).getMonths();
            fromStringRes = R.plurals.months_short;
        }
        return context.getResources().getQuantityString(fromStringRes, age, age);
    }
}
