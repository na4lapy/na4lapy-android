package pl.kodujdlapolski.na4lapy.preferences;

import android.content.SharedPreferences;


public class PreferencesServiceImpl implements PreferencesService {

    private SharedPreferences mSharedPreferences;

    public PreferencesServiceImpl(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }
}
