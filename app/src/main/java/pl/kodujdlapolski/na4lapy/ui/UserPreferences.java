package pl.kodujdlapolski.na4lapy.ui;

/**
 * Created by Gosia on 2016-03-05.
 */
public class UserPreferences {

    private Boolean typeDog;
    private Boolean typeCat;
    private Boolean typeOther;
    private Boolean genderWoman;
    private Boolean genderMan;
    private Integer ageMin;
    private Integer ageMax;
    private Boolean sizeSmall;
    private Boolean sizeMedium;
    private Boolean sizeLarge;
    private Boolean activityLow;
    private Boolean activityHigh;

    public UserPreferences(Boolean typeDog, Boolean typeCat, Boolean typeOther, Boolean genderWoman,
                           Boolean genderMan, Integer ageMin, Integer ageMax, Boolean sizeSmall,
                           Boolean sizeMedium, Boolean sizeLarge, Boolean activityLow, Boolean activityHigh) {

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

    public Boolean isTypeDog() {
        return typeDog;
    }

    public void setTypeDog(Boolean argument) {
        this.typeDog = argument;
    }

    public Boolean isTypeCat() {
        return typeCat;
    }

    public void setTypeCat(Boolean argument) {
        this.typeCat = argument;
    }

    public Boolean isTypeOther() {
        return typeOther;
    }

    public void setTypeOther(Boolean argument) {
        this.typeOther = argument;
    }

    public Boolean isGenderWoman() {
        return genderWoman;
    }

    public void setGenderWoman(Boolean argument) {
        this.genderWoman = argument;
    }

    public Boolean isGenderMan() {
        return genderMan;
    }

    public void setGenderMan(Boolean argument) {
        this.genderMan = argument;
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    public Boolean isSizeSmall() {
        return sizeSmall;
    }

    public void setSizeSmall(Boolean argument) {
        this.sizeSmall = argument;
    }

    public Boolean isSizeMedium() {
        return sizeMedium;
    }

    public void setSizeMedium(Boolean argument) {
        this.sizeMedium = argument;
    }

    public Boolean isActivityLow() {
        return activityLow;
    }

    public void setActivityLow(Boolean argument) {
        this.activityLow = argument;
    }

    public Boolean isSizeLarge() {
        return sizeLarge;
    }

    public void setSizeLarge(Boolean argument) {
        this.sizeLarge = argument;
    }

    public Boolean isActivityHigh() {
        return activityHigh;
    }

    public void setActivityHigh(Boolean argument) {
        this.activityHigh = argument;
    }
}
