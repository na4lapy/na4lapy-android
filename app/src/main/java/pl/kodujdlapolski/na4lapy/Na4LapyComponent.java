package pl.kodujdlapolski.na4lapy;

import javax.inject.Singleton;

import dagger.Component;
import pl.kodujdlapolski.na4lapy.presenter.about_shelter.AboutShelterPresenter;
import pl.kodujdlapolski.na4lapy.api.ApiModule;
import pl.kodujdlapolski.na4lapy.preferences.PreferencesModule;
import pl.kodujdlapolski.na4lapy.presenter.preferences.PreferencesPresenter;
import pl.kodujdlapolski.na4lapy.repository.RepositoryModule;
import pl.kodujdlapolski.na4lapy.repository.database.DatabaseModule;
import pl.kodujdlapolski.na4lapy.sync.SynchronizationModule;
import pl.kodujdlapolski.na4lapy.ui.browse.BrowsePresenter;
import pl.kodujdlapolski.na4lapy.ui.splash.SplashActivity;
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
    void inject(BrowsePresenter browsePresenter);
    void inject(PreferencesPresenter presenter);

}
