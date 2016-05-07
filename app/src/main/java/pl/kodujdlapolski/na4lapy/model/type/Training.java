package pl.kodujdlapolski.na4lapy.model.type;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import pl.kodujdlapolski.na4lapy.R;

public enum Training implements AnimalAttribute {
    BASIC(R.string.training_trainined),
    ADVANCED(R.string.training_untrainined),
    NONE(R.string.training_none),
    UNKNOWN(R.string.training_unknown);

    private int mLabelResId;

    Training(@StringRes int labelResId) {
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
