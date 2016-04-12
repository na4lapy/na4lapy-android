package pl.kodujdlapolski.na4lapy.model.type;

import pl.kodujdlapolski.na4lapy.R;

public enum Gender {
    MALE(R.string.gender_male), FEMALE(R.string.gender_female), UNKNOWN(R.string.gender_unknown);

    public int resId;

    Gender(int resId) {
        this.resId = resId;
    }
}
