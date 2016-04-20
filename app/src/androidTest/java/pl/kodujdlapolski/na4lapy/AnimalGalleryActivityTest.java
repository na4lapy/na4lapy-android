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

/**
 * Created by Malgorzata Syska on 2016-04-18.
 */
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
        photo.setUrl("http://cdn23.se.smcloud.net/t/photos/t/389279/labrador-retriever-pies_23597760.jpg");
        gallery.add(photo);
        photo = new Photo();
        photo.setUrl("http://www.dobrylekarz.info/files/images/pies-sennik-sen-tlumaczenie-interpretacja-snu-pies.jpg");
        gallery.add(photo);
        photo = new Photo();
        photo.setUrl("http://bi.gazeta.pl/im/48/ec/f7/z16247880Q,Pies-byl-pierwszym-zwierzeciem-idomowionym-przez-c.jpg");
        gallery.add(photo);
        photo = new Photo();
        photo.setUrl("http://schroniskopromyk.pl/wp-content/uploads/2016/02/Morus-5-150x150.jpg");
        gallery.add(photo);
    }

}
