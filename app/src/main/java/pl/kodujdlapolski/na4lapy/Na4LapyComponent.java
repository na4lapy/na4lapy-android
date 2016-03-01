package pl.kodujdlapolski.na4lapy;

import javax.inject.Singleton;

import dagger.Component;
import pl.kodujdlapolski.na4lapy.activities.MainActivity;
import pl.kodujdlapolski.na4lapy.presenter.AboutShelterPresenter;
import pl.kodujdlapolski.na4lapy.repository.RepositoryModule;
import pl.kodujdlapolski.na4lapy.ui.about_shelter.AboutShelterActivity;

@Singleton
@Component(modules = {Na4LapyModule.class, RepositoryModule.class})
public interface Na4LapyComponent {
    void inject(AboutShelterPresenter presenter);
    void inject(MainActivity mainActivity);
}
