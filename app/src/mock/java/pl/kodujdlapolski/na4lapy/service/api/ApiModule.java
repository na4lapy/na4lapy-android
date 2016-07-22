package pl.kodujdlapolski.na4lapy.service.api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.kodujdlapolski.na4lapy.service.repository.database.DatabaseHelper;

@Module
public class ApiModule {

    @Singleton
    @Provides
    public ApiService provideApiService(DatabaseHelper databaseHelper) {
        return new FakeApiServiceImpl(databaseHelper);
    }
}
