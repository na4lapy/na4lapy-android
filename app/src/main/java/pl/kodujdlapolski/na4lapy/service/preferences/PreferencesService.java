/*
 *	Copyright 2017 Stowarzyszenie Na4Łapy
 *
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *	http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */
package pl.kodujdlapolski.na4lapy.service.preferences;

import android.support.annotation.Nullable;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.UserPreferences;
import pl.kodujdlapolski.na4lapy.service.payments.model.Customer;

public interface PreferencesService {

    String KEY_USER_PREFERENCES = "PreferencesService.KEY_USER_PREFERENCES";
    String KEY_USER_FAVOURITES = "PreferencesService.KEY_USER_FAVOURITES";
    String KEY_PAYMENT_CUSTOMER = "PreferencesService.KEY_PAYMENT_CUSTOMER";
    String KEY_WAS_INTRODUCTION_SHOWN = "PreferencesService.KEY_WAS_INTRODUCTION_SHOWN";

    void setUserPreferences(UserPreferences userPreferences);
    @Nullable UserPreferences getUserPreferences();

    void addToFavourite(@Nullable Long animalId);
    void removeFromFavourite(@Nullable Long animalId);
    List<Long> getFavouriteList();

    void setCustomer(Customer customer);
    @Nullable Customer getCustomer();

    boolean shouldIntroductionBeShown();
}
