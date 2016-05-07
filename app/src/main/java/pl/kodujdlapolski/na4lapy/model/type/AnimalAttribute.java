package pl.kodujdlapolski.na4lapy.model.type;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

public interface AnimalAttribute {
    @StringRes int getLabelResId();
    @DrawableRes int getDrawableResId();
}
