/*
 *	Copyright 2017 Stowarzyszenie Na4Łapy
 *
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *	http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */
package pl.kodujdlapolski.na4lapy.presenter.preferences;

import android.app.Activity;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.Na4LapyApp;
import pl.kodujdlapolski.na4lapy.model.UserPreferences;
import pl.kodujdlapolski.na4lapy.service.user.UserService;

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
