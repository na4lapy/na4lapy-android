package pl.kodujdlapolski.na4lapy.model.type;

import pl.kodujdlapolski.na4lapy.R;

public enum Attitude {
    HOSTILE(R.string.attitude_hostile), FRIENDLY(R.string.attitude_friendly), NEUTRAL(R.string.attitude_neutral), UNKNOWN(R.string.attitude_unknown);

    public int resId;

    Attitude(int resId) {
        this.resId = resId;
    }
}
