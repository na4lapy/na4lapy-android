package pl.kodujdlapolski.na4lapy.preferences;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import pl.kodujdlapolski.na4lapy.model.UserPreferences;


public class PreferencesServiceImpl implements PreferencesService {

    private SharedPreferences mSharedPreferences;
    private Gson mGson;

    public PreferencesServiceImpl(SharedPreferences sharedPreferences, Gson gson) {
        mSharedPreferences = sharedPreferences;
        mGson = gson;
    }

    @Override
    public void setUserPreferences(UserPreferences userPreferences) {
        String json = mGson.toJson(userPreferences);
        mSharedPreferences.edit().putString(KEY_USER_PREFERENCES, json).apply();
    }

    @Override
    @Nullable
    public UserPreferences getUserPreferences() {
        String json = mSharedPreferences.getString(KEY_USER_PREFERENCES, null);
        return mGson.fromJson(json, UserPreferences.class);
    }

    @Override
    public boolean shouldIntroductionBeShown() {
        boolean wasIntroductionShown = mSharedPreferences.getBoolean(KEY_WAS_INTRODUCTION_SHOWN, false);
        if (!wasIntroductionShown) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putBoolean(KEY_WAS_INTRODUCTION_SHOWN, true);
            editor.apply();
            return true;
        }
        return false;
    }

}
