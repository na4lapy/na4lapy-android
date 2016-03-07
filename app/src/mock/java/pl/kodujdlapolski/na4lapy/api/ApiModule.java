package pl.kodujdlapolski.na4lapy.api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule {

    @Singleton
    @Provides
    public ApiService provideApiService() {
        return new FakeApiServiceImpl();
    }
}
