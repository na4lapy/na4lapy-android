package pl.kodujdlapolski.na4lapy.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.ColorFilterTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.AnimalUtils;

public class DetailsActivity extends AppCompatActivity {

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
    @Bind(R.id.description)
    TextView description;
    @Bind(R.id.animal_size_image)
    ImageView sizeImage;
    @Bind(R.id.animal_gender_image)
    ImageView genderImage;
    @Bind(R.id.animal_activity_image)
    ImageView activityImage;
    @Bind(R.id.images_container)
    LinearLayout imagesContainer;

    @Bind(R.id.info_activity)
    TextView infoActivity;
    @Bind(R.id.info_admittance_date)
    TextView infoAdmittanceDate;
    @Bind(R.id.info_attitude_towards_cats)
    TextView infoAttitudeTowardsCats;
    @Bind(R.id.info_attitude_towards_children)
    TextView infoAttitudeTowardsChildren;
    @Bind(R.id.info_attitude_towards_dogs)
    TextView infoAttitudeTowardsDogs;
    @Bind(R.id.info_attitude_towards_people)
    TextView infoAttitudeTowardsPeople;
    @Bind(R.id.info_chip)
    TextView infoChip;
    @Bind(R.id.info_race)
    TextView infoRace;
    @Bind(R.id.info_size)
    TextView infoSize;
    @Bind(R.id.info_gender)
    TextView infoGender;
    @Bind(R.id.info_training)
    TextView infoTraining;
    @Bind(R.id.info_sterilization)
    TextView infoSterilization;
    @Bind(R.id.info_vaccination)
    TextView infoVaccination;

    public static final String EXTRA_ANIMAL = "extraAnimal";
    private Animal animal;

    String[] picturesSample = new String[]{"http://schroniskopromyk.pl/wp-content/uploads/2016/03/Bodek-1-150x150.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/03/Mundi-1-150x150.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/03/Bobo-1-150x150.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/03/Fado-5-150x150.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/02/Morus-5-150x150.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        if (isAnimalAvailable(savedInstanceState)) {
            initToolbar();
            initToolbarImages();
            initImagesContainer();
            initBasicInfoImagesAndDescription();
            initMoreInfoTable();
        } else {
            showError();
        }
    }

    private void initMoreInfoTable() {
        java.util.Date date = new java.util.Date(animal.getAdmittanceDate());
        infoActivity.setText(getString(animal.getActivity().resId));
        infoAdmittanceDate.setText(android.text.format.DateFormat.format("dd.MM.yyyy", date));
        infoAttitudeTowardsCats.setText(getString(animal.getAttitudeTowardsCats().resId));
        infoAttitudeTowardsChildren.setText(getString(animal.getAttitudeTowardsChildren().resId));
        infoAttitudeTowardsDogs.setText(getString(animal.getAttitudeTowardsDogs().resId));
        infoAttitudeTowardsPeople.setText(getString(animal.getAttitudeTowardsPeople().resId));
        infoChip.setText(animal.getChip() ? getString(R.string.yes) : getString(R.string.no));
        infoRace.setText(animal.getRace());
        infoSize.setText(getString(animal.getSize().resId));
        infoGender.setText(getString(animal.getGender().resId));
        infoTraining.setText(getString(animal.getTraining().resId));
        infoSterilization.setText(animal.getSterilization() ? getString(R.string.yes) : getString(R.string.no));
        infoVaccination.setText(animal.getVaccination() ? getString(R.string.yes) : getString(R.string.no));
    }

    private void initBasicInfoImagesAndDescription() {
        description.setText(animal.getDescription());
        sizeImage.setImageResource(AnimalUtils.getSizeImage(animal));
        activityImage.setImageResource(AnimalUtils.getActivityImage(animal));
        genderImage.setImageResource(AnimalUtils.getGenderImage(animal));
    }

    private void initImagesContainer() {
// todo implementation

    }

    private boolean isAnimalAvailable(Bundle savedInstanceState) {
        if (getIntent() != null && animal == null) {
            if (getIntent().getExtras().getSerializable(EXTRA_ANIMAL) instanceof Animal) {
                animal = (Animal) getIntent().getExtras().getSerializable(EXTRA_ANIMAL);
            }
        }
        if (animal == null && savedInstanceState != null && savedInstanceState.getSerializable(EXTRA_ANIMAL) instanceof Animal) {
            animal = (Animal) savedInstanceState.getSerializable(EXTRA_ANIMAL);
        }
        return animal != null;
    }

    private void showError() {
        // todo implementation
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(animal.getName() + AnimalUtils.getAnimalAgeFormatted(this, animal));
        }
    }

    private void initToolbarImages() {
        String url = picturesSample[new Random().nextInt(picturesSample.length)]; // todo change na animal profile pic
        Picasso.with(this)
                .load(url)
                .transform(new BlurTransformation(this, 2))
                .transform(new ColorFilterTransformation(ContextCompat.getColor(this, R.color.colorPrimaryDark50opacity)))
                .into(background);

        Picasso.with(this)
                .load(url)
                .transform(new CropCircleTransformation())
                .into(profilePic);

        matchingLvl.setImageResource(AnimalUtils.getMatchingLvlImage(animal));
        addToFavFab.setImageResource(AnimalUtils.getAddToFavFabImage(animal));
        addToFavFab.setOnClickListener(v -> {
            // todo     repositoryService.setFavourite(animal.getId(), !animal.getFavourite()).subscribe(this::onFavChanged);
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(EXTRA_ANIMAL, animal);
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
}
