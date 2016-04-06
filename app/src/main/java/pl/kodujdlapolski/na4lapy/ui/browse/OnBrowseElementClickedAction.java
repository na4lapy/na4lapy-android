package pl.kodujdlapolski.na4lapy.ui.browse;

import android.os.Parcelable;

import pl.kodujdlapolski.na4lapy.model.Animal;

/**
 * Created by Natalia on 2016-03-29.
 */
public interface OnBrowseElementClickedAction extends Parcelable{
    void favourite(Animal animal);
    void details(Animal animal);
}