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
import com.google.common.collect.Maps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.service.api.ApiService;
import pl.kodujdlapolski.na4lapy.service.preferences.PreferencesService;
import pl.kodujdlapolski.na4lapy.service.user.UserService;
import rx.Observable;
import rx.observers.TestSubscriber;

import static com.google.common.base.Verify.verifyNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
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

    @Before
    public void setUp() throws Exception {
        repositoryService = new RepositoryServiceImpl(apiService, preferencesService, userService);

        animal = new Animal();
        animal.setId(animalId);

        shelter = new Shelter();
        shelter.setId(shelterId);
    }

    @Test
    public void testGetAnimal() throws Exception {
        Observable<Animal> result = repositoryService.getAnimal(animalId);

        verifyNotNull(result);
        TestSubscriber<Animal> testSubscriber = new TestSubscriber<>();
        result.subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Arrays.asList(animal));
    }


    @Test
    public void testGetAnimalsByShelterId() throws Exception {

        List animals = Lists.newArrayList(animal);

//        // when
//        Observable<List<Animal>> result = repositoryService.getAnimalsByShelterId(shelterId);
//
//        // then
//        verifyNotNull(result);
//        TestSubscriber<List<Animal>> testSubscriber = new TestSubscriber<>();
//        result.subscribe(testSubscriber);
//        testSubscriber.assertNoErrors();
//        testSubscriber.assertReceivedOnNext(Arrays.asList(animals));
    }

    @Test
    public void testGetAnimalsByShelterIdShouldReturnSqlException() throws Exception {

//        // when
//        Observable<List<Animal>> result = repositoryService.getAnimalsByShelterId(shelterId);
//
//        // then
//        verifyNotNull(result);
//        TestSubscriber<List<Animal>> testSubscriber = new TestSubscriber<>();
//        result.subscribe(testSubscriber);
//        testSubscriber.assertError(SQLException.class);
    }

    @Test
    public void testGetAnimals() throws Exception {
        // given
        ArrayList animals = Lists.newArrayList(animal);
        when(apiService.getAnimalList()).thenReturn(Observable.empty());

        // when
        Observable<List<Animal>> result = repositoryService.getAnimals();

        // then
        verifyNotNull(result);
        TestSubscriber<List<Animal>> testSubscriber = new TestSubscriber<>();
        result.subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
    }


    @Test
    public void testGetAnimalsByFavourite() throws Exception {
        // given
        List animals = Lists.newArrayList(animal);

        // when
        Observable<List<Animal>> result = repositoryService.getAnimalsByFavourite();

        // then
        verifyNotNull(result);
        TestSubscriber<List<Animal>> testSubscriber = new TestSubscriber<>();
        result.subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
    }

    @Test
    public void testSetFavourite() throws Exception {
//        // when
//        Observable<Long> result = repositoryService.setFavourite(animalId, true);
//
//        // then
//        verifyNotNull(result);
//        TestSubscriber<Long> testSubscriber = new TestSubscriber<>();
//        result.subscribe(testSubscriber);
//        testSubscriber.assertNoErrors();
//        testSubscriber.assertReceivedOnNext(Arrays.asList(animalId));
    }


    @Test
    public void testGetShelter() throws Exception {
        // given
        when(apiService.getShelter(1L)).thenReturn(Observable.just(shelter));

        // when
        Observable<Shelter> result = repositoryService.getShelter(shelterId);

        // then
        verifyNotNull(result);
        TestSubscriber<Shelter> testSubscriber = new TestSubscriber<>();
        result.subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Arrays.asList(shelter, shelter));
    }

    @Test
    public void testGetShelterShouldReturnSqlException() throws Exception {
        // given
        when(apiService.getShelter(1L)).thenReturn(Observable.just(shelter));

        // when
        Observable<Shelter> result = repositoryService.getShelter(shelterId);

        // then
        verifyNotNull(result);
        TestSubscriber<Shelter> testSubscriber = new TestSubscriber<>();
        result.subscribe(testSubscriber);
        testSubscriber.assertError(SQLException.class);

    }

    @Test
    public void testGetShelters() throws Exception {
        // given
        List shelters = Lists.newArrayList(shelter);

        // when
        Observable<List<Shelter>> result = repositoryService.getShelters();

        // then
        verifyNotNull(result);
        TestSubscriber<List<Shelter>> testSubscriber = new TestSubscriber<>();
        result.subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Arrays.asList(shelters));
    }
}