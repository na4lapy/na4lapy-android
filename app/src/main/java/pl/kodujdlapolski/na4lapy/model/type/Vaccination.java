package pl.kodujdlapolski.na4lapy.model.type;

import android.support.annotation.StringRes;

import pl.kodujdlapolski.na4lapy.R;

public enum Vaccination {
    BASIC(R.string.vaccination_basic),
    EXTENDED(R.string.vaccination_extended),
    NONE(R.string.vaccination_none),
    UNKNOWN(R.string.vaccination_unknown);

    public int resId;

    Vaccination(@StringRes int resId) {
        this.resId = resId;
    }
}
