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

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import pl.kodujdlapolski.na4lapy.model.Photo;
import pl.kodujdlapolski.na4lapy.ui.details.AnimalGalleryActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;

public class AnimalGalleryActivityTest {

    @Rule
    public ActivityTestRule<AnimalGalleryActivity> activityRule = new ActivityTestRule<>(AnimalGalleryActivity.class, false, false);

    @Test
    public void isFirstPhotoDisplayed() {
        activityRule.launchActivity(initiateIntent(0));
        onView(allOf(withId(R.id.animal_pic_in_gallery), isDisplayed())).check(matches(isDisplayed()));
    }

    @Test
     public void isSecondPhotoDisplayed() {
        activityRule.launchActivity(initiateIntent(1));
        onView(allOf(withId(R.id.animal_pic_in_gallery), isDisplayed())).check(matches(isDisplayed()));
    }

    @Test
    public void isThirdPhotoDisplayed() {
        activityRule.launchActivity(initiateIntent(2));
        onView(allOf(withId(R.id.animal_pic_in_gallery), isDisplayed())).check(matches(isDisplayed()));
    }

    @Test
    public void isFourthPhotoDisplayed() {
        activityRule.launchActivity(initiateIntent(3));
        onView(allOf(withId(R.id.animal_pic_in_gallery), isDisplayed())).check(matches(isDisplayed()));
    }

    private static Intent initiateIntent(int index) {
        ArrayList<Photo> gallery = new ArrayList<>();
        createPhotoGallery(gallery);
        Intent i = new Intent();
        i.putExtra(AnimalGalleryActivity.EXTRA_GALLERY, gallery);
        i.putExtra(AnimalGalleryActivity.EXTRA_SELECTED_PIC, index);
        return i;
    }

    private static void createPhotoGallery(ArrayList<Photo> gallery) {
        Photo photo = new Photo();
        gallery.add(photo);
        photo = new Photo();
        gallery.add(photo);
        photo = new Photo();
        gallery.add(photo);
        photo = new Photo();
        gallery.add(photo);
    }

}
