package pl.kodujdlapolski.na4lapy.ui.animals_list.section;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;

import butterknife.Bind;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;

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
    public void init(Animal animal) {
        super.init(animal);

        addToFavBtn.setImageResource(animal.isFavourite() ? R.drawable.ic_favorite_accent : R.drawable.ic_favorite_border_accent);
        addToFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo on animal or add
            }
        });
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo on row clicked
            }
        });
    }
}
