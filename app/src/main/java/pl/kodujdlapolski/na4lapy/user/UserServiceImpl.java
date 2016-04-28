package pl.kodujdlapolski.na4lapy.user;

import android.support.annotation.NonNull;

import java.util.Random;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.UserPreferences;
import pl.kodujdlapolski.na4lapy.preferences.PreferencesService;

import static com.google.common.base.Preconditions.checkNotNull;

public class UserServiceImpl implements UserService {

    private PreferencesService mPreferencesService;

    @Inject
    public UserServiceImpl(PreferencesService preferencesService) {
        mPreferencesService = preferencesService;
    }

    @Override
    public void saveCurrentUserPreferences(@NonNull UserPreferences userPreferences) {
        UserPreferences newUserPreferences = checkNotNull(userPreferences, "UserPreferences cannot be null");
        mPreferencesService.setUserPreferences(newUserPreferences);
    }

    @Override
    @NonNull
    public UserPreferences loadCurrentUserPreferences() {
        UserPreferences userPreferences = mPreferencesService.getUserPreferences();
        return userPreferences != null ? userPreferences : new UserPreferences();
    }

    @Override
    public int getPreferencesComplianceLevel(Animal animal) {
        Random random = new Random();
        return random.nextInt(6);
    }

    @Override
    public void login() {
        // TODO
    }

    @Override
    public void logout() {
        // TODO
    }
}
