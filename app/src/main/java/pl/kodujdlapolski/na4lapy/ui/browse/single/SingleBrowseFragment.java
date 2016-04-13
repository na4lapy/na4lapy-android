package pl.kodujdlapolski.na4lapy.ui.browse.single;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.kodujdlapolski.na4lapy.R;
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
public class SingleBrowseFragment extends Fragment {

    private static final String ARG_ANIMAL = "argAnimal";
    private Animal animal;
    private BrowsePresenter presenter;
    private SingleBrowseViewHolder viewHolder;

    public SingleBrowseFragment() {
    }

    public static SingleBrowseFragment newInstance(Animal animal, BrowsePresenter presenter) {
        SingleBrowseFragment fragment = new SingleBrowseFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ANIMAL, animal);
        args.putParcelable(BrowsePresenter.ARG_PRESENTER, presenter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_single_browse, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initAnimal(savedInstanceState);
        presenter = BrowsePresenter.init(getArguments(), savedInstanceState);

        viewHolder = new SingleBrowseViewHolder(getView());
        viewHolder.init(animal, presenter);
    }

    private void initAnimal(Bundle savedInstanceState) {
        if (getArguments() != null && animal == null) {
            if (getArguments().getSerializable(ARG_ANIMAL) instanceof Animal) {
                animal = (Animal) (getArguments().getSerializable(ARG_ANIMAL));
            }
        }
        if (animal == null && savedInstanceState != null && savedInstanceState.getSerializable(ARG_ANIMAL) instanceof Animal) {
            animal = (Animal) savedInstanceState.getSerializable(ARG_ANIMAL);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(ARG_ANIMAL, animal);
        outState.putParcelable(BrowsePresenter.ARG_PRESENTER, presenter);
        super.onSaveInstanceState(outState);
    }

    public void update(Animal changedAnimal) {
        animal = changedAnimal;
        viewHolder.init(animal, presenter);
    }
}