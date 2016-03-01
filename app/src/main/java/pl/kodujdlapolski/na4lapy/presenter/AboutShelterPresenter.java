package pl.kodujdlapolski.na4lapy.presenter;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.Na4LapyApp;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.repository.RepositoryService;
import pl.kodujdlapolski.na4lapy.ui.about_shelter.AboutShelterActivity;
import pl.kodujdlapolski.na4lapy.ui.about_shelter.AboutShelterFragment;

/**
 * Created by Natalia on 2016-03-01.
 */
public class AboutShelterPresenter {

    @Inject
    RepositoryService repositoryService;
    private AboutShelterFragment aboutShelterFragment;
    private final Long shelterId;

    public AboutShelterPresenter(AboutShelterFragment aboutShelterFragment) {
        this.aboutShelterFragment = aboutShelterFragment;
        shelterId = ((AboutShelterActivity)aboutShelterFragment.getActivity()).getShelterId();
        ((Na4LapyApp) aboutShelterFragment.getActivity().getApplication()).getComponent().inject(this);
        repositoryService.getShelter(shelterId, new RepositoryService.GetShelterCallback() {
            @Override
            public void onShelterLoaded(Shelter shelter) {
                onShelterAvailable(shelter);
            }
        });
    }

    private void onShelterAvailable(Shelter shelter) {
        aboutShelterFragment.populateView(shelter);
    }
}
