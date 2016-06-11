package pl.kodujdlapolski.na4lapy.payments;

import pl.kodujdlapolski.na4lapy.payments.model.Payment;
import pl.kodujdlapolski.na4lapy.payments.model.PaymentResponse;
import rx.Observable;

public interface PaymentsService {

    String PRODUCT = "Na4Łapy";
    String IP = "127.0.0.1";
    String BACK_URL = "http://na4lapy.kodujdlapolski.pl";
    String COUNTRY_CODE = "PL";
    String CURRENCY = "PLN";

    int PAYMENT_AMOUNT_1 = 5;
    int PAYMENT_AMOUNT_2 = 20;
    int PAYMENT_AMOUNT_3 = 50;

    Observable<PaymentResponse> initBankTransfer(Payment payment);
}
