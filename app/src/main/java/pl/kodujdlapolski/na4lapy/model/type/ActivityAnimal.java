package pl.kodujdlapolski.na4lapy.model.type;

import pl.kodujdlapolski.na4lapy.R;

public enum ActivityAnimal {
    HIGH(R.string.activity_high), LOW(R.string.activity_low);
    public int resId;

    ActivityAnimal(int resId) {
        this.resId = resId;
    }
}
