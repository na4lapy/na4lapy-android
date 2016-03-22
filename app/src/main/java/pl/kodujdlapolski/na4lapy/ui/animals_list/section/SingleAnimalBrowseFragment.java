package pl.kodujdlapolski.na4lapy.ui.animals_list.section;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;

/**
 * Created by Natalia on 2016-03-15.
 */
public class SingleAnimalBrowseFragment extends Fragment {

    private static final String ARG_ANIMAL = "argAnimal";
    private Animal animal;

    public SingleAnimalBrowseFragment() {
    }

    public static SingleAnimalBrowseFragment newInstance(Animal animal) {
        SingleAnimalBrowseFragment fragment = new SingleAnimalBrowseFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ANIMAL, animal);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_animal_single_browse, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null && animal == null) {
            if (getArguments().getSerializable(ARG_ANIMAL) instanceof Animal) {
                animal = (Animal) (getArguments().getSerializable(ARG_ANIMAL));
            }
        }
        if (animal == null && savedInstanceState != null && savedInstanceState.getSerializable(ARG_ANIMAL) instanceof Animal) {
            animal = (Animal) savedInstanceState.getSerializable(ARG_ANIMAL);
        }
        AnimalSingleBrowseViewHolder vh = new AnimalSingleBrowseViewHolder(getView());
        vh.init(animal);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(ARG_ANIMAL, animal);
        super.onSaveInstanceState(outState);
    }
}