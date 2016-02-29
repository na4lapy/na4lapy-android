package pl.kodujdlapolski.na4lapy.repository;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.model.Shelter;

import static com.google.common.base.Preconditions.checkNotNull;

public class RepositoryServiceImpl implements RepositoryService {

    private AnimalRepository mAnimalRepository;
    private ShelterRepository mShelterRepository;

    @Inject
    public RepositoryServiceImpl(AnimalRepository animalRepository, ShelterRepository shelterRepository) {
        mAnimalRepository = checkNotNull(animalRepository, "AnimalRepository cannot be null");
        mShelterRepository = checkNotNull(shelterRepository, "ShelterRepository cannot be null");
    }

    @Override
    public void getAnimal(@NonNull Long id, @NonNull GetAnimalCallback callback) {
        checkNotNull(id, "id cannot be null");
        checkNotNull(callback, "callback cannot be null");
        callback.onAnimalLoaded(mAnimalRepository.findOne(id));
    }

    @Override
    public void getAnimalsByShelter(@NonNull Shelter shelter, @NonNull LoadAnimalsCallback callback) {
        checkNotNull(shelter, "shelter cannot be null");
        checkNotNull(callback, "callback cannot be null");
        callback.onAnimalsLoaded(mAnimalRepository.findAllByShelter(shelter));
    }

    @Override
    public void getShelter(@NonNull Long id, @NonNull GetShelterCallback callback) {
        checkNotNull(id, "id cannot be null");
        checkNotNull(callback, "callback cannot be null");
        callback.onShelterLoaded(mShelterRepository.findOne(id));
    }

    @Override
    public void getShelters(@NonNull LoadSheltersCallback callback) {
        checkNotNull(callback, "callback cannot be null");
        callback.onSheltersLoaded(mShelterRepository.findAll());
    }
}
