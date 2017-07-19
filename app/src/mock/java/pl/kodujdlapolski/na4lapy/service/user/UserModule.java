package pl.kodujdlapolski.na4lapy.service.user;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.kodujdlapolski.na4lapy.service.preferences.PreferencesService;

@Module
public class UserModule {

    @Singleton
    @Provides
    public UserService provideUserService(PreferencesService preferencesService) {
        return new FakeUserService();
    }
}
