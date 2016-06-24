package pl.kodujdlapolski.na4lapy.service.repository.database;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    public DatabaseRepository provideDatabaseRepository(DatabaseHelper databaseHelper) {
        return new DatabaseRepositoryImpl(databaseHelper);
    }

    @Singleton
    @Provides
    public DatabaseHelper provideDatabaseHelper(Application application) {
        return new DatabaseHelper(application);
    }
}
