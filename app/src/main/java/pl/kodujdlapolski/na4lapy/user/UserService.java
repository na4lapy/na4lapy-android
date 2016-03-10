package pl.kodujdlapolski.na4lapy.user;

import pl.kodujdlapolski.na4lapy.model.UserPreferences;

public interface UserService {

    void saveCurrentUserPreferences(UserPreferences userPreferences);
    UserPreferences loadCurrentUserPreferences();

    void login();
    void logout();
}
