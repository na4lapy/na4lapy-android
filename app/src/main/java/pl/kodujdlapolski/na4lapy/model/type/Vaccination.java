package pl.kodujdlapolski.na4lapy.model.type;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import pl.kodujdlapolski.na4lapy.R;

public enum Vaccination implements AnimalAttribute {
    BASIC(R.string.vaccination_basic),
    EXTENDED(R.string.vaccination_extended),
    NONE(R.string.vaccination_none),
    UNKNOWN(R.string.vaccination_unknown);

    private int mLabelResId;

    Vaccination(@StringRes int labelResId) {
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
