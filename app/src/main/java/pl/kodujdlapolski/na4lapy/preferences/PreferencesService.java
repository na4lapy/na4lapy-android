package pl.kodujdlapolski.na4lapy.preferences;

import android.support.annotation.Nullable;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.UserPreferences;
import pl.kodujdlapolski.na4lapy.payments.model.Customer;

public interface PreferencesService {

    String KEY_USER_PREFERENCES = "PreferencesService.KEY_USER_PREFERENCES";
    String KEY_USER_FAVOURITES = "PreferencesService.KEY_USER_FAVOURITES";
    String KEY_PAYMENT_CUSTOMER = "PreferencesService.KEY_PAYMENT_CUSTOMER";
    String KEY_WAS_INTRODUCTION_SHOWN = "PreferencesService.KEY_WAS_INTRODUCTION_SHOWN";

    void setUserPreferences(UserPreferences userPreferences);
    @Nullable UserPreferences getUserPreferences();

    void addToFavourite(@Nullable Long animalId);
    void removeFromFavourite(@Nullable Long animalId);
    List<Long> getFavouriteList();

    void setCustomer(Customer customer);
    @Nullable Customer getCustomer();

    boolean shouldIntroductionBeShown();
}
