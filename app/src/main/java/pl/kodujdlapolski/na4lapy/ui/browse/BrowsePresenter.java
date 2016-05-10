package pl.kodujdlapolski.na4lapy.ui.browse;

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
import pl.kodujdlapolski.na4lapy.ui.NotLoggedDialog;
import pl.kodujdlapolski.na4lapy.ui.browse.list.ListBrowsePagerAdapter;
import pl.kodujdlapolski.na4lapy.ui.browse.single.SingleBrowsePagerAdapter;
import pl.kodujdlapolski.na4lapy.ui.details.DetailsActivity;
import pl.kodujdlapolski.na4lapy.user.UserModule;
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

public class BrowsePresenter implements SynchronizationReceiver.SynchronizationReceiverCallback, OnBrowseElementClickedAction {

    public enum PageTypes {
        ALL(R.string.list_section_all, null),
        DOGS(Species.DOG.getLabelResId(), Species.DOG),
        CATS(Species.CAT.getLabelResId(), Species.CAT),
        OTHER(Species.OTHER.getLabelResId(), Species.OTHER);
        public int nameResId;
        Species specie;

        PageTypes(int nameResId, Species specie) {
            this.nameResId = nameResId;
            this.specie = specie;
        }
    }

    public interface UpdateSingleElement {
        void notifyItemChanged(Animal animal);

        void notifyItemRemoved(Animal animal);
    }

    private AbstractBrowseActivity abstractBrowseActivity;
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
    private FragmentPagerAdapter adapter;

    public BrowsePresenter(AbstractBrowseActivity abstractBrowseActivity, boolean isFavList, boolean isSingleBrowse) {
        this.abstractBrowseActivity = abstractBrowseActivity;
        this.isFavList = isFavList;
        ((Na4LapyApp) abstractBrowseActivity.getApplication()).getComponent().inject(this);
        synchronizationReceiver = new SynchronizationReceiver(this);

        animals = new ArrayList<>();
        if (isSingleBrowse) {
            abstractBrowseActivity.removeTabs();
        }
        adapter = isSingleBrowse ?
                new SingleBrowsePagerAdapter(animals, abstractBrowseActivity.getSupportFragmentManager(), abstractBrowseActivity.getViewPagerId())
                : new ListBrowsePagerAdapter(abstractBrowseActivity, animals, abstractBrowseActivity.getSupportFragmentManager(), this);
        startDownloadingData();

    }

    public void startDownloadingData() {
        abstractBrowseActivity.showProgressHideContent(true);
        isAfterSynchronization = false;
        getData();
        synchronizationService.synchronize();
    }

    @Override
    public void onSynchronizationSuccess() {
        if (abstractBrowseActivity.isAlive()) {
            isAfterSynchronization = true;
            getData();
        }
    }

    @Override
    public void onSynchronizationFail() {
        if (animals.isEmpty() && abstractBrowseActivity.isAlive()) {
            abstractBrowseActivity.showError();
        }
    }

    public void onActivityStart(AbstractBrowseActivity abstractBrowseActivity) {
        this.abstractBrowseActivity = abstractBrowseActivity;
        LocalBroadcastManager.getInstance(this.abstractBrowseActivity)
                .registerReceiver(synchronizationReceiver, SynchronizationReceiver.getIntentFilter());
    }

    public void onActivityStop() {
        LocalBroadcastManager.getInstance(abstractBrowseActivity).unregisterReceiver(synchronizationReceiver);
    }

    private void onAnimalsAvailable(List<Animal> animalsFromServer) {
        if (animalsFromServer != null) {
            animals.clear();
            adapter.notifyDataSetChanged();
            animals.addAll(animalsFromServer);
            adapter.notifyDataSetChanged();
            abstractBrowseActivity.showProgressHideContent(false);
        } else {
            if (isAfterSynchronization) {
                abstractBrowseActivity.showError();
            } else {
                abstractBrowseActivity.showProgressHideContent(true);
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

    public FragmentPagerAdapter getAdapter() {
        return adapter;
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

    public static int getIndexOfAnimalOnList(List<Animal> animals, Animal toFind) {
        int indexOfChangedAnimal = -1;
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getId().equals(toFind.getId()))
                indexOfChangedAnimal = i;
        }
        return indexOfChangedAnimal;
    }

    public void handleUndoAnimal(Animal animalToUndo) {
        repositoryService.setFavourite(animalToUndo.getId(), !Boolean.TRUE.equals(animalToUndo.getFavourite()))
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onFavChanged);
    }

    private void onFavChanged(Long changedAnimalId) {
        repositoryService.getAnimal(changedAnimalId)
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onChangedAnimalAvailable);
    }

    public void onChangedAnimalAvailable(Long changedAnimalId) {
        repositoryService.getAnimal(changedAnimalId)
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onChangedAnimalAvailable);
    }

    public void onChangedAnimalAvailable(Animal changedAnimal) {
        int indexWhichShouldBeReplaced = getIndexOfAnimalOnList(animals, changedAnimal);
        if (indexWhichShouldBeReplaced != -1) {
            if (isFavList && !Boolean.TRUE.equals(changedAnimal.getFavourite())) {
                animals.remove(indexWhichShouldBeReplaced);
                ((UpdateSingleElement) adapter).notifyItemRemoved(changedAnimal);
            } else {
                animals.set(indexWhichShouldBeReplaced, changedAnimal);
                ((UpdateSingleElement) adapter).notifyItemChanged(changedAnimal);
            }
        } else {
            animals.add(changedAnimal);
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void favourite(Animal animal) {
        if (userService.isLogged())
            repositoryService.setFavourite(animal.getId(), !Boolean.TRUE.equals(animal.getFavourite()))
                    .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onFavChanged);
        else
            NotLoggedDialog.get(abstractBrowseActivity).show();

    }

    @Override
    public void details(Animal animal) {
        Intent i = new Intent(abstractBrowseActivity, DetailsActivity.class);
        i.putExtra(DetailsActivity.EXTRA_ANIMAL_ID, animal.getId());
        abstractBrowseActivity.startActivityForResult(i, DetailsActivity.REQUEST_CODE_ANIMAL);
    }
}
