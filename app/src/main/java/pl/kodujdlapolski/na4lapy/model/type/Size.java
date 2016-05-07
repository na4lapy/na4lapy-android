package pl.kodujdlapolski.na4lapy.model.type;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import pl.kodujdlapolski.na4lapy.R;

public enum Size implements AnimalAttribute {
    SMALL(R.string.size_small, R.drawable.vector_drawable_przegladanie_maly),
    MEDIUM(R.string.size_medium, R.drawable.vector_drawable_przegladanie_sredni),
    LARGE(R.string.size_large, R.drawable.vector_drawable_przegladanie_duzy);

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
