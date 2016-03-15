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
public class AnimalsSingleBrowsePagerAdapter extends FragmentPagerAdapter {
    private List<Animal> animals;

    public AnimalsSingleBrowsePagerAdapter(List<Animal> animals, FragmentManager fm) {
        super(fm);
        this.animals = animals;
    }

    @Override
    public Fragment getItem(int position) {
        return SingleAnimalBrowseFragment.newInstance(animals.get(position));
    }

    @Override
    public int getCount() {
        return animals.size();
    }
}
