package pl.kodujdlapolski.na4lapy.repository;

import android.support.annotation.NonNull;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import rx.Observable;

public interface RepositoryService {
    Observable<Animal> getAnimal(@NonNull Long id);
    Observable<List<Animal>> getAnimals();

    Observable<List<Animal>> getAnimalsByShelterId(@NonNull Long shelterId);
    Observable<List<Animal>> getAnimalsByFavourite();

    Observable<Long> setFavourite(@NonNull Long id, boolean favourite);

    Observable<Shelter> getShelter(@NonNull Long id);
    Observable<List<Shelter>> getShelters();
}
