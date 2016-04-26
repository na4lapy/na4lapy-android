package pl.kodujdlapolski.na4lapy.preferences;

import android.support.annotation.Nullable;

import pl.kodujdlapolski.na4lapy.model.UserPreferences;

public interface PreferencesService {

    String KEY_USER_PREFERENCES = "PreferencesService.KEY_USER_PREFERENCES";
    String KEY_WAS_INTRODUCTION_SHOWN = "PreferencesService.KEY_WAS_INTRODUCTION_SHOWN";

    void setUserPreferences(UserPreferences userPreferences);
    @Nullable  UserPreferences getUserPreferences();
    boolean shouldIntroductionBeShown();
}
