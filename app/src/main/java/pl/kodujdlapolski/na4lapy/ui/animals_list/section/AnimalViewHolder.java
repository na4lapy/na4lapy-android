package pl.kodujdlapolski.na4lapy.ui.animals_list.section;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;

/**
 * Created by Natalia on 2016-02-27.
 */
public class AnimalViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.view_holder_animal_name)
    TextView name;
    @Bind(R.id.view_holder_animal_age)
    TextView age;

    public AnimalViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void init(Animal animal) {
        name.setText(animal.getName());
//        age.setText(animal.getAge() +" lat");
    }
}
