package pl.kodujdlapolski.na4lapy;

import javax.inject.Singleton;

import dagger.Component;
import pl.kodujdlapolski.na4lapy.activities.MainActivity;
import pl.kodujdlapolski.na4lapy.api.ApiModule;
import pl.kodujdlapolski.na4lapy.repository.RepositoryModule;
import pl.kodujdlapolski.na4lapy.repository.database.DatabaseModule;

@Singleton
@Component(modules = {Na4LapyModule.class, RepositoryModule.class, ApiModule.class, DatabaseModule.class})
public interface Na4LapyComponent {
    void inject(MainActivity activity);
}
