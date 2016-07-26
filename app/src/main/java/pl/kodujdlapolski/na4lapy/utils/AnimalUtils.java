/*
 * Copyright (C) 2016 Stowarzyszenie Na4Łapy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.kodujdlapolski.na4lapy.utils;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;

@Deprecated
public class AnimalUtils {

    //TODO https://trello.com/c/MAZ8umPt
    @Deprecated
    public static int getAddToFavFabImage(Animal animal) {
        return Boolean.TRUE.equals(animal.getFavourite()) ? R.drawable.ic_favorite_white_24dp : R.drawable.ic_favorite_border_white_24dp;
    }
}
