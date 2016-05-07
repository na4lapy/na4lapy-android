package pl.kodujdlapolski.na4lapy.model.type;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import pl.kodujdlapolski.na4lapy.R;

public enum Sterilization implements AnimalAttribute {
    STERILIZED(R.string.sterilization_sterilized),
    NOT_STERILIZED(R.string.sterilization_not_sterilized),
    UNKNOWN(R.string.sterilization_unknown);

    private int mLabelResId;

    Sterilization(@StringRes int labelResId) {
        mLabelResId = labelResId;
    }

    @StringRes
    public int getLabelResId() {
        return mLabelResId;
    }

    @DrawableRes
    public int getDrawableResId() {
        return -1;
    }
}
