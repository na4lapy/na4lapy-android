package pl.kodujdlapolski.na4lapy.repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.kodujdlapolski.na4lapy.preferences.PreferencesService;
import pl.kodujdlapolski.na4lapy.repository.database.DatabaseRepository;
import pl.kodujdlapolski.na4lapy.user.UserService;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public RepositoryService provideRepositoryService(
            DatabaseRepository databaseRepository,
            PreferencesService preferencesService,
            UserService userService
    ) {
        return new RepositoryServiceImpl(databaseRepository, preferencesService, userService);
    }
}
