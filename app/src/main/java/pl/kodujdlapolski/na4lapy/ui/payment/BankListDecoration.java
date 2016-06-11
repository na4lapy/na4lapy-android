package pl.kodujdlapolski.na4lapy.ui.payment;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import pl.kodujdlapolski.na4lapy.R;

public class BankListDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;
    private int mSpanCount;

    public BankListDecoration(Context context, int spanCount) {
        mSpace = context.getResources().getDimensionPixelSize(R.dimen.element_spacing);
        mSpanCount = spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int column = position % mSpanCount;

        if (position < mSpanCount) {
            outRect.top = mSpace;
        } else {
            outRect.top = 0;
        }

        if (column == mSpanCount - 1) {
            outRect.right = mSpace;
        } else {
            outRect.right = 0;
        }

        outRect.left = mSpace;
        outRect.bottom = mSpace;
    }
}
