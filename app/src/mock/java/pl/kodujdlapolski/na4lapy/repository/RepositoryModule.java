package pl.kodujdlapolski.na4lapy.repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public RepositoryService provideRepositoryService(AnimalRepository animalRepository, ShelterRepository shelterRepository) {
        return new RepositoryServiceImpl(animalRepository, shelterRepository);
    }

    @Singleton
    @Provides
    public AnimalRepository provideAnimalRepository() {
        return new FakeAnimalRepositoryImpl();
    }

    @Singleton
    @Provides
    public ShelterRepository provideShelterRepository() {
        return new FakeShelterRepositoryImpl();
    }
}
