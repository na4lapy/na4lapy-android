package pl.kodujdlapolski.na4lapy.user;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.model.UserPreferences;
import pl.kodujdlapolski.na4lapy.preferences.PreferencesService;

public class UserServiceImpl implements UserService {

    private PreferencesService mPreferencesService;

    @Inject
    public UserServiceImpl(PreferencesService preferencesService) {
        mPreferencesService = preferencesService;
    }

    @Override
    public void saveCurrentUserPreferences(UserPreferences userPreferences) {
        mPreferencesService.setUserPreferences(userPreferences);
    }

    @Override
    public UserPreferences loadCurrentUserPreferences() {
        return mPreferencesService.getUserPreferences();
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
