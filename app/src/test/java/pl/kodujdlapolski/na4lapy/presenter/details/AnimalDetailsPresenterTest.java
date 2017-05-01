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
package pl.kodujdlapolski.na4lapy.presenter.details;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.service.preferences.PreferencesService;
import pl.kodujdlapolski.na4lapy.service.repository.RepositoryService;
import rx.Observable;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnimalDetailsPresenterTest {

    @Mock RepositoryService repositoryService;
    @Mock PreferencesService preferencesService;
    @Mock AnimalDetailsContract.View view;

    private AnimalDetailsPresenter presenter;
    private Long animalId = 1L;
    private Long wrongAnimalId = 0L;
    private Animal animal;

    @Before
    public void setUp() {
        presenter = new AnimalDetailsPresenter(view, animalId, repositoryService, preferencesService);
        animal = new Animal();
    }

    @Test
    public void testSetupBasicData() {
        when(repositoryService.getAnimal(animalId)).thenReturn(Observable.just(animal));

        presenter.setupBasicData();

        verify(view, times(1)).populateView(animal);
        verify(view, times(1)).showLoading(false);
        verify(view, times(1)).showLoading(true);
    }

    @Test
    public void testSetupFavPositive() {
        when(preferencesService.getFavouriteList()).thenReturn(Collections.singletonList(animalId));

        presenter.setupFav();

        verify(view, times(1)).setIsFav(true);
    }

    @Test
    public void testSetupFavNegative() {
        when(preferencesService.getFavouriteList()).thenReturn(Collections.singletonList(wrongAnimalId));

        presenter.setupFav();

        verify(view, times(1)).setIsFav(false);
    }
}