package pl.kodujdlapolski.na4lapy.repository;

import android.support.annotation.NonNull;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.preferences.PreferencesService;
import pl.kodujdlapolski.na4lapy.repository.database.DatabaseRepository;
import rx.Observable;
import rx.Subscriber;

import static com.google.common.base.Preconditions.checkNotNull;

public class RepositoryServiceImpl implements RepositoryService {

    private DatabaseRepository mDatabaseRepository;
    private PreferencesService mPreferencesService;

    @Inject
    public RepositoryServiceImpl(DatabaseRepository databaseRepository, PreferencesService preferencesService) {
        mDatabaseRepository = checkNotNull(databaseRepository, "DatabaseRepository cannot be null");
        mPreferencesService = checkNotNull(preferencesService, "PreferencesService cannot be null");
    }

    @Override
    public Observable<Animal> getAnimal(@NonNull Long id) {
        checkNotNull(id, "id cannot be null");
        return Observable.create(new Observable.OnSubscribe<Animal>() {
            @Override
            public void call(Subscriber<? super Animal> subscriber) {
                try {
                    Animal animal = mDatabaseRepository.findOneById(id, Animal.class);
                    subscriber.onNext(animal);
                    subscriber.onCompleted();
                } catch (SQLException e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<List<Animal>> getAnimals() {
        return Observable.create(new Observable.OnSubscribe<List<Animal>>() {
            @Override
            public void call(Subscriber<? super List<Animal>> subscriber) {
                try {
                    List<Animal> animals = mDatabaseRepository.findAll(Animal.class);
                    subscriber.onNext(animals);
                    subscriber.onCompleted();
                } catch (SQLException e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<List<Animal>> getAnimalsByShelterId(@NonNull Long shelterId) {
        checkNotNull(shelterId, "shelterId cannot be null");
        return Observable.create(new Observable.OnSubscribe<List<Animal>>() {
            @Override
            public void call(Subscriber<? super List<Animal>> subscriber) {
                try {
                    List<Animal> animals = mDatabaseRepository.findAllByForeignId(shelterId, Animal.class, Shelter.class);
                    subscriber.onNext(animals);
                    subscriber.onCompleted();
                } catch (SQLException e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<List<Animal>> getAnimalsByFavourite() {
        return Observable.create(new Observable.OnSubscribe<List<Animal>>() {
            @Override
            public void call(Subscriber<? super List<Animal>> subscriber) {
                try {
                    List<Animal> animals = mDatabaseRepository.findAllByIdList(mPreferencesService.getFavouriteList(), Animal.class);
                    subscriber.onNext(animals);
                    subscriber.onCompleted();
                } catch (SQLException e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Long> setFavourite(@NonNull Long id, boolean favourite) {
        checkNotNull(id, "id cannot be null");
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                try {
                    Animal animal = mDatabaseRepository.findOneById(id, Animal.class);
                    animal.setFavourite(favourite);
                    mDatabaseRepository.save(animal);
                    subscriber.onNext(animal.getId());
                    subscriber.onCompleted();
                } catch (SQLException e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Shelter> getShelter(@NonNull Long id) {
        checkNotNull(id, "id cannot be null");
        return Observable.create(new Observable.OnSubscribe<Shelter>() {
            @Override
            public void call(Subscriber<? super Shelter> subscriber) {
                try {
                    Shelter shelter = mDatabaseRepository.findOneById(id, Shelter.class);
                    subscriber.onNext(shelter);
                    subscriber.onCompleted();
                } catch (SQLException e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<List<Shelter>> getShelters() {
        return Observable.create(new Observable.OnSubscribe<List<Shelter>>() {
            @Override
            public void call(Subscriber<? super List<Shelter>> subscriber) {
                try {
                    List<Shelter> shelters = mDatabaseRepository.findAll(Shelter.class);
                    subscriber.onNext(shelters);
                    subscriber.onCompleted();
                } catch (SQLException e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
