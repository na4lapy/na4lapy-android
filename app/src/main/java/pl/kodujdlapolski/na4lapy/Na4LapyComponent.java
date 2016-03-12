package pl.kodujdlapolski.na4lapy;

import javax.inject.Singleton;

import dagger.Component;
import pl.kodujdlapolski.na4lapy.presenter.AboutShelterPresenter;
import pl.kodujdlapolski.na4lapy.api.ApiModule;
import pl.kodujdlapolski.na4lapy.preferences.PreferencesModule;
import pl.kodujdlapolski.na4lapy.repository.RepositoryModule;
import pl.kodujdlapolski.na4lapy.repository.database.DatabaseModule;
import pl.kodujdlapolski.na4lapy.sync.SynchronizationModule;
import pl.kodujdlapolski.na4lapy.ui.animals_list.AnimalsListPresenter;
import pl.kodujdlapolski.na4lapy.ui.main.activity.SplashActivity;
import pl.kodujdlapolski.na4lapy.user.UserModule;

@Singleton
@Component(modules = {
        Na4LapyModule.class,
        RepositoryModule.class,
        ApiModule.class,
        DatabaseModule.class,
        SynchronizationModule.class,
        PreferencesModule.class,
        UserModule.class
})
public interface Na4LapyComponent {
    void inject(AboutShelterPresenter presenter);
    void inject(SplashActivity mainActivity);
    void inject(AnimalsListPresenter animalsListPresenter);
}
