package pl.kodujdlapolski.na4lapy.ui.details;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Photo;
import pl.kodujdlapolski.na4lapy.presenter.details.AnimalGalleryPresenter;
import pl.kodujdlapolski.na4lapy.ui.AbstractSingleActivity;

/**
 * Created by Malgorzata Syska on 2016-03-30.
 */
public class AnimalGallerySectionsPagerAdapter extends FragmentPagerAdapter {

    private List<Photo> gallery;
    private AnimalGalleryPresenter presenter;
    private AbstractSingleActivity activity;

    public AnimalGallerySectionsPagerAdapter(FragmentManager fm, List<Photo> gallery,
                                             AnimalGalleryPresenter presenter, AbstractSingleActivity activity) {
        super(fm);
        this.gallery = gallery;
        this.presenter = presenter;
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        return AnimalGalleryPlaceholderFragment.newInstance(gallery.get(position), presenter);
    }

    @Override
    public int getCount() {
        return gallery.size();
    }
}
