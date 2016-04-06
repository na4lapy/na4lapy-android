package pl.kodujdlapolski.na4lapy.presenter.preferences;

import android.app.Activity;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.Na4LapyApp;
import pl.kodujdlapolski.na4lapy.model.UserPreferences;
import pl.kodujdlapolski.na4lapy.user.UserService;

/**
 * Created by Gosia on 2016-03-12.
 */
public class PreferencesPresenter implements PreferencesContract.UserActionListener {

    @Inject
    UserService userService;

    public PreferencesPresenter(Activity activity, PreferencesContract.View view) {

        ((Na4LapyApp)activity.getApplication()).getComponent().inject(this);
        view.showUserPreferences(loadPreferences());
    }

    @Override
    public void savePreferences(UserPreferences userPreferences) {
        userService.saveCurrentUserPreferences(userPreferences);
    }

    protected UserPreferences loadPreferences() {
        return userService.loadCurrentUserPreferences();
    }

}
