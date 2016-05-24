package pl.kodujdlapolski.na4lapy.ui.details;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Photo;
import pl.kodujdlapolski.na4lapy.presenter.details.AnimalGalleryPresenter;

/**
 * Created by Malgorzata Syska on 2016-03-30.
 */
public class AnimalGallerySectionsPagerAdapter extends FragmentPagerAdapter {

    private List<Photo> gallery;
    private AnimalGalleryPresenter presenter;
    private GalleryViewPager galleryViewPager;

    public AnimalGallerySectionsPagerAdapter(FragmentManager fm, List<Photo> gallery,
                                             AnimalGalleryPresenter presenter,
                                             GalleryViewPager galleryViewPager) {
        super(fm);
        this.gallery = gallery;
        this.presenter = presenter;
        this.galleryViewPager = galleryViewPager;
    }

    @Override
    public Fragment getItem(int position) {
        return AnimalGalleryPlaceholderFragment.newInstance(
                gallery.get(position), this.galleryViewPager, position, this.getCount());
    }

    @Override
    public int getCount() {
        return gallery.size();
    }
}
