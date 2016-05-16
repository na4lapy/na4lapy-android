package pl.kodujdlapolski.na4lapy.preferences;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
    public void addToFavourite(@Nullable Long animalId) {
        if (animalId == null) {
            return;
        }
        Type listType = new TypeToken<ArrayList<Long>>(){}.getType();
        String json = mSharedPreferences.getString(KEY_USER_FAVOURITES, null);
        List<Long> favourites = mGson.fromJson(json, listType);

        if (favourites == null) {
            favourites = new ArrayList<>();
        }

        favourites.add(animalId);
        json = mGson.toJson(favourites, listType);
        mSharedPreferences.edit().putString(KEY_PAYMENT_USER, json).apply();
    }

    @Override
    public void removeFromFavourite(Long animalId) {
        if (animalId == null) {
            return;
        }
        Type listType = new TypeToken<ArrayList<Long>>(){}.getType();
        String json = mSharedPreferences.getString(KEY_USER_FAVOURITES, null);
        List<Long> favourites = mGson.fromJson(json, listType);
        if (favourites == null) {
            return;
        }

        favourites.remove(favourites.indexOf(animalId));
        json = mGson.toJson(favourites, listType);
        mSharedPreferences.edit().putString(KEY_PAYMENT_USER, json).apply();
    }

    @Override
    public List<Long> getFavouriteList() {
        Type listType = new TypeToken<ArrayList<Long>>(){}.getType();
        String json = mSharedPreferences.getString(KEY_USER_FAVOURITES, null);
        List<Long> favourites = mGson.fromJson(json, listType);
        if (favourites == null) {
            favourites = new ArrayList<>();
        }
        return favourites;
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
