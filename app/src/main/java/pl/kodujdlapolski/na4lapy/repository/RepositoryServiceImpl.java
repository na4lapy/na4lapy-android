package pl.kodujdlapolski.na4lapy.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.repository.database.DatabaseService;

import static com.google.common.base.Preconditions.checkNotNull;

public class RepositoryServiceImpl implements RepositoryService {

    private DatabaseService mDatabaseService;

    @Inject
    public RepositoryServiceImpl(DatabaseService databaseService) {
        mDatabaseService = checkNotNull(databaseService, "DatabaseService cannot be null");
    }

    @Override
    public void getAnimal(@NonNull Long id, @NonNull GetAnimalCallback callback) {
        checkNotNull(id, "id cannot be null");
        checkNotNull(callback, "callback cannot be null");
        Animal animal = null;
        try {
            animal = mDatabaseService.findOneById(id, Animal.class);
        } catch (SQLException e) {
            Log.w(getClass().getSimpleName(), e);
        }
        callback.onAnimalLoaded(animal);
    }

    @Override
    public void getAnimalsByShelterId(@NonNull Long shelterId, @NonNull LoadAnimalsCallback callback) {
        checkNotNull(shelterId, "shelterId cannot be null");
        checkNotNull(callback, "callback cannot be null");
        List<Animal> animals = null;
        try {
            animals = mDatabaseService.findAllByForeignId(shelterId, Animal.class, Shelter.class);
        } catch (SQLException e) {
            Log.w(getClass().getSimpleName(), e);
        }
        callback.onAnimalsLoaded(animals);
    }

    @Override
    public void getShelter(@NonNull Long id, @NonNull GetShelterCallback callback) {
        checkNotNull(id, "id cannot be null");
        checkNotNull(callback, "callback cannot be null");
        Shelter shelter = null;
        try {
            shelter = mDatabaseService.findOneById(id, Shelter.class);
        } catch (SQLException e) {
            Log.w(getClass().getSimpleName(), e);
        }
        callback.onShelterLoaded(shelter);
    }

    @Override
    public void getShelters(@NonNull LoadSheltersCallback callback) {
        checkNotNull(callback, "callback cannot be null");
        List<Shelter> shelters = null;
        try {
            shelters = mDatabaseService.findAll(Shelter.class);
        } catch (SQLException e) {
            Log.w(getClass().getSimpleName(), e);
        }
        callback.onSheltersLoaded(shelters);
    }
}
