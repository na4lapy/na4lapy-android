package pl.kodujdlapolski.na4lapy;

import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import pl.kodujdlapolski.na4lapy.ui.about_shelter.AboutShelterActivity;
import pl.kodujdlapolski.na4lapy.ui.browse.list.ListBrowseActivity;
import pl.kodujdlapolski.na4lapy.ui.browse.single.SingleBrowseActivity;
import pl.kodujdlapolski.na4lapy.ui.preferences.PreferencesActivity;
import pl.kodujdlapolski.na4lapy.ui.settings.SettingsActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.endsWith;


/**
 * Created by Malgorzata Syska on 2016-03-27.
 */
public class DrawerActivityTest {

    @Rule
    public ActivityTestRule<PreferencesActivity> activityRule = new ActivityTestRule<>(PreferencesActivity.class, false, false);

    @Rule
    public ActivityTestRule<SingleBrowseActivity> activityRuleBrowse = new ActivityTestRule<>(SingleBrowseActivity.class, false, false);

    @Before
    public void openDrawerMenu() {
        activityRule.launchActivity(new Intent());
        onView(withContentDescription(activityRule.getActivity().getString(R.string.navigation_drawer_open)))
                .perform(click());
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()));
    }

    //todo - do zmiany po zaimplementowaniu logowania
    @Test
    public void isHeaderDisplayed() {
        onView(withText("Imię użytkownika")).check(matches(isDisplayed()));
        onView(withId(R.id.imageView)).check(matches(isDisplayed()));
    }

    @Test
    public void areCategoriesTitlesDisplayed() {
        onView(withText(R.string.browsing)).check(matches(isDisplayed()));
        onView(withText(R.string.favouritiesList)).check(matches(isDisplayed()));
        onView(allOf(withText(R.string.lookupPreferences), withParent(withClassName(endsWith("NavigationMenuItemView"))))).check(matches(isDisplayed()));
        onView(withText(R.string.aboutShelter)).check(matches(isDisplayed()));
        onView(withText(R.string.aboutApplication)).check(matches(isDisplayed()));
    }

    @Test
    public void doesClickOnBrowseDirectsToBrowsingActivity() {
        isProperActivityDisplayed(
                R.string.browsing,
                SingleBrowseActivity.class,
                SingleBrowseActivity.EXTRA_IS_SINGLE_ELEMENT_BROWSE,
                true);
    }

    @Test
    public void doesClickOnFavouritesDirectsToFavouritesListActivity() {
        isProperActivityDisplayed(
                R.string.favouritiesList,
                ListBrowseActivity.class,
                ListBrowseActivity.EXTRA_IS_FAV_LIST,
                true);
    }

    @Test
    public void doesClickOnPreferencesDirectsToPreferencesActivity() {
        activityRuleBrowse.launchActivity(new Intent());
        onView(withContentDescription(activityRuleBrowse.getActivity().getString(R.string.navigation_drawer_open)))
                .perform(click());
        isProperActivityDisplayed(
                R.string.lookupPreferences,
                PreferencesActivity.class);
    }

    @Test
    public void doesClickAboutShelterDirectsToAboutShelterActivity() {
        isProperActivityDisplayed(
                R.string.aboutShelter,
                AboutShelterActivity.class);
    }

    @Test
    public void doesClickOnSettingsDirectsToSettingsActivity() {
        isProperActivityDisplayed(
                R.string.aboutApplication,
                SettingsActivity.class);
    }

    @Test
    public void isNavigationItemSelectedWhenYouClickIt() {
        onView(withText(R.string.browsing)).perform(click());
        onView(withContentDescription(activityRule.getActivity().getString(R.string.navigation_drawer_open)))
                .perform(click());
        onView(withText(R.string.browsing)).check(matches(isChecked()));
    }

    private void isProperActivityDisplayed (int category, Class<?> clazz, String extraKey, boolean extraValue) {
        Intents.init();
        onView(withText(category)).perform(click());
        intended(
            hasComponent(hasClassName(clazz.getName())));
        if (extraValue) {
            intended(
                hasExtra(extraKey, extraValue));
        }
        Intents.release();
    }

    private void isProperActivityDisplayed(int category, Class<?> clazz) {
        isProperActivityDisplayed(category, clazz, "", false);
    }
}
