package pl.kodujdlapolski.na4lapy.payments.api;

import pl.kodujdlapolski.na4lapy.payments.model.Payment;
import pl.kodujdlapolski.na4lapy.payments.model.PaymentResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface PayLaneApi {

    @POST("banktransfers/sale")
    Observable<PaymentResponse> bankTransfer(@Body Payment payment);
}
