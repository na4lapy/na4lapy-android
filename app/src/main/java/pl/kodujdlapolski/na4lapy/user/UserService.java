package pl.kodujdlapolski.na4lapy.user;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.UserPreferences;

public interface UserService {

    void saveCurrentUserPreferences(@NonNull UserPreferences userPreferences);

    @NonNull UserPreferences loadCurrentUserPreferences();

    int getPreferencesComplianceLevel(Animal animal);

    void login();
    void logout();
    @Nullable String getUserFirstName();
    @Nullable String getUserPhotoUrl();
    boolean isLogged();

    void addToFavourite(Animal animal);
    void removeFromFavourite(Animal animal);
}
