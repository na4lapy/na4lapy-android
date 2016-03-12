package pl.kodujdlapolski.na4lapy.model;

/**
 * Created by Gosia on 2016-03-05.
 */
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

    public UserPreferences(boolean typeDog, boolean typeCat, boolean typeOther, boolean genderWoman,
                           boolean genderMan, int ageMin, int ageMax, boolean sizeSmall,
                           boolean sizeMedium, boolean sizeLarge, boolean activityLow, boolean activityHigh) {

        this.typeDog = typeDog;
        this.typeCat = typeCat;
        this.typeOther = typeOther;
        this.genderWoman = genderWoman;
        this.genderMan = genderMan;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.sizeSmall = sizeSmall;
        this.sizeMedium = sizeMedium;
        this.sizeLarge = sizeLarge;
        this.activityLow = activityLow;
        this.activityHigh = activityHigh;
    }

    public UserPreferences() {

        this.typeDog = true;
        this.typeCat = true;
        this.typeOther = true;
        this.genderWoman = true;
        this.genderMan = true;
        this.ageMin = 0;
        this.ageMax = 20;
        this.sizeSmall = true;
        this.sizeMedium = true;
        this.sizeLarge = true;
        this.activityLow = true;
        this.activityHigh = true;
    }

    public boolean isTypeDog() {
        return typeDog;
    }

    public void setTypeDog(boolean argument) {
        this.typeDog = argument;
    }

    public boolean isTypeCat() {
        return typeCat;
    }

    public void setTypeCat(boolean argument) {
        this.typeCat = argument;
    }

    public boolean isTypeOther() {
        return typeOther;
    }

    public void setTypeOther(boolean argument) {
        this.typeOther = argument;
    }

    public boolean isGenderWoman() {
        return genderWoman;
    }

    public void setGenderWoman(boolean argument) {
        this.genderWoman = argument;
    }

    public boolean isGenderMan() {
        return genderMan;
    }

    public void setGenderMan(boolean argument) {
        this.genderMan = argument;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }

    public boolean isSizeSmall() {
        return sizeSmall;
    }

    public void setSizeSmall(boolean argument) {
        this.sizeSmall = argument;
    }

    public boolean isSizeMedium() {
        return sizeMedium;
    }

    public void setSizeMedium(boolean argument) {
        this.sizeMedium = argument;
    }

    public boolean isActivityLow() {
        return activityLow;
    }

    public void setActivityLow(boolean argument) {
        this.activityLow = argument;
    }

    public boolean isSizeLarge() {
        return sizeLarge;
    }

    public void setSizeLarge(boolean argument) {
        this.sizeLarge = argument;
    }

    public boolean isActivityHigh() {
        return activityHigh;
    }

    public void setActivityHigh(boolean argument) {
        this.activityHigh = argument;
    }
}
