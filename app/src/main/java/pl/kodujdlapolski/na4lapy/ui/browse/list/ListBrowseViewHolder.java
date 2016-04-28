package pl.kodujdlapolski.na4lapy.ui.browse.list;

import android.view.View;
import android.widget.ImageButton;

import butterknife.Bind;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.ui.browse.AbstractBrowseViewHolder;
import pl.kodujdlapolski.na4lapy.ui.browse.OnBrowseElementClickedAction;
import pl.kodujdlapolski.na4lapy.user.UserService;

/**
 * Created by Natalia Wróblewska on 2016-03-22.
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
public class ListBrowseViewHolder extends AbstractBrowseViewHolder {

    @Bind(R.id.add_to_fav_btn)
    ImageButton addToFavBtn;

    public ListBrowseViewHolder(View itemView, UserService userService) {
        super(itemView, userService);
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
