package pl.kodujdlapolski.na4lapy;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.LocalDate;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.service.api.AnimalDeserializer;
import pl.kodujdlapolski.na4lapy.service.api.LocalDateDeserializer;

@Module
public class Na4LapyModule {

    private Application mApplication;

    public Na4LapyModule(Application application) {
        mApplication = application;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return mApplication;
    }

    @Singleton
    @Provides
    public Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .registerTypeAdapter(Animal.class, new AnimalDeserializer(mApplication))
                .create();
    }
}
