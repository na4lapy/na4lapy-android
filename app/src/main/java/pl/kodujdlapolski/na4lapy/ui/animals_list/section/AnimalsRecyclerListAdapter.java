package pl.kodujdlapolski.na4lapy.ui.animals_list.section;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;

/**
 * Created by Natalia on 2016-02-27.
 */
public class AnimalsRecyclerListAdapter extends RecyclerView.Adapter<AnimalViewHolder> {
    private ArrayList<Animal> animals;

    public AnimalsRecyclerListAdapter(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AnimalViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.view_holder_animal_on_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder holder, int position) {
        holder.init(animals.get(position));
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }
}
