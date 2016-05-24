package pl.kodujdlapolski.na4lapy.presenter.details;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Photo;
import pl.kodujdlapolski.na4lapy.ui.details.AnimalGalleryActivity;
import pl.kodujdlapolski.na4lapy.ui.details.AnimalGallerySectionsPagerAdapter;
import pl.kodujdlapolski.na4lapy.ui.details.GalleryViewPager;

/**
 * Created by Malgorzata Syska on 2016-03-30.
 */
public class AnimalGalleryPresenter {

    private AnimalGalleryActivity activity;
    private AnimalGallerySectionsPagerAdapter animalGallerySectionsPagerAdapter;
    private GalleryViewPager galleryViewPager;

    public AnimalGalleryPresenter (
            AnimalGalleryActivity activity,
            List<Photo> gallery,
            GalleryViewPager galleryViewPager) {
        this.activity = activity;
        this.galleryViewPager = galleryViewPager;
        animalGallerySectionsPagerAdapter = new AnimalGallerySectionsPagerAdapter(
                this.activity.getSupportFragmentManager(), gallery, this, galleryViewPager);
    }

    public AnimalGallerySectionsPagerAdapter getAdapter() {
        return animalGallerySectionsPagerAdapter;
    }

}
