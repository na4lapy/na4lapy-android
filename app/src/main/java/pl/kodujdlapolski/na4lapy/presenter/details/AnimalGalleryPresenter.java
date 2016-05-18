package pl.kodujdlapolski.na4lapy.presenter.details;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Photo;
import pl.kodujdlapolski.na4lapy.ui.details.AnimalGalleryActivity;
import pl.kodujdlapolski.na4lapy.ui.details.AnimalGallerySectionsPagerAdapter;

/**
 * Created by Malgorzata Syska on 2016-03-30.
 */
public class AnimalGalleryPresenter {

    private AnimalGalleryActivity activity;
    private AnimalGallerySectionsPagerAdapter animalGallerySectionsPagerAdapter;

    public AnimalGalleryPresenter (AnimalGalleryActivity activity, List<Photo> gallery) {
        this.activity = activity;
        animalGallerySectionsPagerAdapter = new AnimalGallerySectionsPagerAdapter(
                this.activity.getSupportFragmentManager(), gallery, this);
    }

    public AnimalGallerySectionsPagerAdapter getAdapter() {
        return animalGallerySectionsPagerAdapter;
    }

}
