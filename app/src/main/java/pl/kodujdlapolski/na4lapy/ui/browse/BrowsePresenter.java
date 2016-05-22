package pl.kodujdlapolski.na4lapy.ui.browse;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.Na4LapyApp;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.type.Species;
import pl.kodujdlapolski.na4lapy.repository.RepositoryService;
import pl.kodujdlapolski.na4lapy.sync.SynchronizationService;
import pl.kodujdlapolski.na4lapy.sync.receiver.SynchronizationReceiver;
import pl.kodujdlapolski.na4lapy.ui.browse.list.ListBrowsePagerAdapter;
import pl.kodujdlapolski.na4lapy.ui.details.DetailsActivity;
import pl.kodujdlapolski.na4lapy.user.UserService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Natalia on 2016-03-09.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * Modified by Marek Wojtuszkiewicz on 2016-04-06
 */

public class BrowsePresenter implements SynchronizationReceiver.SynchronizationReceiverCallback, BrowseContract.Presenter {

    private BrowseContract.View view;
    @Inject
    SynchronizationService synchronizationService;
    @Inject
    RepositoryService repositoryService;
    @Inject
    UserService userService;
    private SynchronizationReceiver synchronizationReceiver;
    private boolean isAfterSynchronization = false;
    private List<Animal> animals;
    private boolean isFavList;

    public BrowsePresenter(BrowseContract.View view, boolean isFavList) {
        this.view = view;
        this.isFavList = isFavList;
        ((Na4LapyApp) view.getActivity().getApplication()).getComponent().inject(this);
        synchronizationReceiver = new SynchronizationReceiver(this);
        animals = new ArrayList<>();
        startDownloadingData();
    }

    @Override
    public void startDownloadingData() {
        view.showProgressHideContent(true);
        isAfterSynchronization = false;
        getData();
        synchronizationService.synchronize();
    }

    @Override
    public void onSynchronizationSuccess() {
        if (view.isAlive()) {
            isAfterSynchronization = true;
            getData();
        }
    }

    @Override
    public void onSynchronizationFail() {
        if (animals.isEmpty() && view.isAlive()) {
            view.showError();
        }
    }

    @Override
    public void favourite(Animal animal) {
        if (Boolean.TRUE.equals(animal.getFavourite())) {
            animal.setFavourite(false);
            userService.removeFromFavourite(animal);
        } else {
            animal.setFavourite(true);
            userService.addToFavourite(animal);
        }
    }

    @Override
    public void details(Animal animal) {
        Intent i = new Intent(view.getActivity(), DetailsActivity.class);
        i.putExtra(DetailsActivity.EXTRA_ANIMAL_ID, animal.getId());
        view.getActivity().startActivityForResult(i, DetailsActivity.REQUEST_CODE_ANIMAL);
    }

    @Override
    public List<Animal> getAnimals() {
        return animals;
    }

    @Override
    public BroadcastReceiver getSynchronizationReceiver() {
        return synchronizationReceiver;
    }

    @Override
    public void onChangedAnimalAvailable(Long changedAnimalId) {
        repositoryService.getAnimal(changedAnimalId)
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onChangedAnimalAvailable);
    }

    public static int getIndexOfAnimalOnList(List<Animal> animals, Animal toFind) {
        int indexOfChangedAnimal = -1;
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getId().equals(toFind.getId()))
                indexOfChangedAnimal = i;
        }
        return indexOfChangedAnimal;
    }

    @Override
    public void handleUndoAnimal(Animal animalToUndo) {
        repositoryService.setFavourite(animalToUndo.getId(), !Boolean.TRUE.equals(animalToUndo.getFavourite()))
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onFavChanged);
    }

    @Override
    public UserService getUserService() {
        return userService;
    }

    public static List<Animal> getAnimalsByType(List<Animal> animals, PageTypes type) {
        ArrayList<Animal> result = new ArrayList<>();
        if (type.specie == null) {
            result.addAll(animals);
        } else {
            for (Animal a : animals) {
                if (a.getSpecies() != null && a.getSpecies().equals(type.specie)) {
                    result.add(a);
                }
            }
        }
        return result;
    }

    private void onChangedAnimalAvailable(Animal changedAnimal) {
        int indexWhichShouldBeReplaced = getIndexOfAnimalOnList(animals, changedAnimal);
        if (indexWhichShouldBeReplaced != -1) {
            if (isFavList && !Boolean.TRUE.equals(changedAnimal.getFavourite())) {
                animals.remove(indexWhichShouldBeReplaced);
                view.getAdapter().notifyItemRemoved(changedAnimal);
            } else {
                animals.set(indexWhichShouldBeReplaced, changedAnimal);
                view.getAdapter().notifyItemChanged(changedAnimal);
            }
        } else {
            animals.add(changedAnimal);
            view.getAdapter().notifyDataSetChanged();
        }
    }


    private void onAnimalsAvailable(List<Animal> animalsFromServer) {
        if (animalsFromServer != null) {
            animals.clear();
            view.getAdapter().notifyDataSetChanged();
            animals.addAll(animalsFromServer);
            view.getAdapter().notifyDataSetChanged();
            view.showProgressHideContent(false);
        } else {
            if (isAfterSynchronization) {
                view.showError();
            } else {
                view.showProgressHideContent(true);
            }
        }
    }

    private void getData() {
        if (isFavList) {
            repositoryService.getAnimalsByFavourite()
                    .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onAnimalsAvailable);
        } else {
            repositoryService.getAnimals()
                    .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onAnimalsAvailable);
        }
    }


    private void onFavChanged(Long changedAnimalId) {
        repositoryService.getAnimal(changedAnimalId)
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onChangedAnimalAvailable);
    }
}
