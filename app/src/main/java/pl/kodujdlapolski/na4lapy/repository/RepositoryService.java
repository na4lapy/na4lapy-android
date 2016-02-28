package pl.kodujdlapolski.na4lapy.repository;

import android.support.annotation.NonNull;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Shelter;

public interface RepositoryService {
    interface GetAnimalCallback {
        void onAnimalLoaded(Animal animal);
    }
    interface LoadAnimalsCallback {
        void onAnimalsLoaded(List<Animal> animals);
    }
    interface GetShelterCallback {
        void onShelterLoaded(Shelter shelter);
    }
    interface LoadSheltersCallback {
        void onSheltersLoaded(List<Shelter> shelters);
    }

    void getAnimal(@NonNull Long id, @NonNull GetAnimalCallback callback);
    void getAnimalsByShelter(@NonNull Shelter shelter, @NonNull LoadAnimalsCallback callback);

    void getShelter(@NonNull Long id, @NonNull GetShelterCallback callback);
    void getShelters(@NonNull LoadSheltersCallback callback);
}
