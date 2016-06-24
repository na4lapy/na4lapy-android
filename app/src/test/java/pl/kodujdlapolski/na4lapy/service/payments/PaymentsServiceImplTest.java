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
        // given
        Address address = new Address();
        Customer customer = new Customer();
        customer.setAddress(address);
        Sale sale = new Sale();
        Payment payment = new Payment();
        payment.setSale(sale);
        payment.setCustomer(customer);
        when(payLaneApi.bankTransfer(payment)).thenReturn(observable);

        // when
        Observable<PaymentResponse> result = paymentsService.initBankTransfer(payment);

        // then
        verify(payLaneApi).bankTransfer(payment);
    }
}