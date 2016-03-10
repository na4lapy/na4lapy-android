package pl.kodujdlapolski.na4lapy.preferences;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PreferencesModule {

    @Provides
    @Singleton
    public PreferencesService providePreferencesService(SharedPreferences sharedPreferences, Gson gson) {
        return new PreferencesServiceImpl(sharedPreferences, gson);
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Singleton
    @Provides
    public Gson provideGson() {
        return new Gson();
    }
}
