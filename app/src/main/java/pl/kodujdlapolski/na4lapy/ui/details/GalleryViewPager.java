package pl.kodujdlapolski.na4lapy.ui.details;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import pl.kodujdlapolski.na4lapy.R;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by Malgorzata Syska on 2016-04-12.
 */
public class GalleryViewPager extends ViewPager {

    private float pictureScale = 0.0f;
    private Activity activity;

    public GalleryViewPager(Context context) {
        super(context);
    }

    public GalleryViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.canSwipe()) {
            return super.onTouchEvent(event);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.canSwipe()) {
            return super.onInterceptTouchEvent(event);
        }
        return false;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    private boolean canSwipe() {
        //todo poprawic dzialanie
        PhotoView photoView = (PhotoView) activity.findViewById(R.id.animal_pic_in_gallery);
        System.out.println("SKALA: " + photoView.getScale());

        if (photoView == null) {
            return true;
        }
        if (photoView.getScale()<= 1.0) {
            return true;
        }
        return false;
    }



}
