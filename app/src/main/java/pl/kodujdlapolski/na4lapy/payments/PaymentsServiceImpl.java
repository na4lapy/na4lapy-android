package pl.kodujdlapolski.na4lapy.payments;

import pl.kodujdlapolski.na4lapy.payments.api.PayLaneApi;
import pl.kodujdlapolski.na4lapy.payments.model.Payment;
import pl.kodujdlapolski.na4lapy.payments.model.PaymentResponse;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PaymentsServiceImpl implements PaymentsService {

    private PayLaneApi mPayLaneApi;

    public PaymentsServiceImpl(PayLaneApi payLaneApi) {
        mPayLaneApi = payLaneApi;
    }

    @Override
    public Observable<PaymentResponse> initBankTransfer(Payment payment) {
        payment.getSale().setDescription(PRODUCT);
        payment.getCustomer().setIp(IP);
        payment.getCustomer().getAddress().setCountryCode(COUNTRY_CODE);
        payment.setBackUrl(BACK_URL);
        payment.getSale().setCurrency(CURRENCY);
        return mPayLaneApi.bankTransfer(payment).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }
}
