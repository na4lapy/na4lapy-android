package pl.kodujdlapolski.na4lapy.ui.browse;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
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
import pl.kodujdlapolski.na4lapy.ui.browse.single.SingleBrowsePagerAdapter;
import pl.kodujdlapolski.na4lapy.ui.details.DetailsActivity;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Natalia on 2016-03-09.
 *
 * Modified by Marek Wojtuszkiewicz on 2016-04-06
 */

public class BrowsePresenter implements SynchronizationReceiver.SynchronizationReceiverCallback, OnBrowseElementClickedAction {


    public enum PageTypes {
        ALL(R.string.list_section_all, null),
        DOGS(R.string.list_section_dogs, Species.DOG),
        CATS(R.string.list_section_cats, Species.CAT),
        OTHER(R.string.list_section_other, Species.OTHER);
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

    public static final String ARG_PRESENTER = "argAction";
    private AbstractBrowseActivity abstractBrowseActivity;
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

    public BrowsePresenter(AbstractBrowseActivity abstractBrowseActivity, boolean isFavList, boolean isSingleBrowse) {
        this.abstractBrowseActivity = abstractBrowseActivity;
        this.isFavList = isFavList;
        this.isSingleBrowse = isSingleBrowse;
        ((Na4LapyApp) abstractBrowseActivity.getApplication()).getComponent().inject(this);
        synchronizationReceiver = new SynchronizationReceiver(this);

        animals = new ArrayList<>();
        if (isSingleBrowse) {
            abstractBrowseActivity.removeTabs();
        }
        adapter = isSingleBrowse ?
                new SingleBrowsePagerAdapter(animals, abstractBrowseActivity.getSupportFragmentManager(), this, abstractBrowseActivity.getViewPagerId())
                : new ListBrowsePagerAdapter(abstractBrowseActivity, animals, abstractBrowseActivity.getSupportFragmentManager(), this);

        startDownloadingData();
    }

    /**
     * as this is used only for methods invocations in fragments and for  repositoryService
     */
    public BrowsePresenter(Parcel in) {
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

    public void onActivityStart() {
        LocalBroadcastManager.getInstance(abstractBrowseActivity)
                .registerReceiver(synchronizationReceiver, SynchronizationReceiver.getIntentFilter());
    }

    public void onActivityStop() {
        LocalBroadcastManager.getInstance(abstractBrowseActivity).unregisterReceiver(synchronizationReceiver);
    }

    private void onAnimalsAvailable(List<Animal> animalsFromServer) {
        if (animalsFromServer != null) {
            animals.clear();
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
                if (a.getSpecies().equals(type.specie)) {
                    result.add(a);
                }
            }
        }
        return result;
    }

    private void onFavChanged(Long changedAnimalId) {
        repositoryService.getAnimal(changedAnimalId)
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onChangedAnimalAvailable);
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
        repositoryService.setFavourite(animal.getId(), !animal.getFavourite())
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onFavChanged);
    }

    @Override
    public void details(Animal animal) {
        abstractBrowseActivity.startActivity(new Intent(abstractBrowseActivity, DetailsActivity.class));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public BrowsePresenter createFromParcel(Parcel in) {
            return new BrowsePresenter(in);
        }

        public BrowsePresenter[] newArray(int size) {
            return new BrowsePresenter[size];
        }
    };

    public static BrowsePresenter init(Bundle arguments, Bundle savedInstanceState) {
        BrowsePresenter presenter = null;
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
        repositoryService.setFavourite(animalToUndo.getId(), !animalToUndo.getFavourite())
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onFavChanged);
    }
}
