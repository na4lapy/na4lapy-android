package pl.kodujdlapolski.na4lapy.repository.database;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    public DatabaseService provideDatabaseService(DatabaseHelper databaseHelper) {
        return new DatabaseServiceImpl(databaseHelper);
    }

    @Singleton
    @Provides
    public DatabaseHelper provideDatabaseHelper(Application application) {
        return new DatabaseHelper(application);
    }
}
