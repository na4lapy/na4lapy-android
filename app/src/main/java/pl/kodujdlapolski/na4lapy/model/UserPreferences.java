package pl.kodujdlapolski.na4lapy.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Malgorzata Syska on 2016-03-05.
 */
@Getter
@Setter
public class UserPreferences {

    private boolean typeDog;
    private boolean typeCat;
    private boolean typeOther;
    private boolean genderWoman;
    private boolean genderMan;
    private int ageMin;
    private int ageMax;
    private boolean sizeSmall;
    private boolean sizeMedium;
    private boolean sizeLarge;
    private boolean activityLow;
    private boolean activityHigh;

    public UserPreferences() {
        typeDog = false;
        typeCat = false;
        typeOther = false;
        genderWoman = false;
        genderMan = false;
        ageMin = 0;
        ageMax = 20;
        sizeSmall = false;
        sizeMedium = false;
        sizeLarge = false;
        activityLow = false;
        activityHigh = false;
    }
}
