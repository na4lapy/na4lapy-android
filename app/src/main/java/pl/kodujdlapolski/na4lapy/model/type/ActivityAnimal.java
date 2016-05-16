package pl.kodujdlapolski.na4lapy.model.type;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import pl.kodujdlapolski.na4lapy.R;

public enum ActivityAnimal implements AnimalAttribute {
    HIGH(R.string.activity_high, R.drawable.vector_drawable_przegladanie_aktywny),
    LOW(R.string.activity_low, R.drawable.vector_drawable_przegladanie_domator),
    UNKNOWN(R.string.activity_unknown, R.drawable.vector_drawable_przegladanie_domator);

    private int mLabelResId;
    private int mDrawableResId;

    ActivityAnimal(@StringRes int labelResId, @DrawableRes int drawableResId) {
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
