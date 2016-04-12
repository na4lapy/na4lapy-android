package pl.kodujdlapolski.na4lapy.ui.browse;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.utils.AnimalUtils;

/**
 * Created by Natalia on 2016-02-27.
 */
public abstract class AbstractBrowseViewHolder extends RecyclerView.ViewHolder {

    String[] picturesSample = new String[]{"http://schroniskopromyk.pl/wp-content/uploads/2016/03/Bodek-1-150x150.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/03/Mundi-1-150x150.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/03/Bobo-1-150x150.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/03/Fado-5-150x150.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/02/Morus-5-150x150.jpg"};

    @Bind(R.id.view_holder_animal_name)
    TextView name;
    @Bind(R.id.view_holder_animal_age)
    TextView age;
    @Bind(R.id.matching_lvl_image)
    ImageView matchLevelImage;
    @Bind(R.id.profile_pic_on_list)
    public ImageView profilePic;

    public AbstractBrowseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void init(Animal animal, OnBrowseElementClickedAction onBrowseElementClickedAction) {
//          Photo pic = animal.getPhotos().iterator().next(); // todo remove comment when pictures are available
        Picasso.with(itemView.getContext())
                .load(picturesSample[new Random().nextInt(picturesSample.length)])
                .into(profilePic);

        name.setText(animal.getName());
        age.setText(AnimalUtils.getAnimalAgeFormatted(itemView.getContext(), animal));
        matchLevelImage.setImageResource(AnimalUtils.getMatchingLvlImage(animal));
    }
}
