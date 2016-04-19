package pl.kodujdlapolski.na4lapy.ui.browse.single;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.ui.browse.BrowsePresenter;

/**
 * Created by Natalia Wróblewska on 2016-03-15.
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
public class SingleBrowsePagerAdapter extends FragmentPagerAdapter implements BrowsePresenter.UpdateSingleElement {
    private List<Animal> animals;
    private FragmentManager manager;
    private int viewPagerId;

    public SingleBrowsePagerAdapter(List<Animal> animals, FragmentManager manager, int viewPagerId) {
        super(manager);
        this.manager = manager;
        this.animals = animals;
        this.viewPagerId = viewPagerId;
    }

    @Override
    public Fragment getItem(int position) {
        return SingleBrowseFragment.newInstance(animals.get(position));
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
