/*
 *	Copyright 2017 Stowarzyszenie Na4Łapy
 *
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *	http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */
package pl.kodujdlapolski.na4lapy.service.payments;

import pl.kodujdlapolski.na4lapy.service.payments.api.PayLaneApi;
import pl.kodujdlapolski.na4lapy.service.payments.model.Payment;
import pl.kodujdlapolski.na4lapy.service.payments.model.PaymentResponse;
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
