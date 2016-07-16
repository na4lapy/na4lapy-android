package pl.kodujdlapolski.na4lapy;


import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import pl.kodujdlapolski.na4lapy.ui.browse.single.SingleBrowseActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;

public class SingleBrowseActivityTest {

    @Rule
    public ActivityTestRule<SingleBrowseActivity> activityRule = new ActivityTestRule<>(SingleBrowseActivity.class, false, false);

    @Before
    public void setUp() {
        activityRule.launchActivity(new Intent());
    }

    @Test
    public void test() {
        onView(allOf(withId(R.id.animal_size_image), isDisplayed())).check(matches(isDisplayed()));
    }

}
