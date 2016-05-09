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

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.ColorFilterTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pl.kodujdlapolski.na4lapy.Na4LapyApp;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.repository.RepositoryService;
import pl.kodujdlapolski.na4lapy.user.UserService;
import pl.kodujdlapolski.na4lapy.utils.AnimalUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//TODO utworzyć presentera dla widoku
public class DetailsActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ANIMAL = 1;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.background_picture)
    ImageView background;
    @Bind(R.id.profile_pic_on_details)
    ImageView profilePic;
    @Bind(R.id.matching_lvl_image)
    ImageView matchingLvl;
    @Bind(R.id.add_to_fav_fab)
    FloatingActionButton addToFavFab;
    @Bind(R.id.details_container)
    NestedScrollView detailsContainer;

    public static final String EXTRA_ANIMAL_ID = "extraAnimalId";
    private Long id;
    private Animal animal;

    @Inject
    RepositoryService repositoryService;
    @Inject
    UserService userService;

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
                .subscribe(this::onAnimalAvailable);
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
        if (animal.getPhotos() != null && animal.getPhotos().iterator().hasNext()) {
            String url = animal.getPhotos().iterator().next().getUrl();
            Picasso.with(this)
                    .load(url)
                    .transform(new BlurTransformation(this, 2))
                    .transform(new ColorFilterTransformation(ContextCompat.getColor(this, R.color.colorPrimaryDark50opacity)))
                    .into(background);

            Picasso.with(this)
                    .load(url)
                    .transform(new CropCircleTransformation())
                    .into(profilePic);
        }
        matchingLvl.setImageLevel(userService.getPreferencesComplianceLevel(animal));
        addToFavFab.setImageResource(AnimalUtils.getAddToFavFabImage(animal));
        addToFavFab.setOnClickListener(v -> repositoryService.setFavourite(animal.getId(), !Boolean.TRUE.equals(animal.getFavourite()))
                .subscribe(this::onFavChanged));
    }

    private void onFavChanged(Long changedAnimalId) {
        repositoryService.getAnimal(changedAnimalId)
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onChangedAnimalAvailable);
    }

    private void onChangedAnimalAvailable(Animal changedAnimal) {
        animal = changedAnimal;
        Intent returnIntent = new Intent();
        returnIntent.putExtra(EXTRA_ANIMAL_ID, id);
        setResult(Activity.RESULT_OK, returnIntent);
        addToFavFab.setImageResource(AnimalUtils.getAddToFavFabImage(animal));
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
        Intent shareIntent = AnimalUtils.getShareIntent(animal);
        shareActionProvider.setShareIntent(shareIntent);
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
