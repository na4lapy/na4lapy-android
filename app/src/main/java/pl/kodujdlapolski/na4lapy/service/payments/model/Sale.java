package pl.kodujdlapolski.na4lapy.service.payments.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Sale {

    @SerializedName("amount")
    private double amount;

    @SerializedName("currency")
    private String currency;

    @SerializedName("description")
    private String description;
}
