package pl.kodujdlapolski.na4lapy.repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.kodujdlapolski.na4lapy.preferences.PreferencesService;
import pl.kodujdlapolski.na4lapy.repository.database.DatabaseRepository;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public RepositoryService provideRepositoryService(DatabaseRepository databaseRepository, PreferencesService preferencesService) {
        return new RepositoryServiceImpl(databaseRepository, preferencesService);
    }
}
