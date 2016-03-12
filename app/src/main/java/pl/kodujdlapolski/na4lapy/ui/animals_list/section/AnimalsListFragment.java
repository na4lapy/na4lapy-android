package pl.kodujdlapolski.na4lapy.ui.animals_list.section;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;

/**
 * Created by Natalia on 2016-03-09.
 */
public class AnimalsListFragment extends Fragment {
    private static final String ARG_ANIMALS_LIST = "animals_list";
    private ArrayList<Animal> animals;

    @Bind(R.id.animals_recycle)
    RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private AnimalsRecyclerListAdapter adapter;


    public AnimalsListFragment() {
    }

    public static AnimalsListFragment newInstance(ArrayList<Animal> animals) {
        AnimalsListFragment fragment = new AnimalsListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ANIMALS_LIST, animals);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animals_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            if (getArguments().getSerializable(ARG_ANIMALS_LIST) instanceof ArrayList<?>) {
                animals = (ArrayList<Animal>) (getArguments().getSerializable(ARG_ANIMALS_LIST));
            }
        }
        if (animals == null && savedInstanceState != null && savedInstanceState.getSerializable(ARG_ANIMALS_LIST) instanceof ArrayList<?>) {
            animals = (ArrayList<Animal>) savedInstanceState.getSerializable(ARG_ANIMALS_LIST);
        }

        recycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(layoutManager);
        adapter = new AnimalsRecyclerListAdapter(animals);
        recycler.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(ARG_ANIMALS_LIST, animals);
        super.onSaveInstanceState(outState);
    }

    public void updateList(ArrayList<Animal> animalsByType) {
        if (animals != null) {
            animals.clear();
            animals.addAll(animalsByType);
            adapter.notifyDataSetChanged();
        }
    }
}