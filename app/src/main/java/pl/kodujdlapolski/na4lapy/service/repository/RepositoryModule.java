package pl.kodujdlapolski.na4lapy.service.repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.kodujdlapolski.na4lapy.service.api.ApiService;
import pl.kodujdlapolski.na4lapy.service.preferences.PreferencesService;
import pl.kodujdlapolski.na4lapy.service.repository.database.DatabaseRepository;
import pl.kodujdlapolski.na4lapy.service.user.UserService;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public RepositoryService provideRepositoryService(
            ApiService apiService, DatabaseRepository databaseRepository,
            PreferencesService preferencesService, UserService userService
    ) {
        return new RepositoryServiceImpl(apiService, databaseRepository, preferencesService, userService);
    }
}
