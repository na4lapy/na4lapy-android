package pl.kodujdlapolski.na4lapy.service.payments.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Customer {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("ip")
    private String ip;

    @SerializedName("address")
    private Address address;
}
