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
package pl.kodujdlapolski.na4lapy.service.repository;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.AnimalsPage;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.service.api.ApiService;
import pl.kodujdlapolski.na4lapy.service.preferences.PreferencesService;
import pl.kodujdlapolski.na4lapy.service.user.UserService;
import rx.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

public class RepositoryServiceImpl implements RepositoryService {

    private ApiService mApiService;
    private PreferencesService mPreferencesService;
    private UserService mUserService;

    @Inject
    public RepositoryServiceImpl(ApiService apiService, PreferencesService preferencesService, UserService userService) {
        mApiService = checkNotNull(apiService, "ApiService cannot be null");
        mPreferencesService = checkNotNull(preferencesService, "PreferencesService cannot be null");
        mUserService = checkNotNull(userService, "UserService cannot be null");
    }

    @Override
    public Observable<Animal> getAnimal(@NonNull Long id) {
        checkNotNull(id, "id cannot be null");
        return mApiService.getAnimal(id);
    }

    @Override
    public Observable<List<Animal>> getAnimals() {
        return mApiService.getAnimalList().map((AnimalsPage::getData))
                .doOnNext((animals -> mUserService.sortByUserPreferences(animals)));
    }

    @Override
    public Observable<List<Animal>> getAnimalsByFavourite() {
        List<Long> favAnimalsIds = mPreferencesService.getFavouriteList();
        if (favAnimalsIds.isEmpty()) return Observable.just(new ArrayList<Animal>());
        return mApiService.getAnimals(favAnimalsIds).map((AnimalsPage::getData))
                .doOnNext((animals) -> {
                    for (Animal a : animals) {
                        a.setFavourite(true);
                    }
                });
    }

    @Override  // TODO get shelter by id from API
    public Observable<Shelter> getShelter(@NonNull Long id) {
        checkNotNull(id, "id cannot be null");
        return mApiService.getShelter(id);
    }

    @Override // TODO get many shelters
    public Observable<List<Shelter>> getShelters() {
        return null;
    }

    //TODO get animals by preferences
}
