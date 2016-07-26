package pl.kodujdlapolski.na4lapy.model.type;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import pl.kodujdlapolski.na4lapy.R;

public enum Gender implements AnimalAttribute {
    MALE(R.string.gender_male, R.drawable.animal_gender_male_100dp),
    FEMALE(R.string.gender_female, R.drawable.animal_gender_female_100dp),
    UNKNOWN(R.string.gender_unknown, R.drawable.animal_gender_unknown_100dp);

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
