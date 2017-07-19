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

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.AnimalsPage;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.service.api.ApiService;
import pl.kodujdlapolski.na4lapy.service.preferences.PreferencesService;
import pl.kodujdlapolski.na4lapy.service.user.UserService;
import rx.Observable;
import rx.observers.TestSubscriber;

import static com.google.common.base.Verify.verifyNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryServiceImplTest {

    @Mock
    private ApiService apiService;

    @Mock
    private PreferencesService preferencesService;

    @Mock
    private UserService userService;

    @InjectMocks
    private RepositoryServiceImpl repositoryService;

    private Long animalId = 60L, shelterId = 61L;
    private Animal animal;
    private Shelter shelter;
    private AnimalsPage page;

    @Before
    public void setUp() {
        repositoryService = new RepositoryServiceImpl(apiService, preferencesService, userService);

        animal = new Animal();
        animal.setId(animalId);

        shelter = new Shelter();
        shelter.setId(shelterId);

        List animals = Lists.newArrayList(animal);
        page = new AnimalsPage();
        page.setData(animals);
    }

    @Test
    public void testGetAnimal() {
        when(apiService.getAnimal(animalId)).thenReturn(Observable.just(animal));
        Observable<Animal> result = repositoryService.getAnimal(animalId);

        verifyNotNull(result);
        TestSubscriber<Animal> testSubscriber = new TestSubscriber<>();
        result.subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Collections.singletonList(animal));
    }

    @Test
    public void testGetAnimals() {
        // given
        when(apiService.getAnimalList()).thenReturn(Observable.just(page));

        // when
        Observable<List<Animal>> result = repositoryService.getAnimals();

        // then
        performTestOnSubscriber(result, page.getData());
    }


    @Test
    public void testGetAnimalsByFavourite() {
        // given
        List<Long> ids = Collections.singletonList(animalId);
        when(preferencesService.getFavouriteList()).thenReturn(ids);
        when(apiService.getAnimals(ids)).thenReturn(Observable.just(page));
        // when
        Observable<List<Animal>> result = repositoryService.getAnimalsByFavourite();

        // then
        performTestOnSubscriber(result, page.getData());
    }

    @Test
    public void testGetShelter() {
        // given
        when(apiService.getShelter(shelterId)).thenReturn(Observable.just(shelter));

        // when
        Observable<Shelter> result = repositoryService.getShelter(shelterId);

        // then
        performTestOnSubscriber(result, shelter);
    }

    @Test
    public void testGetShelters() throws Exception {
        // given
        List shelters = Lists.newArrayList(shelter);
        when(apiService.getShelters()).thenReturn(Observable.just(shelters));

        // when
        Observable<List<Shelter>> result = repositoryService.getShelters();

        // then
        performTestOnSubscriber(result, shelters);
    }

    public <T> void performTestOnSubscriber(Observable<T> result, T expected) {
        verifyNotNull(result);
        TestSubscriber<T> testSubscriber = new TestSubscriber<>();
        result.subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Collections.singletonList(expected));
    }
}