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
package pl.kodujdlapolski.na4lapy.presenter.payment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.service.payments.model.Customer;
import pl.kodujdlapolski.na4lapy.service.payments.model.Payment;
import pl.kodujdlapolski.na4lapy.service.payments.model.PaymentResponse;
import pl.kodujdlapolski.na4lapy.service.payments.model.type.PaymentType;
import pl.kodujdlapolski.na4lapy.utils.formvalidator.FormValidator;

public interface PaymentContract {

    String KEY_SHELTER_ID = "pl.kodujdlapolski.na4lapy.presenter.payment.PaymentContract.KEY_SHELTER_ID";

    int PAGE_AMOUNT_CHOOSER = 0;
    int PAGE_PAYMENT_TYPE_CHOOSER = 1;
    int PAGE_USER_FORM = 2;
    int PAGE_CONFIRMATION = 3;
    int PAGE_BANK_TRANSFER = 4;
    int PAGE_PAYMENT_SUCCESS = 5;

    int FORM_VALIDATION_PAYMENT_TERMS = 0;
    int FORM_VALIDATION_TEXT_INPUT = 1;

    interface View {
        void setPage(int page, PaymentContract.UserActionListener listener);

        void setTitle(int page);

        void hideKeyboard();

        void showAcceptRequirementDialog();

        void showPaymentTermsDialog();

        void showConnectionErrorAndFinish();
    }

    interface UserActionListener {
        void setPaymentAmount(double amount);

        void setPaymentType(@NonNull PaymentType paymentType);

        void saveCustomerAndGoToNextPage(@Nullable Customer paymentUser);

        void makePayment();

        void onPaymentCompleted();

        void closePaymentScreen();

        void validateAndSubmitUserForm(FormValidator validator, Customer customer);

        void showPaymentTerms();

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
