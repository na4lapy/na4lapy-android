package pl.kodujdlapolski.na4lapy;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
}
