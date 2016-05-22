package pl.kodujdlapolski.na4lapy.system;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SystemModule {

    @Singleton
    @Provides
    public SystemService provideSystemService(Application application) {
        return new SystemServiceImpl(application);
    }
}
