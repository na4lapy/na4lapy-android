package pl.kodujdlapolski.na4lapy;

import javax.inject.Singleton;

import dagger.Component;
import pl.kodujdlapolski.na4lapy.repository.RepositoryModule;
import pl.kodujdlapolski.na4lapy.repository.database.DatabaseModule;

@Singleton
@Component(modules = {Na4LapyModule.class, RepositoryModule.class, DatabaseModule.class})
public interface Na4LapyComponent {
}
