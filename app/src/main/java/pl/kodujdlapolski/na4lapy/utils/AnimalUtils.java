package pl.kodujdlapolski.na4lapy.utils;

import android.content.Intent;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;

/**
 * Created by Natalia Wróblewska on 2016-03-13.
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
//TODO przekształcić w komponent DI. Statyczne metody są słabo testowalne
public class AnimalUtils {

    //TODO użyć StateListDrawable dla buttona zamiast zmieniać jego grafikę ręcznie podmieniając drawable
    public static int getAddToFavFabImage(Animal animal) {
        return animal.getFavourite() ? R.drawable.ic_favorite_white : R.drawable.ic_favorite_border_white;
    }

    //TODO dodać zdjęcie zwierzęcia
    public static Intent getShareIntent(Animal animal) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, animal.getName());
        return sharingIntent;
    }
}
