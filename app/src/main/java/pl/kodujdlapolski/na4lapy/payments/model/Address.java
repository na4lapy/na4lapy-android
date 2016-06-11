package pl.kodujdlapolski.na4lapy.payments.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Address {

    @SerializedName("street_house")
    private String streetHouse;

    @SerializedName("city")
    private String city;

    @SerializedName("state")
    private String state;

    @SerializedName("zip")
    private String zip;

    @SerializedName("country_code")
    private String countryCode;
}
