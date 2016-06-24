package pl.kodujdlapolski.na4lapy.service.preferences;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pl.kodujdlapolski.na4lapy.model.UserPreferences;
import pl.kodujdlapolski.na4lapy.service.payments.model.Customer;


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
        mSharedPreferences.edit().putString(KEY_USER_FAVOURITES, json).apply();
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
        if (favourites.contains(animalId)) {
            favourites.remove(favourites.indexOf(animalId));
            json = mGson.toJson(favourites, listType);
            mSharedPreferences.edit().putString(KEY_USER_FAVOURITES, json).apply();
        }
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
    public void setCustomer(Customer customer) {
        String json = mGson.toJson(customer);
        mSharedPreferences.edit().putString(KEY_PAYMENT_CUSTOMER, json).apply();
    }

    @NonNull
    @Override
    public Customer getCustomer() {
        String json = mSharedPreferences.getString(KEY_PAYMENT_CUSTOMER, null);
        return mGson.fromJson(json, Customer.class);
    }

    @Override
    public boolean shouldIntroductionBeShown() {
        boolean wasIntroductionShown = mSharedPreferences.getBoolean(KEY_WAS_INTRODUCTION_SHOWN, false);

        if (wasIntroductionShown) {
            return false;
        }

        mSharedPreferences.edit().putBoolean(KEY_WAS_INTRODUCTION_SHOWN, true).apply();
        return true;
    }
}
