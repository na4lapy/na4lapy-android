package pl.kodujdlapolski.na4lapy.ui.animals_list.section;

import android.view.View;
import android.widget.ImageButton;

import butterknife.Bind;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.ui.animals_list.AnimalsListPresenter;
import pl.kodujdlapolski.na4lapy.ui.animals_list.OnClickedAction;

/**
 * Created by Natalia on 2016-03-22.
 */
public class AnimalListViewHolder extends AnimalViewHolder {

    @Bind(R.id.add_to_fav_btn)
    ImageButton addToFavBtn;

    public AnimalListViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void init(Animal animal, OnClickedAction onClickedAction) {
        super.init(animal, onClickedAction);

        addToFavBtn.setImageResource(animal.isFavourite() ? R.drawable.ic_favorite_accent : R.drawable.ic_favorite_border_accent);
        addToFavBtn.setOnClickListener(v -> {
            onClickedAction.favourite(animal);
        });
        itemView.setOnClickListener(v -> {
            onClickedAction.details(animal);
        });
    }
}
