package pl.kodujdlapolski.na4lapy.model.type;

import pl.kodujdlapolski.na4lapy.R;

public enum Training {
    TRAINED(R.string.training_trainined), UNTRAINED(R.string.training_untrainined);

    public int resId;

    Training(int resId) {
        this.resId = resId;
    }
}
