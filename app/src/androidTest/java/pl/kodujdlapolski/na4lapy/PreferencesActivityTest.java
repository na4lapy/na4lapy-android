package pl.kodujdlapolski.na4lapy;

import android.content.Intent;
import android.graphics.ColorFilter;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import pl.kodujdlapolski.na4lapy.model.UserPreferences;
import pl.kodujdlapolski.na4lapy.presenter.preferences.PreferencesPresenter;
import pl.kodujdlapolski.na4lapy.ui.preferences.ToggleImageButton;
import pl.kodujdlapolski.na4lapy.ui.preferences.PreferencesActivity;
import pl.kodujdlapolski.na4lapy.ui.preferences.PreferencesFragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by Malgorzata Syska on 2016-03-20.
 */
public class PreferencesActivityTest {

    private UserPreferences userPreferences;

    @Rule
    public ActivityTestRule<PreferencesActivity> activityRule = new ActivityTestRule<>(PreferencesActivity.class, false, false);

    @Before
    public void setUserPreferences() {
        activityRule.launchActivity(new Intent());
        PreferencesFragment fragment = (PreferencesFragment) activityRule.getActivity()
                .getSupportFragmentManager().findFragmentById(R.id.preferences_app_fragment);
        PreferencesPresenter preferencesPresenter = fragment.getPresenter();
        userPreferences = new UserPreferences(true, false, true, true, true, 1, 15, true, true, true, true, true);
        preferencesPresenter.savePreferences(userPreferences);
    }

    @Test
    public void isTitleDisplayed() {
        onView(withId(R.id.whatAreYouLookingFor)).check(matches(isDisplayed()));
    }

    @Test
    public void areTextCategoriesDisplayed() {
        onView(withId(R.id.typeText)).check(matches(withText(R.string.type)));
        onView(withId(R.id.typeText)).check(matches(isDisplayed()));
        onView(withId(R.id.genderText)).check(matches(withText(R.string.sex)));
        onView(withId(R.id.genderText)).check(matches(isDisplayed()));
        onView(withId(R.id.ageText)).check(matches(withText(R.string.age)));
        onView(withId(R.id.ageText)).check(matches(isDisplayed()));
        onView(withId(R.id.sizeText)).check(matches(withText(R.string.size)));
        onView(withId(R.id.sizeText)).check(matches(isDisplayed()));
        onView(withId(R.id.activityText)).check(matches(withText(R.string.activity)));
        onView(withId(R.id.activityText)).check(matches(isDisplayed()));
    }

    @Test
    public void areIconsDisplayed() {
        onView(withId(R.id.type_dog)).check(matches(isDisplayed()));
        onView(withId(R.id.type_cat)).check(matches(isDisplayed()));
        onView(withId(R.id.type_other)).check(matches(isDisplayed()));
        onView(withId(R.id.gender_woman)).check(matches(isDisplayed()));
        onView(withId(R.id.gender_man)).check(matches(isDisplayed()));
        onView(withId(R.id.age_min)).check(matches(isDisplayed()));
        onView(withId(R.id.age_max)).check(matches(isDisplayed()));
        onView(withId(R.id.size_small)).check(matches(isDisplayed()));
        onView(withId(R.id.size_medium)).check(matches(isDisplayed()));
        onView(withId(R.id.size_large)).check(matches(isDisplayed()));
        onView(withId(R.id.activity_low)).check(matches(isDisplayed()));
        onView(withId(R.id.activity_high)).check(matches(isDisplayed()));
    }

    @Test
    public void doesIconUncheckWhenYouClickIt() {
        onView(withId(R.id.type_dog)).check(matches(isChecked()));
        onView(withId(R.id.type_dog)).perform(click()).check(matches(isNotChecked()));
    }

    @Test
    public void doesCheckedIconHasColor() {
        onView(withId(R.id.type_dog)).check(matches(new Matcher<View>() {
            @Override
            public boolean matches(Object item) {
                ToggleImageButton button = (ToggleImageButton) item;
                ColorFilter buttonFilter = button.getColorFilter();
                return (buttonFilter != null);
            }

            @Override
            public void describeMismatch(Object item, Description mismatchDescription) { }

            @Override
            public void _dont_implement_Matcher___instead_extend_BaseMatcher_() { }

            @Override
            public void describeTo(Description description) { }
        }));
    }

    @Test
    public void doesIconChangeColorWhenYouClickIt() {
        onView(withId(R.id.type_dog)).perform(click());
        onView(withId(R.id.type_dog)).check(matches(new Matcher<View>() {
            @Override
            public boolean matches(Object item) {
                ToggleImageButton button = (ToggleImageButton) item;
                ColorFilter buttonFilter = button.getColorFilter();
                return (buttonFilter == null);
            }

            @Override
            public void describeMismatch(Object item, Description mismatchDescription) { }

            @Override
            public void _dont_implement_Matcher___instead_extend_BaseMatcher_() { }

            @Override
            public void describeTo(Description description) { }
        }));
    }

    @Test
    public void doesClickAtMinAgeDisplaysDialogBox() {
        onView(withId(R.id.age_min)).perform(click());
        onView(withId(R.id.numberPicker)).check(matches(isDisplayed()));
    }

    @Test
    public void doesClickAtMaxAgeDisplaysDialogBox() {
        onView(withId(R.id.age_max)).perform(click());
        onView(withId(R.id.numberPicker)).check(matches(isDisplayed()));
    }

    @Test
    public void isValueOfMinAgePickerIdenticalWithAgeText() {
        onView(withId(R.id.age_min)).perform(click());
        onView(withId(R.id.numberPicker)).check(matches(isDisplayed()));
        String age = String.valueOf(userPreferences.getAgeMin());
        onView(withText(age)).check(matches(isDisplayed()));
    }

    @Test
    public void isValueOfMaxAgePickerIdenticalWithAgeText() {
        onView(withId(R.id.age_max)).perform(click());
        onView(withId(R.id.numberPicker)).check(matches(isDisplayed()));
        String age = String.valueOf(userPreferences.getAgeMax());
        onView(withText(age)).check(matches(isDisplayed()));
    }

    @Test
    public void doesMinAgePickerDialogHasProperTitle() {
        onView(withId(R.id.age_min)).perform(click());
        onView(withText(activityRule.getActivity().getResources()
                .getString(R.string.dialog_age_picker_title_minimal))).check(matches(isDisplayed()));
    }

    @Test
    public void doesMaxAgePickerDialogHasProperTitle() {
        onView(withId(R.id.age_max)).perform(click());
        onView(withText(activityRule.getActivity().getResources()
                .getString(R.string.dialog_age_picker_title_maximal))).check(matches(isDisplayed()));
    }

    @Test
    public void areIconsProperlyCheckedDueToUserPreferences() {
        onView(withId(R.id.type_dog)).check(matches(isChecked()));
        onView(withId(R.id.type_cat)).check(matches(isNotChecked()));
        onView(withId(R.id.type_other)).check(matches(isChecked()));
        onView(withId(R.id.gender_woman)).check(matches(isChecked()));
        onView(withId(R.id.gender_man)).check(matches(isChecked()));
        onView(withId(R.id.age_min)).check(matches(withText("1")));
        onView(withId(R.id.age_max)).check(matches(withText("15")));
        onView(withId(R.id.size_small)).check(matches(isChecked()));
        onView(withId(R.id.size_medium)).check(matches(isChecked()));
        onView(withId(R.id.size_large)).check(matches(isChecked()));
        onView(withId(R.id.activity_low)).check(matches(isChecked()));
        onView(withId(R.id.activity_high)).check(matches(isChecked()));
    }

    @Test
    public void doesNegativeButtonOnNumberPickerDismissADialog() {
        String cancel = activityRule.getActivity().getResources().getString(R.string.cancel);
        onView(withId(R.id.age_min)).perform(click());
        onView(withText(cancel)).check(matches(isDisplayed()));
        onView(withText(cancel)).perform(click());
        onView(withId(R.id.numberPicker)).check(doesNotExist());
    }

    @Test
    public void doesPositiveButtonOnNumberPickerSavesAge() {
        String choose = activityRule.getActivity().getResources().getString(R.string.choose);
        onView(withId(R.id.age_min)).perform(click());
        onView(withText(choose)).check(matches(isDisplayed()));
        onView(withId(R.id.numberPicker)).perform(swipeUp());
        onView(withText(choose)).perform(click());
        onView(withId(R.id.numberPicker)).check(doesNotExist());
        onView(withText("1")).check(doesNotExist());
    }

    @Test
    public void isSaveButtonDisplayed() {
        onView(withId(R.id.preferences_app_fragment)).perform(swipeUp());
        onView(withId(R.id.save_preferences)).check(matches(isDisplayed()));
    }

    @Test
    public void doesSaveButtonSavesDataAndDisplayToast() {
        onView(withId(R.id.preferences_app_fragment)).perform(swipeUp());
        onView(withId(R.id.save_preferences)).perform(click());
        onView(withText(R.string.save_preferences_message))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void doesAgeSwapWorks() {
        String choose = activityRule.getActivity().getResources().getString(R.string.choose);
        onView(withId(R.id.age_min)).perform(click());
        for (int i = 0;i<10;i++) {
            onView(withId(R.id.numberPicker)).perform(swipeUp());
        }
        onView(withText(choose)).perform(click());
        onView(withId(R.id.age_min)).check(matches(withText("15")));
        onView(withId(R.id.age_max)).check(matches(withText("20")));
    }

    @Test
    public void areYouTransferedToBrowsingAnimalsWhenYouSavePreferences() {
        onView(withId(R.id.preferences_app_fragment)).perform(swipeUp());
        onView(withId(R.id.save_preferences)).perform(click());
        onView(withId(R.id.preferences_app_fragment)).check(doesNotExist());
        onView(withId(R.id.container)).check(matches(isDisplayed()));
    }

}
