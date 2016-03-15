package pl.kodujdlapolski.na4lapy.ui.animals_list.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.AnimalUtils;

/**
 * Created by Natalia on 2016-02-27.
 */
public class AnimalViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.view_holder_animal_name)
    TextView name;
    @Bind(R.id.view_holder_animal_age)
    TextView age;
    @Bind(R.id.add_to_fav_btn)
    ImageButton addToFavBtn;
    @Bind(R.id.match_level_rating_bar)
    RatingBar matchLevelRatingBar;

    public AnimalViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void init(Animal animal) {
        name.setText(animal.getName());
        age.setText(AnimalUtils.getAnimalAgeFormatted(itemView.getContext(), animal));
        addToFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo on animal remove
            }
        });
        matchLevelRatingBar.setRating(animal.getMatchLevel());
    }
}
