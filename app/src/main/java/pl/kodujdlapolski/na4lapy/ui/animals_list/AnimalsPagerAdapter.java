package pl.kodujdlapolski.na4lapy.ui.animals_list;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.ui.animals_list.section.AnimalsListFragment;

/**
 * Created by Natalia on 2016-03-09.
 */
public class AnimalsPagerAdapter extends FragmentPagerAdapter {

    private Context ctx;
    private List<Animal> animals;
    private HashMap<AnimalsListPresenter.PageTypes, AnimalsListFragment> fragments = new HashMap<>();

    public AnimalsPagerAdapter(Context ctx, List<Animal> animals, FragmentManager fm) {
        super(fm);
        this.ctx = ctx;
        this.animals = animals;
    }

    @Override
    public Fragment getItem(int position) {
        AnimalsListPresenter.PageTypes type = AnimalsListPresenter.PageTypes.values()[position];
        return AnimalsListFragment.newInstance(AnimalsListPresenter.getAnimalsByType(animals,
                type), type);
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
        for (Map.Entry<AnimalsListPresenter.PageTypes, AnimalsListFragment> set : fragments.entrySet()) {
            set.getValue().updateList(AnimalsListPresenter.getAnimalsByType(animals, set.getKey()));
        }
        super.notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        // save the appropriate reference depending on position
        fragments.put(AnimalsListPresenter.PageTypes.values()[position], (AnimalsListFragment) createdFragment);
        return createdFragment;
    }
}
