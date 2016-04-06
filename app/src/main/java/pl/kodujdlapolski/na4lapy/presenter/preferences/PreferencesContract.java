package pl.kodujdlapolski.na4lapy.presenter.preferences;

import pl.kodujdlapolski.na4lapy.model.UserPreferences;

/**
 * Created by Gosia on 2016-03-12.
 */
public interface PreferencesContract {

    interface View {
        void showUserPreferences(UserPreferences userPreferences);

    }

    interface UserActionListener {
        void savePreferences(UserPreferences userPreferences);
    }
}
