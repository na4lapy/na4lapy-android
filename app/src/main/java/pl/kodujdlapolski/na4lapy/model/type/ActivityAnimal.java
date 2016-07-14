package pl.kodujdlapolski.na4lapy.model.type;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import pl.kodujdlapolski.na4lapy.R;

public enum ActivityAnimal implements AnimalAttribute {
    HIGH(R.string.activity_high, R.drawable.animal_activity_high_200dp),
    LOW(R.string.activity_low, R.drawable.animal_activity_low_200dp),
    UNKNOWN(R.string.activity_unknown, R.drawable.animal_activity_unknown_200dp);

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
