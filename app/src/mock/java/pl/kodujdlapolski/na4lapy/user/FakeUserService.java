package pl.kodujdlapolski.na4lapy.user;

import android.support.annotation.NonNull;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.UserPreferences;

public class FakeUserService implements UserService {

    public FakeUserService() {
    }

    @Override
    public void saveCurrentUserPreferences(@NonNull UserPreferences userPreferences) {
    }

    @NonNull
    @Override
    public UserPreferences loadCurrentUserPreferences() {
        return new UserPreferences();
    }

    @Override
    public int getPreferencesComplianceLevel(Animal animal) {
        return 5;
    }

    @Override
    public void login() {
    }

    @Override
    public void logout() {
    }

    @Override
    public String getFullUserName() {
        return "Stefan Uśmiechnięty";
    }

    @Override
    public String getUserPhotoUrl() {
        return "https://secure-static.tagged.com/im/people/silhouette_m_300.png";
    }

    @Override
    public boolean isLogged() {
        return true;
    }
}
