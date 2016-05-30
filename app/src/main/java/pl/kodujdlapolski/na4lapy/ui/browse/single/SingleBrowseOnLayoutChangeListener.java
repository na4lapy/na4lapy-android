package pl.kodujdlapolski.na4lapy.ui.browse.single;

import android.view.View;

import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

/**
 * https://github.com/lsjwzh/RecyclerViewPager/blob/master/app/src/main/java/com/lsjwzh/widget/recyclerviewpagerdeomo/SingleFlingPagerActivity.java
 */
public class SingleBrowseOnLayoutChangeListener implements View.OnLayoutChangeListener {

    private RecyclerViewPager recyclerView;

    public SingleBrowseOnLayoutChangeListener(RecyclerViewPager recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (recyclerView.getChildCount() < 3) {
            if (recyclerView.getChildAt(1) != null) {
                if (recyclerView.getCurrentPosition() == 0) {
                    View v1 = recyclerView.getChildAt(1);
                    v1.setScaleY(0.9f);
                    v1.setScaleX(0.9f);
                } else {
                    View v1 = recyclerView.getChildAt(0);
                    v1.setScaleY(0.9f);
                    v1.setScaleX(0.9f);
                }
            }
        } else {
            if (recyclerView.getChildAt(0) != null) {
                View v0 = recyclerView.getChildAt(0);
                v0.setScaleY(0.9f);
                v0.setScaleX(0.9f);
            }
            if (recyclerView.getChildAt(2) != null) {
                View v2 = recyclerView.getChildAt(2);
                v2.setScaleY(0.9f);
                v2.setScaleX(0.9f);
            }
        }

    }
}
