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

import pl.kodujdlapolski.na4lapy.service.preferences.PreferencesService;
import pl.kodujdlapolski.na4lapy.service.repository.RepositoryService;

public class AnimalDetailsPresenter {

    private RepositoryService repositoryService;
    private PreferencesService preferencesService;
    private AnimalDetailsContract.View view;
    private Long animalId;

    AnimalDetailsPresenter(AnimalDetailsContract.View view, Long animalId, RepositoryService repositoryService, PreferencesService preferencesService) {
        this.view = view;
        this.animalId = animalId;
        this.repositoryService = repositoryService;
        this.preferencesService = preferencesService;
    }

    public void setupBasicData() {
        view.showLoading(true);
        repositoryService.getAnimal(animalId).subscribe(animal -> {
            view.populateView(animal);
            view.showLoading(false);
        });
    }

    public void setupFav() {
        view.setIsFav(preferencesService.getFavouriteList().contains(animalId));
    }
}
