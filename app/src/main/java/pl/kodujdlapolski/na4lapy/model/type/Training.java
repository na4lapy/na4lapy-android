package pl.kodujdlapolski.na4lapy.model.type;

import android.support.annotation.StringRes;

import pl.kodujdlapolski.na4lapy.R;

public enum Training {
    TRAINED(R.string.training_trainined),
    UNTRAINED(R.string.training_untrainined),
    NONE(R.string.training_none);

    public int resId;

    Training(@StringRes int resId) {
        this.resId = resId;
    }
}
