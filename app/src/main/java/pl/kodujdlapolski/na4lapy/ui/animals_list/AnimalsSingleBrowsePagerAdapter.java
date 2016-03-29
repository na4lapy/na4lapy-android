package pl.kodujdlapolski.na4lapy.ui.animals_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.ui.animals_list.section.SingleAnimalBrowseFragment;

/**
 * Created by Natalia on 2016-03-15.
 */
public class AnimalsSingleBrowsePagerAdapter extends FragmentPagerAdapter implements AnimalsListPresenter.UpdateSingleElement {
    private List<Animal> animals;
    private AnimalsListPresenter presenter;
    private FragmentManager manager;
    private int viewPagerId;

    public AnimalsSingleBrowsePagerAdapter(List<Animal> animals, FragmentManager manager, AnimalsListPresenter presenter, int viewPagerId) {
        super(manager);
        this.manager = manager;
        this.animals = animals;
        this.presenter = presenter;
        this.viewPagerId = viewPagerId;
    }

    @Override
    public Fragment getItem(int position) {
        return SingleAnimalBrowseFragment.newInstance(animals.get(position), presenter);
    }

    @Override
    public int getCount() {
        return animals.size();
    }

    @Override
    public void notifyItemChanged(Animal animal) {
        int positionOnList = AnimalsListPresenter.getIndexOfAnimalOnList(animals, animal);
        if (positionOnList != -1) {
            String tag = "android:switcher:" + viewPagerId + ":" + positionOnList;
            ((SingleAnimalBrowseFragment) manager.findFragmentByTag(tag)).update(animal);
        }
    }

    @Override
    public void notifyItemRemoved(Animal animal) {
        this.notifyDataSetChanged(); // as item in view pager should be removed
    }
}
