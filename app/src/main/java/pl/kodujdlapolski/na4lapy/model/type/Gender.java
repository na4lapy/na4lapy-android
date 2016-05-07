package pl.kodujdlapolski.na4lapy.model.type;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import pl.kodujdlapolski.na4lapy.R;

public enum Gender implements AnimalAttribute {
    MALE(R.string.gender_male, R.drawable.vector_drawable_przegladanie_samiec),
    FEMALE(R.string.gender_female, R.drawable.vector_drawable_przegladanie_suczka),
    UNKNOWN(R.string.gender_unknown, R.drawable.vector_drawable_przegladanie_samiec);

    private int mLabelResId;
    private int mDrawableResId;


    Gender(@StringRes int labelResId, @DrawableRes int drawableResId) {
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
