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
import pl.kodujdlapolski.na4lapy.ui.browse.BrowseContract;
import pl.kodujdlapolski.na4lapy.ui.browse.BrowsePresenter;
import pl.kodujdlapolski.na4lapy.ui.browse.PageTypes;

/**
 * Created by Natalia Wróblewska on 2016-03-09.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
public class ListBrowsePagerAdapter extends FragmentPagerAdapter implements BrowseContract.Adapter {

    private Context ctx;
    private List<Animal> animals;
    private HashMap<PageTypes, ListBrowseFragment> fragments = new HashMap<>();

    public ListBrowsePagerAdapter(Context ctx, List<Animal> animals, FragmentManager fm) {
        super(fm);
        this.ctx = ctx;
        this.animals = animals;
    }

    @Override
    public Fragment getItem(int position) {
        PageTypes type = PageTypes.values()[position];
        return ListBrowseFragment.newInstance(BrowsePresenter.getAnimalsByType(animals,
                type));
    }

    @Override
    public int getCount() {
        return PageTypes.values().length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ctx.getString(PageTypes.values()[position].nameResId);
    }

    @Override
    public void notifyDataSetChanged() {
        for (Map.Entry<PageTypes, ListBrowseFragment> set : fragments.entrySet()) {
            set.getValue().updateList(BrowsePresenter.getAnimalsByType(animals, set.getKey()));
        }
        super.notifyDataSetChanged();
    }

    public void notifyItemChanged(Animal animal){
        for (Map.Entry<PageTypes, ListBrowseFragment> set : fragments.entrySet()) {
            set.getValue().updateElement(animal);
        }
    }
    public  void notifyItemRemoved(Animal animal){
        for (Map.Entry<PageTypes, ListBrowseFragment> set : fragments.entrySet()) {
            set.getValue().removeElement(animal);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        // save the appropriate reference depending on position
        fragments.put(PageTypes.values()[position], (ListBrowseFragment) createdFragment);
        return createdFragment;
    }
}
