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

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.AnimalsPage;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import rx.Observable;

public interface ApiService {

    Observable<Shelter> getShelter(Long id);
    Observable<AnimalsPage> getAnimalList();
    Observable<Animal> getAnimal(Long id);
    Observable<AnimalsPage> getAnimals(List<Long> ids);
}
