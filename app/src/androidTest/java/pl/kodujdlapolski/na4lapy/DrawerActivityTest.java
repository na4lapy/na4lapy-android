package pl.kodujdlapolski.na4lapy;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import pl.kodujdlapolski.na4lapy.ui.main.activity.SplashActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Gosia on 2016-03-27.
 */
public class DrawerActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> activityRule = new ActivityTestRule<>(SplashActivity.class, false, false);

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
        onView(withText(R.string.lookupPreferences)).check(matches(isDisplayed()));
        onView(withText(R.string.aboutShelter)).check(matches(isDisplayed()));
        onView(withText(R.string.accountSettings)).check(matches(isDisplayed()));
    }

    @Test
    public void doesClickOnBrowseDirectsToBrowsingActivity() {
        onView(withText(R.string.browsing)).perform(click());
        onView(withText(R.string.animals_list_activity)).check(matches(isDisplayed()));
    }

    @Test
    public void doesClickOnFavouritesDirectsToFavouritesListActivity() {
        onView(withText(R.string.favouritiesList)).perform(click());
        onView(withText(R.string.fav_animals_list_activity)).check(matches(isDisplayed()));
    }

    @Test
    public void doesClickOnPreferencesDirectsToPreferencesActivity() {
        onView(withText(R.string.lookupPreferences)).perform(click());
        onView(withId(R.id.preferences_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void doesClickAboutShelterDirectsToAboutShelterActivity() {
        onView(withText(R.string.aboutShelter)).perform(click());
        onView(withId(R.id.about_shelter_app_fragment)).check(matches(isDisplayed()));
    }

    @Test
    public void doesClickOnSettingsDirectsToSettingsActivity() {
        onView(withText(R.string.accountSettings)).perform(click());
        onView(withId(R.id.about_app_fragment)).check(matches(isDisplayed()));
    }

    //todo - trzeba zauktualizowac kod - od Natalii
    @Test
    public void isNavigationItemSelectedWhenYouClickIt() {
        onView(withText(R.string.browsing)).perform(click());
        onView(withContentDescription(activityRule.getActivity().getString(R.string.navigation_drawer_open)))
                .perform(click());
        onView(withText(R.string.browsing)).check(matches(isChecked()));
    }
}
