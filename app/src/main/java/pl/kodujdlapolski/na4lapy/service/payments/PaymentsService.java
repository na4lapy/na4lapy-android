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

import pl.kodujdlapolski.na4lapy.service.payments.model.Payment;
import pl.kodujdlapolski.na4lapy.service.payments.model.PaymentResponse;
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
