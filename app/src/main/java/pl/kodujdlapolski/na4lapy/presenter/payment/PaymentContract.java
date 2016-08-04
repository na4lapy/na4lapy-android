package pl.kodujdlapolski.na4lapy.presenter.payment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.service.payments.model.Customer;
import pl.kodujdlapolski.na4lapy.service.payments.model.Payment;
import pl.kodujdlapolski.na4lapy.service.payments.model.PaymentResponse;
import pl.kodujdlapolski.na4lapy.service.payments.model.type.PaymentType;

public interface PaymentContract {

    String KEY_SHELTER_ID = "pl.kodujdlapolski.na4lapy.presenter.payment.PaymentContract.KEY_SHELTER_ID";

    int PAGE_AMOUNT_CHOOSER = 0;
    int PAGE_PAYMENT_TYPE_CHOOSER = 1;
    int PAGE_USER_FORM = 2;
    int PAGE_CONFIRMATION = 3;
    int PAGE_BANK_TRANSFER = 4;
    int PAGE_PAYMENT_SUCCESS = 5;

    interface View {
        void setPage(int page, PaymentContract.UserActionListener listener);
        void setTitle(int page);
        void hideKeyboard();
        void showConnectionErrorAndFinish();
    }

    interface UserActionListener{
        void setPaymentAmount(double amount);
        void setPaymentType(@NonNull PaymentType paymentType);
        void saveCustomer(@Nullable Customer paymentUser);
        void makePayment();
        void onPaymentCompleted();
        void closePaymentScreen();

        @Nullable
        Shelter getShelter();

        @Nullable
        Payment getPayment();

        @Nullable
        Customer getCustomer();

        @Nullable
        PaymentResponse getPaymentResponse();
    }
}
