package pl.kodujdlapolski.na4lapy.sync;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.kodujdlapolski.na4lapy.api.ApiService;
import pl.kodujdlapolski.na4lapy.repository.database.DatabaseRepository;

@Module
public class SynchronizationModule {

    @Singleton
    @Provides
    public SynchronizationService provideSynchronizationService(ApiService apiService, DatabaseRepository databaseRepository) {
        return new SynchronizationServiceImpl(apiService, databaseRepository);
    }
}
