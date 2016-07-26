package pl.kodujdlapolski.na4lapy.model.type;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import pl.kodujdlapolski.na4lapy.R;

public enum Size implements AnimalAttribute {
    SMALL(R.string.size_small, R.drawable.animal_size_small_100dp),
    MEDIUM(R.string.size_medium, R.drawable.animal_size_medium_100dp),
    LARGE(R.string.size_large, R.drawable.animal_size_big_100dp),
    UNKNOWN(R.string.size_unknown, R.drawable.animal_size_unknown_100dp);

    private int mLabelResId;
    private int mDrawableResId;

    Size(@StringRes int labelResId, @DrawableRes int drawableResId) {
        mLabelResId = labelResId;
        mDrawableResId = drawableResId;
    }

    @StringRes
    public int getLabelResId() {
        return mLabelResId;
    }

    @DrawableRes
    public int getDrawableResId() {
        return mDrawableResId;
    }
}
