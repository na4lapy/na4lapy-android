package pl.kodujdlapolski.na4lapy.ui.details;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

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
        //todo napisac sprawdzenie zooma
        return true;
    }



}
