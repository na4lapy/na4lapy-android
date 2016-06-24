package pl.kodujdlapolski.na4lapy.ui.browse.list;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.Na4LapyApp;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.service.user.UserService;
import pl.kodujdlapolski.na4lapy.ui.browse.BrowsePresenter;

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
public class ListBrowseFragment extends Fragment {
    private static final String ARG_ANIMALS_LIST = "animals_list";

    @Inject UserService userService;

    private ArrayList<Animal> animals;

    @BindView(R.id.animals_recycle)
    RecyclerView recycler;
    private ListBrowseRecyclerAdapter adapter;

    public ListBrowseFragment() {
    }

    public static ListBrowseFragment newInstance(List<Animal> animals) {
        ListBrowseFragment fragment = new ListBrowseFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ANIMALS_LIST, (ArrayList<Animal>) animals);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_browse, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((Na4LapyApp) getActivity().getApplication()).getComponent().inject(this);
        initAnimals(savedInstanceState);
        recycler.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(null);
        adapter = new ListBrowseRecyclerAdapter(animals, ((ListBrowseActivity) getActivity()).getPresenter(), userService);
        recycler.setAdapter(adapter);
    }

    private void initAnimals(Bundle savedInstanceState) {
        if (getArguments() != null && (animals == null || animals.isEmpty())) {
            if (getArguments().getSerializable(ARG_ANIMALS_LIST) instanceof ArrayList<?>) {
                animals = (ArrayList<Animal>) (getArguments().getSerializable(ARG_ANIMALS_LIST));
            }
        }
        if (animals == null && savedInstanceState != null && savedInstanceState.getSerializable(ARG_ANIMALS_LIST) instanceof ArrayList<?>) {
            animals = (ArrayList<Animal>) savedInstanceState.getSerializable(ARG_ANIMALS_LIST);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(ARG_ANIMALS_LIST, animals);

        super.onSaveInstanceState(outState);
    }

    public void updateList(final List<Animal> animalsByType) {
        if (animals != null) {
            animals.clear();
            animals.addAll(animalsByType);
            adapter.notifyDataSetChanged();
        }
    }

    public void updateElement(final Animal changedAnimal) {
        if (animals != null) {
            int index = BrowsePresenter.getIndexOfAnimalOnList(animals, changedAnimal);
            if (index != -1) {
                animals.set(index, changedAnimal);
                adapter.notifyItemChanged(index);
            }
        }
    }

    public void removeElement(Animal animalToRemove) {
        if (animals != null) {
            int index = BrowsePresenter.getIndexOfAnimalOnList(animals, animalToRemove);
            if (index != -1) {
                animals.remove(index);
                adapter.notifyItemRemoved(index);
                showUndoSnack(animalToRemove);
            }
        }
    }

    private void showUndoSnack(Animal animalToUndo) {
        if (getView() != null) {
            Snackbar.make(getView(), String.format(getString(R.string.removed_from_fav_undo_mess), animalToUndo.getName()), Snackbar.LENGTH_LONG)
                    .setAction(R.string.removed_from_fav_undo_option, v -> {
                        ((ListBrowseActivity) getActivity()).getPresenter().handleUndoAnimal(animalToUndo);
                    })
                    .show();
        }
    }
}