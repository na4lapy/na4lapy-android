package pl.kodujdlapolski.na4lapy.ui.browse;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.user.UserService;
import pl.kodujdlapolski.na4lapy.utils.AnimalUtils;

/**
 * Created by Natalia Wróblewska on 2016-02-27.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
public abstract class AbstractBrowseViewHolder extends RecyclerView.ViewHolder {

    String[] picturesSample = new String[]{"http://2.bp.blogspot.com/-uI_rgOFxmT0/Vw6lkKwdTDI/AAAAAAAABLw/T0d2NW0Uc-MsYe1y6u6zvdUdCJxFv4uwACK4B/s1600/pies1_5.jpg",
            "http://4.bp.blogspot.com/-zjCctidFhyA/Vw6lkEpvejI/AAAAAAAABLA/7Y375FdJBSgnNhHQLqj922KoJtRVQBDqACK4B/s1600/pies2_1.jpg"};

    @Bind(R.id.view_holder_animal_name)
    TextView name;
    @Bind(R.id.view_holder_animal_age)
    TextView age;
    @Bind(R.id.matching_lvl_image)
    ImageView matchLevelImage;
    @Bind(R.id.profile_pic_on_list)
    public ImageView profilePic;

    private UserService mUserService;

    public AbstractBrowseViewHolder(View itemView, UserService userService) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mUserService = userService;
    }

    public void init(Animal animal, OnBrowseElementClickedAction onBrowseElementClickedAction) {
//          Photo pic = animal.getPhotos().iterator().next(); // todo remove comment when pictures are available
        Picasso.with(itemView.getContext())
                .load(picturesSample[animal.getId() %2 == 0 ? 0 : 1 ])
                .placeholder(R.drawable.pic_404dog)
                .into(profilePic);

        name.setText(animal.getName());
        age.setText(AnimalUtils.getAnimalAgeFormatted(itemView.getContext(), animal));
        matchLevelImage.getDrawable().setLevel(mUserService.getPreferencesComplianceLevel(animal));
    }
}
