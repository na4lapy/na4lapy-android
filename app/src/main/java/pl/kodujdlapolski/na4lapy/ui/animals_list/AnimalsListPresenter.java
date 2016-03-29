package pl.kodujdlapolski.na4lapy.ui.animals_list;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

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
import pl.kodujdlapolski.na4lapy.ui.details.DetailsActivity;

/**
 * Created by Natalia on 2016-03-09.
 */

public class AnimalsListPresenter implements SynchronizationReceiver.SynchronizationReceiverCallback, OnClickedAction {


    public enum PageTypes {
        ALL(R.string.list_section_all, null),
        DOGS(R.string.list_section_dogs, Species.DOG),
        CATS(R.string.list_section_cats, Species.CAT),
        OTHER(R.string.list_section_other, Species.OTHER);
        int nameResId;
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

    public static final String ARG_PRESENTER = "argAction";
    private AnimalsListActivity animalsListActivity;
    @Inject
    SynchronizationService synchronizationService;
    @Inject
    RepositoryService repositoryService;
    private SynchronizationReceiver synchronizationReceiver;
    private boolean isAfterSynchronization = false;
    private List<Animal> animals;
    private boolean isFavList;
    private boolean isSingleBrowse;
    private FragmentPagerAdapter adapter;

    public AnimalsListPresenter(AnimalsListActivity animalsListActivity, boolean isFavList, boolean isSingleBrowse) {
        this.animalsListActivity = animalsListActivity;
        this.isFavList = isFavList;
        this.isSingleBrowse = isSingleBrowse;
        ((Na4LapyApp) animalsListActivity.getApplication()).getComponent().inject(this);
        synchronizationReceiver = new SynchronizationReceiver(this);

        animals = new ArrayList<>();
        if (isSingleBrowse) {
            animalsListActivity.removeTabs();
        }
        adapter = isSingleBrowse ?
                new AnimalsSingleBrowsePagerAdapter(animals, animalsListActivity.getSupportFragmentManager(), this, animalsListActivity.getViewPagerId())
                : new AnimalsPagerAdapter(animalsListActivity, animals, animalsListActivity.getSupportFragmentManager(), this);

        startDownloadingData();
    }

    /**
     * as this is used only for methods invocations in fragments and for  repositoryService
     */
    public AnimalsListPresenter(Parcel in) {
    }

    public void startDownloadingData() {
        animalsListActivity.showProgressHideContent(true);
        isAfterSynchronization = false;
        getData();
        synchronizationService.synchronize();
    }

    @Override
    public void onSynchronizationSuccess() {
        if (animalsListActivity.isAlive()) {
            isAfterSynchronization = true;
            getData();
        }
    }

    @Override
    public void onSynchronizationFail() {
        if (animals.isEmpty() && animalsListActivity.isAlive()) {
            animalsListActivity.showError();
        }
    }

    public void onActivityStart() {
        LocalBroadcastManager.getInstance(animalsListActivity)
                .registerReceiver(synchronizationReceiver, SynchronizationReceiver.getIntentFilter());
    }

    public void onActivityStop() {
        LocalBroadcastManager.getInstance(animalsListActivity).unregisterReceiver(synchronizationReceiver);
    }

    private void onAnimalsAvailable(List<Animal> animalsFromServer) {
        if (animalsFromServer != null) {
            animals.clear();
            animals.addAll(animalsFromServer);
            adapter.notifyDataSetChanged();
            animalsListActivity.showProgressHideContent(false);
        } else {
            if (isAfterSynchronization) {
                animalsListActivity.showError();
            } else {
                animalsListActivity.showProgressHideContent(true);
            }
        }
    }

    private void getData() {
        if (isFavList) {
            repositoryService.getAnimalsByFavourite().subscribe(this::onAnimalsAvailable);
        } else {
            repositoryService.getAnimals().subscribe(this::onAnimalsAvailable);
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
                if (a.getSpecies().equals(type.specie)) {
                    result.add(a);
                }
            }
        }
        return result;
    }

    private void onFavChanged(Long changedAnimalId) {
        repositoryService.getAnimal(changedAnimalId).subscribe(this::onChangedAnimalAvailable);
    }

    private void onChangedAnimalAvailable(Animal changedAnimal) {
        int indexWhichShouldBeReplaced = getIndexOfAnimalOnList(animals, changedAnimal);
        if (indexWhichShouldBeReplaced != -1) {
            if (isFavList && !changedAnimal.isFavourite()) {
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
        repositoryService.setFavourite(animal.getId(), !animal.getFavourite()).subscribe(this::onFavChanged);
    }

    @Override
    public void details(Animal animal) {
        animalsListActivity.startActivity(new Intent(animalsListActivity, DetailsActivity.class));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public AnimalsListPresenter createFromParcel(Parcel in) {
            return new AnimalsListPresenter(in);
        }

        public AnimalsListPresenter[] newArray(int size) {
            return new AnimalsListPresenter[size];
        }
    };

    public static AnimalsListPresenter init(Bundle arguments, Bundle savedInstanceState) {
        AnimalsListPresenter presenter = null;
        if (arguments != null) {
            presenter = arguments.getParcelable(ARG_PRESENTER);
        }
        if (presenter == null && savedInstanceState != null) {
            presenter = savedInstanceState.getParcelable(ARG_PRESENTER);
        }
        return presenter;
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
        repositoryService.setFavourite(animalToUndo.getId(), !animalToUndo.getFavourite()).subscribe(this::onFavChanged);
    }
}
