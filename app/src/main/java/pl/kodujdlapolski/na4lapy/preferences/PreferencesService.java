package pl.kodujdlapolski.na4lapy.preferences;

import pl.kodujdlapolski.na4lapy.model.UserPreferences;

public interface PreferencesService {

    String KEY_USER_PREFERENCES = "PreferencesService.KEY_USER_PREFERENCES";

    void setUserPreferences(UserPreferences userPreferences);
    UserPreferences getUserPreferences();
}
