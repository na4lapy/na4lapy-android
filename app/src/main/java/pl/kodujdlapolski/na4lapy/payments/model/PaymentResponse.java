package pl.kodujdlapolski.na4lapy.payments.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class PaymentResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("id_sale")
    private int saleId;

    @SerializedName("redirect_url")
    private String url;
}
