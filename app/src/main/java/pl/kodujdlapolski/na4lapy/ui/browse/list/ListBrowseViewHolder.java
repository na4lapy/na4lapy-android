package pl.kodujdlapolski.na4lapy.ui.browse.list;

import android.view.View;
import android.widget.ImageButton;

import butterknife.Bind;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.ui.browse.AbstractBrowseViewHolder;
import pl.kodujdlapolski.na4lapy.ui.browse.OnBrowseElementClickedAction;

/**
 * Created by Natalia on 2016-03-22.
 */
public class ListBrowseViewHolder extends AbstractBrowseViewHolder {

    @Bind(R.id.add_to_fav_btn)
    ImageButton addToFavBtn;

    public ListBrowseViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void init(Animal animal, OnBrowseElementClickedAction onBrowseElementClickedAction) {
        super.init(animal, onBrowseElementClickedAction);

        addToFavBtn.setImageResource(animal.isFavourite() ? R.drawable.ic_favorite_accent : R.drawable.ic_favorite_border_accent);
        addToFavBtn.setOnClickListener(v -> {
            onBrowseElementClickedAction.favourite(animal);
        });
        itemView.setOnClickListener(v -> {
            onBrowseElementClickedAction.details(animal);
        });
    }
}
