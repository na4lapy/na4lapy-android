package pl.kodujdlapolski.na4lapy.ui.browse.single;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.ui.browse.AbstractBrowseViewHolder;
import pl.kodujdlapolski.na4lapy.ui.browse.OnBrowseElementClickedAction;
import pl.kodujdlapolski.na4lapy.user.UserService;
import pl.kodujdlapolski.na4lapy.utils.AnimalUtils;

/**
 * Created by Natalia Wróblewska on 2016-03-22.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class SingleBrowseViewHolder extends AbstractBrowseViewHolder {

    @BindView(R.id.add_to_fav_fab)
    FloatingActionButton addToFavFab;
    @BindView(R.id.animal_size_image)
    ImageView sizeImage;
    @BindView(R.id.animal_gender_image)
    ImageView genderImage;
    @BindView(R.id.animal_activity_image)
    ImageView activityImage;

    public SingleBrowseViewHolder(View itemView, UserService userService) {
        super(itemView, userService);
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
        if (animal.getSize() != null)
            sizeImage.setImageResource(animal.getSize().getDrawableResId());
        if (animal.getActivity() != null)
            activityImage.setImageResource(animal.getActivity().getDrawableResId());
        if (animal.getGender() != null)
            genderImage.setImageResource(animal.getGender().getDrawableResId());
    }
}
