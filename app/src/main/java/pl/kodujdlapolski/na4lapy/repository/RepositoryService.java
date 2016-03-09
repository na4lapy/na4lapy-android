package pl.kodujdlapolski.na4lapy.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Shelter;

public interface RepositoryService {
    interface GetAnimalCallback {
        void onAnimalLoaded(@Nullable Animal animal);
    }
    interface LoadAnimalsCallback {
        void onAnimalsLoaded(@Nullable List<Animal> animals);
    }
    interface GetShelterCallback {
        void onShelterLoaded(@Nullable Shelter shelter);
    }
    interface LoadSheltersCallback {
        void onSheltersLoaded(@Nullable List<Shelter> shelters);
    }

    void getAnimal(@NonNull Long id, @NonNull GetAnimalCallback callback);
    void getAnimals(@NonNull LoadAnimalsCallback callback);
    void getAnimalsByShelterId(@NonNull Long shelterId, @NonNull LoadAnimalsCallback callback);
    void getAnimalsByFavourite(@NonNull LoadAnimalsCallback callback);

    void getShelter(@NonNull Long id, @NonNull GetShelterCallback callback);
    void getShelters(@NonNull LoadSheltersCallback callback);
}
