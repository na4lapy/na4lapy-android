package pl.kodujdlapolski.na4lapy.ui.animals_list.section;

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
import pl.kodujdlapolski.na4lapy.model.AnimalUtils;
import pl.kodujdlapolski.na4lapy.ui.animals_list.AnimalsListPresenter;
import pl.kodujdlapolski.na4lapy.ui.animals_list.OnClickedAction;

/**
 * Created by Natalia on 2016-02-27.
 */
public abstract class AnimalViewHolder extends RecyclerView.ViewHolder {

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
    ImageView profilePic;

    public AnimalViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void init(Animal animal, OnClickedAction onClickedAction) {
//          Photo pic = animal.getPhotos().iterator().next(); // todo remove comment when pictures are available
        Picasso.with(itemView.getContext())
                .load(picturesSample[new Random().nextInt(picturesSample.length)])
                .into(profilePic);

        name.setText(animal.getName());
        age.setText(AnimalUtils.getAnimalAgeFormatted(itemView.getContext(), animal));

        switch (animal.getMatchLevel()) {
            case 0:
                matchLevelImage.setImageResource(R.drawable.vector_drawable_procent_0);
                break;
            case 1:
                matchLevelImage.setImageResource(R.drawable.vector_drawable_procent_20);
                break;
            case 2:
                matchLevelImage.setImageResource(R.drawable.vector_drawable_procent_40);
                break;
            case 3:
                matchLevelImage.setImageResource(R.drawable.vector_drawable_procent_60);
                break;
            case 4:
                matchLevelImage.setImageResource(R.drawable.vector_drawable_procent_80);
                break;
            case 5:
                matchLevelImage.setImageResource(R.drawable.vector_drawable_procent_100);
                break;
        }
    }
}
