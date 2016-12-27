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
package pl.kodujdlapolski.na4lapy;

import android.content.pm.PackageManager;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.kodujdlapolski.na4lapy.presenter.settings.WebPageTypes;
import pl.kodujdlapolski.na4lapy.ui.settings.SettingsActivity;
import pl.kodujdlapolski.na4lapy.ui.settings.WebViewActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.action.ViewActions.click;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class SettingsActivityTest {

    @Rule
    public ActivityTestRule<SettingsActivity> mActivityRule = new ActivityTestRule<>(SettingsActivity.class);

    @Test
    public void isVersionDisplayed() {
        try {
            String versionName = String.format(mActivityRule.getActivity().getResources().getString(R.string.version),
                    mActivityRule.getActivity().getPackageManager()
                            .getPackageInfo(mActivityRule.getActivity().getPackageName(), 0).versionName);
            onView(withText(versionName)).check(matches(isDisplayed()));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isInfoDisplayed() {
        onView(withText(mActivityRule.getActivity().getString(R.string.about_text))).check(matches(isDisplayed()));
    }

    @Test
    public void isPrivacyPolicyDisplayedAndOnClickFiresProperIntent() {
        Intents.init();
        ViewInteraction privacyPolicyViewInteraction = onView(withText(mActivityRule.getActivity().getString(R.string.privacy_policy_text)));
        privacyPolicyViewInteraction.check(matches(isDisplayed()));
        privacyPolicyViewInteraction.perform(click());
        intended(allOf(
                        hasComponent(hasClassName(WebViewActivity.class.getName())),
                        hasExtra(WebViewActivity.EXTRA_TYPE, WebPageTypes.POLICY)
                )
        );
        Intents.release();
    }

    @Test
    public void isOpenSourceDisplayedAndOnClickFiresProperIntent() {
        Intents.init();
        ViewInteraction viewInteraction = onView(withText(mActivityRule.getActivity().getString(R.string.open_source_libs_text)));
        viewInteraction.check(matches(isDisplayed()));
        viewInteraction.perform(click());
        intended(allOf(
                        hasComponent(hasClassName(WebViewActivity.class.getName())),
                        hasExtra(WebViewActivity.EXTRA_TYPE, WebPageTypes.OPEN_SOURCE)
                )
        );
        Intents.release();
    }

    //todo - it should be deleted, because we resigned from login/logout feature
/*    @Test
    public void isLoginLogoutButtonDisplayed() {
        ViewInteraction viewInteraction = onView(withText(mActivityRule.getActivity().getString(R.string.login)));
        viewInteraction.check(matches(isDisplayed()));
    }*/
}