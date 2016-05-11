package pl.kodujdlapolski.na4lapy.user;

import android.support.annotation.NonNull;

import java.util.Random;

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
        UserPreferences up = new UserPreferences();
        up.setTypeDog(true);
        up.setGenderMan(true);
        up.setSizeLarge(true);
        return up;
    }

    @Override
    public int getPreferencesComplianceLevel(Animal animal) {
        Random random = new Random();
        return random.nextInt(6);
    }

    @Override
    public void login() {
    }

    @Override
    public void logout() {
    }

    @Override
    public String getUserFirstName() {
        return "Hermenegilda";
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
