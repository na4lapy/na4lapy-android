package pl.kodujdlapolski.na4lapy.payments.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import pl.kodujdlapolski.na4lapy.payments.model.type.PaymentType;

@Data
public class Payment {

    @SerializedName("sale")
    private Sale sale;

    @SerializedName("customer")
    private Customer customer;

    @SerializedName("back_url")
    private String backUrl;

    @SerializedName("payment_type")
    private PaymentType paymentType;
}
