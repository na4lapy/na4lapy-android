package pl.kodujdlapolski.na4lapy.ui.browse.single;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * https://github.com/lsjwzh/RecyclerViewPager/blob/master/app/src/main/java/com/lsjwzh/widget/recyclerviewpagerdeomo/SingleFlingPagerActivity.java
 */
public class SingleBrowseRecyclerScrollListener extends RecyclerView.OnScrollListener {

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        int childCount = recyclerView.getChildCount();
        int width = recyclerView.getChildAt(0).getWidth();
        int padding = (recyclerView.getWidth() - width) / 2;

        for (int j = 0; j < childCount; j++) {
            View v = recyclerView.getChildAt(j);
            //往左 从 padding 到 -(v.getWidth()-padding) 的过程中，由大到小
            float rate = 0;
            if (v.getLeft() <= padding) {
                if (v.getLeft() >= padding - v.getWidth()) {
                    rate = (padding - v.getLeft()) * 1f / v.getWidth();
                } else {
                    rate = 1;
                }
                v.setScaleY(1 - rate * 0.1f);
                v.setScaleX(1 - rate * 0.1f);

            } else {
                //往右 从 padding 到 recyclerView.getWidth()-padding 的过程中，由大到小
                if (v.getLeft() <= recyclerView.getWidth() - padding) {
                    rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
                }
                v.setScaleY(0.9f + rate * 0.1f);
                v.setScaleX(0.9f + rate * 0.1f);
            }
        }
    }
}
