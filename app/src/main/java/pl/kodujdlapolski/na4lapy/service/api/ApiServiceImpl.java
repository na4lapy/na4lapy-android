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
package pl.kodujdlapolski.na4lapy.service.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.AnimalsPage;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import rx.Observable;

public class ApiServiceImpl implements ApiService {

    private Api mApi;

    @Inject
    public ApiServiceImpl(Api api) {
        mApi = api;
    }

    @Override
    public Observable<Shelter> getShelter(Long id) {
        return mApi.getShelter(id);
    }

    @Override
    public Observable<List<Shelter>> getShelters() {
        return mApi.getShelters();
    }

    @Override
    public Observable<AnimalsPage> getAnimalList() {
        return mApi.getAnimalList();
    }

    @Override
    public Observable<Animal> getAnimal(Long id) {
        return mApi.getAnimal(id);
    }

    @Override
    public Observable<AnimalsPage> getAnimals(List<Long> ids) {
        JsonObject object = new JsonObject();
        object.add("ids", new Gson().toJsonTree(ids));
        return mApi.getAnimals(object);
    }
}
