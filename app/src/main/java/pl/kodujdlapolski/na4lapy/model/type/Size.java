package pl.kodujdlapolski.na4lapy.model.type;

import pl.kodujdlapolski.na4lapy.R;

public enum Size {
    SMALL(R.string.size_small), MEDIUM(R.string.size_medium), LARGE(R.string.size_large);

    public int resId;

    Size(int resId) {
        this.resId = resId;
    }
}
