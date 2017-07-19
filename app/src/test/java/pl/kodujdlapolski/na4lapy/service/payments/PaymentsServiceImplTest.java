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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.kodujdlapolski.na4lapy.service.payments.api.PayLaneApi;
import pl.kodujdlapolski.na4lapy.service.payments.model.Address;
import pl.kodujdlapolski.na4lapy.service.payments.model.Customer;
import pl.kodujdlapolski.na4lapy.service.payments.model.Payment;
import pl.kodujdlapolski.na4lapy.service.payments.model.PaymentResponse;
import pl.kodujdlapolski.na4lapy.service.payments.model.Sale;
import rx.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PaymentsServiceImplTest {

    @Mock
    private PayLaneApi payLaneApi;

    @Mock
    private Observable<PaymentResponse> observable;

    @Captor
    private ArgumentCaptor<Payment> paymentCaptor;

    @InjectMocks
    private PaymentsServiceImpl paymentsService;

    @Test
    public void testInitBankTransfer() throws Exception {
        Address address = new Address();
        Customer customer = new Customer();
        customer.setAddress(address);
        Sale sale = new Sale();
        Payment payment = new Payment();
        payment.setSale(sale);
        payment.setCustomer(customer);
        when(payLaneApi.bankTransfer(payment)).thenReturn(observable);

        Observable<PaymentResponse> result = paymentsService.initBankTransfer(payment);
        verify(payLaneApi).bankTransfer(payment);
    }
}