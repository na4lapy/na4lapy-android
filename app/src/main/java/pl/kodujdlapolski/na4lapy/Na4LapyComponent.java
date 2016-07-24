package pl.kodujdlapolski.na4lapy;

import javax.inject.Singleton;

import dagger.Component;
import pl.kodujdlapolski.na4lapy.presenter.about_shelter.AboutShelterPresenter;
import pl.kodujdlapolski.na4lapy.presenter.payment.PaymentPresenter;
import pl.kodujdlapolski.na4lapy.presenter.preferences.PreferencesPresenter;
import pl.kodujdlapolski.na4lapy.service.api.ApiModule;
import pl.kodujdlapolski.na4lapy.service.payments.PaymentsModule;
import pl.kodujdlapolski.na4lapy.service.preferences.PreferencesModule;
import pl.kodujdlapolski.na4lapy.service.repository.RepositoryModule;
import pl.kodujdlapolski.na4lapy.service.repository.database.DatabaseModule;
import pl.kodujdlapolski.na4lapy.service.system.SystemModule;
import pl.kodujdlapolski.na4lapy.service.user.UserModule;
import pl.kodujdlapolski.na4lapy.ui.browse.BrowsePresenter;
import pl.kodujdlapolski.na4lapy.ui.browse.list.ListBrowseFragment;
import pl.kodujdlapolski.na4lapy.ui.details.DetailsActivity;
import pl.kodujdlapolski.na4lapy.ui.drawer.DrawerActivityHandler;
import pl.kodujdlapolski.na4lapy.ui.settings.SettingsFragment;
import pl.kodujdlapolski.na4lapy.ui.splash.SplashActivity;

@Singleton
@Component(modules = {
        Na4LapyModule.class,
        RepositoryModule.class,
        ApiModule.class,
        DatabaseModule.class,
        PaymentsModule.class,
        PreferencesModule.class,
        SystemModule.class,
        UserModule.class
})
public interface Na4LapyComponent {

    void inject(AboutShelterPresenter presenter);
    void inject(SplashActivity mainActivity);
    void inject(BrowsePresenter browsePresenter);
    void inject(PreferencesPresenter presenter);
    void inject(DetailsActivity detailsActivity);
    void inject(ListBrowseFragment fragment);
    void inject(DrawerActivityHandler handler);
    void inject(SettingsFragment fragment);
    void inject(PaymentPresenter presenter);
}
