package pl.kodujdlapolski.na4lapy.ui.animals_list;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.ui.animals_list.section.AnimalsListFragment;

/**
 * Created by Natalia on 2016-03-09.
 */
public class AnimalsPagerAdapter extends FragmentPagerAdapter {

    private Context ctx;
    private ArrayList<Animal> animals;

    public AnimalsPagerAdapter(Context ctx, ArrayList<Animal> animals, FragmentManager fm) {
        super(fm);
        this.ctx = ctx;
        this.animals = animals;
    }

    @Override
    public Fragment getItem(int position) {
        return AnimalsListFragment.newInstance(AnimalsListPresenter.getAnimalsByType(animals,
                AnimalsListPresenter.PageTypes.values()[position]));
    }

    @Override
    public int getCount() {
        return AnimalsListPresenter.PageTypes.values().length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ctx.getString(AnimalsListPresenter.PageTypes.values()[position].nameResId);
    }

    @Override
    public void notifyDataSetChanged() {
        // todo wywala sie
        for (int i = 0; i < getCount(); i++) {
            ((AnimalsListFragment) getItem(i)).updateList(AnimalsListPresenter.getAnimalsByType(animals,
                    AnimalsListPresenter.PageTypes.values()[i]));
        }
        super.notifyDataSetChanged();
    }
}
