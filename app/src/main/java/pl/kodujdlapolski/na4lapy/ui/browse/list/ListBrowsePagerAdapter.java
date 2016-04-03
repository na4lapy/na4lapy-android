package pl.kodujdlapolski.na4lapy.ui.browse.list;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.ui.browse.BrowsePresenter;

/**
 * Created by Natalia on 2016-03-09.
 */
public class ListBrowsePagerAdapter extends FragmentPagerAdapter implements BrowsePresenter.UpdateSingleElement {

    private Context ctx;
    private List<Animal> animals;
    private HashMap<BrowsePresenter.PageTypes, ListBrowseFragment> fragments = new HashMap<>();
    private BrowsePresenter presenter;

    public ListBrowsePagerAdapter(Context ctx, List<Animal> animals, FragmentManager fm, BrowsePresenter presenter) {
        super(fm);
        this.ctx = ctx;
        this.animals = animals;
        this.presenter = presenter;
    }

    @Override
    public Fragment getItem(int position) {
        BrowsePresenter.PageTypes type = BrowsePresenter.PageTypes.values()[position];
        return ListBrowseFragment.newInstance(BrowsePresenter.getAnimalsByType(animals,
                type), presenter);
    }

    @Override
    public int getCount() {
        return BrowsePresenter.PageTypes.values().length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ctx.getString(BrowsePresenter.PageTypes.values()[position].nameResId);
    }

    @Override
    public void notifyDataSetChanged() {
        for (Map.Entry<BrowsePresenter.PageTypes, ListBrowseFragment> set : fragments.entrySet()) {
            set.getValue().updateList(BrowsePresenter.getAnimalsByType(animals, set.getKey()));
        }
        super.notifyDataSetChanged();
    }

    public void notifyItemChanged(Animal animal){
        for (Map.Entry<BrowsePresenter.PageTypes, ListBrowseFragment> set : fragments.entrySet()) {
            set.getValue().updateElement(animal);
        }
    }
    public  void notifyItemRemoved(Animal animal){
        for (Map.Entry<BrowsePresenter.PageTypes, ListBrowseFragment> set : fragments.entrySet()) {
            set.getValue().removeElement(animal);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        // save the appropriate reference depending on position
        fragments.put(BrowsePresenter.PageTypes.values()[position], (ListBrowseFragment) createdFragment);
        return createdFragment;
    }
}
