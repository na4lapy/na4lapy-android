package pl.kodujdlapolski.na4lapy.ui.animals_list.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.AnimalUtils;

/**
 * Created by Natalia on 2016-02-27.
 */
public class AnimalViewHolder extends RecyclerView.ViewHolder {

    String[] picturesSample = new String[]{"http://schroniskopromyk.pl/wp-content/uploads/2016/03/Bodek-1-150x150.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/03/Mundi-1-150x150.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/03/Bobo-1-150x150.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/03/Fado-5-150x150.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/02/Morus-5-150x150.jpg"};

    @Bind(R.id.view_holder_animal_name)
    TextView name;
    @Bind(R.id.view_holder_animal_age)
    TextView age;
    @Bind(R.id.add_to_fav_btn)
    ImageButton addToFavBtn;
    @Bind(R.id.match_level_rating_bar)
    RatingBar matchLevelRatingBar;
    @Bind(R.id.profile_pic_on_list)
    ImageView profilePic;

    public AnimalViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void init(Animal animal) {
        //  Photo pic = animal.getPhotos().iterator().next(); // todo remove comment when pictures are available
        Picasso.with(itemView.getContext())
                .load(picturesSample[new Random().nextInt(picturesSample.length)])
                .into(profilePic);

        name.setText(animal.getName());
        age.setText(AnimalUtils.getAnimalAgeFormatted(itemView.getContext(), animal));
        addToFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo on animal remove
            }
        });
        matchLevelRatingBar.setRating(animal.getMatchLevel());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo on animal click
            }
        });
    }
}
