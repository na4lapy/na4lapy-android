package pl.kodujdlapolski.na4lapy.user;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.kodujdlapolski.na4lapy.preferences.PreferencesService;

@Module
public class UserModule {

    @Singleton
    @Provides
    public UserService provideUserService(PreferencesService preferencesService) {
        return new UserServiceImpl(preferencesService);
    }
}
