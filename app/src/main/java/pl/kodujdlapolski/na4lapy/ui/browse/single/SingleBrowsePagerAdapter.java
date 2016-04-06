package pl.kodujdlapolski.na4lapy.ui.browse.single;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.ui.browse.BrowsePresenter;

/**
 * Created by Natalia on 2016-03-15.
 */
public class SingleBrowsePagerAdapter extends FragmentPagerAdapter implements BrowsePresenter.UpdateSingleElement {
    private List<Animal> animals;
    private BrowsePresenter presenter;
    private FragmentManager manager;
    private int viewPagerId;

    public SingleBrowsePagerAdapter(List<Animal> animals, FragmentManager manager, BrowsePresenter presenter, int viewPagerId) {
        super(manager);
        this.manager = manager;
        this.animals = animals;
        this.presenter = presenter;
        this.viewPagerId = viewPagerId;
    }

    @Override
    public Fragment getItem(int position) {
        return SingleBrowseFragment.newInstance(animals.get(position), presenter);
    }

    @Override
    public int getCount() {
        return animals.size();
    }

    @Override
    public void notifyItemChanged(Animal animal) {
        int positionOnList = BrowsePresenter.getIndexOfAnimalOnList(animals, animal);
        if (positionOnList != -1) {
            String tag = "android:switcher:" + viewPagerId + ":" + positionOnList;
            ((SingleBrowseFragment) manager.findFragmentByTag(tag)).update(animal);
        }
    }

    @Override
    public void notifyItemRemoved(Animal animal) {
        this.notifyDataSetChanged(); // as item in view pager should be removed
    }
}
