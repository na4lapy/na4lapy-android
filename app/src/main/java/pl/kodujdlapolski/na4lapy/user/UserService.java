package pl.kodujdlapolski.na4lapy.user;

import android.support.annotation.NonNull;

import pl.kodujdlapolski.na4lapy.model.UserPreferences;

public interface UserService {

    void saveCurrentUserPreferences(@NonNull UserPreferences userPreferences);
    @NonNull UserPreferences loadCurrentUserPreferences();

    void login();
    void logout();
}
