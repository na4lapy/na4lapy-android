package pl.kodujdlapolski.na4lapy.model;

import android.support.annotation.Nullable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "shelters")
public class Shelter extends BaseEntity implements Serializable {

    @DatabaseField private String name;
    @DatabaseField private String street;
    @DatabaseField private String buildingNumber;
    @DatabaseField private String city;
    @DatabaseField private String postalCode;
    @DatabaseField private String email;
    @DatabaseField private String phoneNumber;
    @DatabaseField private String website;
    @DatabaseField private String accountNumber;
    @DatabaseField private String adoptionRules;

    public Shelter() {
        // needed by ormlite
    }

    public @Nullable String  getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public @Nullable String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public @Nullable String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public @Nullable String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public @Nullable String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public @Nullable String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public @Nullable String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @Nullable String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public @Nullable String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public @Nullable String getAdoptionRules() {
        return adoptionRules;
    }

    public void setAdoptionRules(String adoptionRules) {
        this.adoptionRules = adoptionRules;
    }

    @Override
    public String toString() {
        return "Shelter{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", buildingNumber='" + buildingNumber + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", website='" + website + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
