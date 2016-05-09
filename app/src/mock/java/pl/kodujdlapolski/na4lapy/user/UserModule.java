package pl.kodujdlapolski.na4lapy.user;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    @Singleton
    @Provides
    public UserService provideUserService() {
        return new FakeUserService();
    }
}
