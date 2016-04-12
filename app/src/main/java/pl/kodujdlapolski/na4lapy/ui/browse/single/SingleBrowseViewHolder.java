package pl.kodujdlapolski.na4lapy.ui.browse.single;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.AnimalUtils;
import pl.kodujdlapolski.na4lapy.model.type.ActivityAnimal;
import pl.kodujdlapolski.na4lapy.model.type.Gender;
import pl.kodujdlapolski.na4lapy.ui.browse.AbstractBrowseViewHolder;
import pl.kodujdlapolski.na4lapy.ui.browse.OnBrowseElementClickedAction;

/**
 * Created by Natalia on 2016-03-22.
 */

public class SingleBrowseViewHolder extends AbstractBrowseViewHolder {

    @Bind(R.id.add_to_fav_fab)
    FloatingActionButton addToFavFab;
    @Bind(R.id.animal_size_image)
    ImageView sizeImage;
    @Bind(R.id.animal_gender_image)
    ImageView genderImage;
    @Bind(R.id.animal_activity_image)
    ImageView activityImage;

    public SingleBrowseViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void init(Animal animal, OnBrowseElementClickedAction onBrowseElementClickedAction) {
        super.init(animal, onBrowseElementClickedAction);
        addToFavFab.setImageResource(AnimalUtils.getAddToFavFabImage(animal));
        addToFavFab.setOnClickListener(v -> {
          onBrowseElementClickedAction.favourite(animal);
        });
        profilePic.setOnClickListener(v -> {
            onBrowseElementClickedAction.details(animal);
        });

        sizeImage.setImageResource(AnimalUtils.getSizeImage(animal));
        activityImage.setImageResource(AnimalUtils.getActivityImage(animal));
        genderImage.setImageResource(AnimalUtils.getGenderImage(animal));
    }
}
