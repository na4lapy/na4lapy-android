package pl.kodujdlapolski.na4lapy.preferences;

import android.support.annotation.Nullable;

import pl.kodujdlapolski.na4lapy.model.UserPreferences;

public interface PreferencesService {

    String KEY_USER_PREFERENCES = "PreferencesService.KEY_USER_PREFERENCES";

    void setUserPreferences(UserPreferences userPreferences);
    @Nullable  UserPreferences getUserPreferences();
}
