package pl.kodujdlapolski.na4lapy.repository;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.common.collect.Maps;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.repository.database.DatabaseRepository;

import static com.google.common.base.Preconditions.checkNotNull;

public class RepositoryServiceImpl implements RepositoryService {

    private DatabaseRepository mDatabaseRepository;

    @Inject
    public RepositoryServiceImpl(DatabaseRepository databaseRepository) {
        mDatabaseRepository = checkNotNull(databaseRepository, "DatabaseRepository cannot be null");
    }

    @Override
    public void getAnimal(@NonNull Long id, @NonNull final GetAnimalCallback callback) {
        checkNotNull(id, "id cannot be null");
        checkNotNull(callback, "callback cannot be null");

        new AsyncTask<Long, Void, Animal>() {
            @Override
            protected Animal doInBackground(Long... params) {
                try {
                    return mDatabaseRepository.findOneById(params[0], Animal.class);
                } catch (SQLException e) {
                    Log.w(getClass().getSimpleName(), e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Animal result) {
                callback.onAnimalLoaded(result);
            }
        }.execute(id);
    }

    @Override
    public void getAnimals(@NonNull final LoadAnimalsCallback callback) {
        checkNotNull(callback, "callback cannot be null");
        new AsyncTask<Void, Void, List<Animal>>() {
            @Override
            protected List<Animal> doInBackground(Void... params) {
                try {
                    return mDatabaseRepository.findAll(Animal.class);
                } catch (SQLException e) {
                    Log.w(getClass().getSimpleName(), e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<Animal> result) {
                callback.onAnimalsLoaded(result);
            }
        }.execute();
    }

    @Override
    public void getAnimalsByShelterId(@NonNull Long shelterId, @NonNull final LoadAnimalsCallback callback) {
        checkNotNull(shelterId, "shelterId cannot be null");
        checkNotNull(callback, "callback cannot be null");

        new AsyncTask<Long, Void, List<Animal>>() {
            @Override
            protected List<Animal> doInBackground(Long... params) {
                try {
                    return mDatabaseRepository.findAllByForeignId(params[0], Animal.class, Shelter.class);
                } catch (SQLException e) {
                    Log.w(getClass().getSimpleName(), e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<Animal> result) {
                callback.onAnimalsLoaded(result);
            }
        }.execute(shelterId);
    }

    @Override
    public void getAnimalsByFavourite(@NonNull final LoadAnimalsCallback callback) {
        checkNotNull(callback, "callback cannot be null");

        new AsyncTask<Void, Void, List<Animal>>() {
            @Override
            protected List<Animal> doInBackground(Void... params) {
                Map<String, Object> favouriteField = Maps.newHashMap();
                favouriteField.put(Animal.COLUMN_NAME_FAVOURITE, true);
                try {
                    return mDatabaseRepository.findAllByFields(favouriteField, Animal.class);
                } catch (SQLException e) {
                    Log.w(getClass().getSimpleName(), e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<Animal> result) {
                callback.onAnimalsLoaded(result);
            }
        }.execute();
    }

    @Override
    public void setFavourite(@NonNull Long id, final boolean favourite) {
        checkNotNull(id, "id cannot be null");

        new AsyncTask<Long, Void, Integer>() {
            @Override
            protected Integer doInBackground(Long... params) {
                try {
                    Animal animal = mDatabaseRepository.findOneById(params[0], Animal.class);
                    animal.setFavourite(favourite);
                    return mDatabaseRepository.save(animal);
                } catch (SQLException e) {
                    Log.w(getClass().getSimpleName(), e);
                }
                return null;
            }
        }.execute(id);
    }

    @Override
    public void getShelter(@NonNull Long id, @NonNull final GetShelterCallback callback) {
        checkNotNull(id, "id cannot be null");
        checkNotNull(callback, "callback cannot be null");

        new AsyncTask<Long, Void, Shelter>() {
            @Override
            protected Shelter doInBackground(Long... params) {
                try {
                    return mDatabaseRepository.findOneById(params[0], Shelter.class);
                } catch (SQLException e) {
                    Log.w(getClass().getSimpleName(), e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Shelter result) {
                callback.onShelterLoaded(result);
            }
        }.execute(id);
    }

    @Override
    public void getShelters(@NonNull final LoadSheltersCallback callback) {
        checkNotNull(callback, "callback cannot be null");

        new AsyncTask<Void, Void, List<Shelter>>() {
            @Override
            protected List<Shelter> doInBackground(Void... params) {
                try {
                    return mDatabaseRepository.findAll(Shelter.class);
                } catch (SQLException e) {
                    Log.w(getClass().getSimpleName(), e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<Shelter> result) {
                callback.onSheltersLoaded(result);
            }
        }.execute();
    }
}
