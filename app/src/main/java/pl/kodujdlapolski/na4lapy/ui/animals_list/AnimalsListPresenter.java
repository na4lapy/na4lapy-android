package pl.kodujdlapolski.na4lapy.ui.animals_list;

import android.support.annotation.Nullable;
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

/**
 * Created by Natalia on 2016-03-09.
 */
public class AnimalsListPresenter implements SynchronizationReceiver.SynchronizationReceiverCallback {
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

    private AnimalsListActivity animalsListActivity;
    @Inject
    SynchronizationService synchronizationService;
    @Inject
    RepositoryService repositoryService;
    private SynchronizationReceiver synchronizationReceiver;
    private boolean isAfterSynchronization = false;
    private List<Animal> animals;
    private boolean isFavList;

    RepositoryService.LoadAnimalsCallback loadAnimalsCallback = new RepositoryService.LoadAnimalsCallback() {
        @Override
        public void onAnimalsLoaded(@Nullable List<Animal> animals) {
            AnimalsListPresenter.this.animals = animals;
            onAnimalsAvailable();
        }
    };

    public AnimalsListPresenter(AnimalsListActivity animalsListActivity, boolean isFavList) {
        this.animalsListActivity = animalsListActivity;
        this.isFavList = isFavList;
        ((Na4LapyApp) animalsListActivity.getApplication()).getComponent().inject(this);
        synchronizationReceiver = new SynchronizationReceiver(this);
        startDownloadingData();
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
        if (animals == null && animalsListActivity.isAlive()) {
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

    private void onAnimalsAvailable() {
        if (animals != null) {
            animalsListActivity.updateAnimals(animals);
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
            repositoryService.getAnimalsByFavourite(loadAnimalsCallback);
        } else {
            // todo get all animals
//            repositoryService.getAnimalsByShelterId(loadAnimalsCallback);
        }
    }

    public static ArrayList<Animal> getAnimalsByType(ArrayList<Animal> animals, PageTypes type) {
        ArrayList<Animal> result = new ArrayList<>();
        if (type.specie == null) {
            return animals;
        }
        for (Animal a : animals) {
            if (a.getSpecies().equals(type.specie)) {
                result.add(a);
            }
        }
        return result;
    }
}
