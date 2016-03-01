package pl.kodujdlapolski.na4lapy.repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.kodujdlapolski.na4lapy.repository.database.DatabaseService;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public RepositoryService provideRepositoryService(DatabaseService databaseService) {
        return new RepositoryServiceImpl(databaseService);
    }
}
