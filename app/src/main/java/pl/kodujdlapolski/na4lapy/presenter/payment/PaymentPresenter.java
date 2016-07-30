/*
 * Copyright (C) 2016 Stowarzyszenie Na4Łapy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.kodujdlapolski.na4lapy.presenter.payment;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.common.collect.Collections2;

import java.util.List;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.Na4LapyApp;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.service.payments.PaymentsService;
import pl.kodujdlapolski.na4lapy.service.payments.model.Customer;
import pl.kodujdlapolski.na4lapy.service.payments.model.Payment;
import pl.kodujdlapolski.na4lapy.service.payments.model.PaymentResponse;
import pl.kodujdlapolski.na4lapy.service.payments.model.Sale;
import pl.kodujdlapolski.na4lapy.service.payments.model.type.PaymentType;
import pl.kodujdlapolski.na4lapy.service.preferences.PreferencesService;
import pl.kodujdlapolski.na4lapy.service.repository.RepositoryService;
import pl.kodujdlapolski.na4lapy.service.system.SystemService;
import pl.kodujdlapolski.na4lapy.utils.formvalidator.FormValidator;
import pl.kodujdlapolski.na4lapy.utils.formvalidator.ValidationError;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Marek Wojtuszkiewicz
 */
public class PaymentPresenter implements PaymentContract.UserActionListener {

    @Inject
    PaymentsService paymentsService;

    @Inject
    PreferencesService preferencesService;

    @Inject
    SystemService systemService;

    @Inject
    RepositoryService repositoryService;

    private Shelter shelter;
    private PaymentContract.View mView;
    private Payment payment;
    private Activity mActivity;
    private PaymentResponse paymentResponse;

    public PaymentPresenter(Activity parent, PaymentContract.View view) {
        ((Na4LapyApp)parent.getApplication()).getComponent().inject(this);
        mActivity = parent;
        mView = view;
        payment = create();
        repositoryService.getShelter(parent.getIntent().getLongExtra(PaymentContract.KEY_SHELTER_ID, -1))
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    setShelter(s);
                    mView.setPage(PaymentContract.PAGE_AMOUNT_CHOOSER, this);
                }, t -> parent.finish());
    }

    @Override
    public void setPaymentAmount(double amount) {
        payment.getSale().setAmount(amount);
        mView.setPage(PaymentContract.PAGE_PAYMENT_TYPE_CHOOSER, this);
    }

    @Override
    public void setPaymentType(@NonNull PaymentType paymentType) {
        payment.setPaymentType(paymentType);
        mView.setPage(PaymentContract.PAGE_USER_FORM, this);
    }

    @Override
    public void saveCustomerAndGoToNextPage(@Nullable Customer customer) {
        payment.setCustomer(customer);
        preferencesService.setCustomer(customer);
        mView.hideKeyboard();
        mView.setPage(PaymentContract.PAGE_CONFIRMATION, this);
    }

    @Override
    public void makePayment() {
        if (!systemService.isOnline()) {
            Toast.makeText(mActivity, R.string.payment_no_connection, Toast.LENGTH_LONG).show();
            return;
        }

        paymentsService.initBankTransfer(payment)
                .subscribe(
                        r -> {
                            paymentResponse = r;
                            mView.setPage(PaymentContract.PAGE_BANK_TRANSFER, this);
                        },
                        t -> {
                            Toast.makeText(mActivity, R.string.paymentsRequestError, Toast.LENGTH_SHORT).show();
                            Log.e(getClass().getSimpleName(), "Error when processing bank transfer", t);
                        }
                );
    }

    @Override
    public void onPaymentCompleted() {
        mView.setPage(PaymentContract.PAGE_PAYMENT_SUCCESS, this);
    }

    @Override
    public void closePaymentScreen() {
        mActivity.finish();
    }

    @Nullable
    @Override
    public Shelter getShelter() {
        return shelter;
    }

    @Nullable
    @Override
    public Payment getPayment() {
        return payment;
    }

    @Nullable
    @Override
    public Customer getCustomer() {
        return payment.getCustomer();
    }

    @Nullable
    @Override
    public PaymentResponse getPaymentResponse() {
        return paymentResponse;
    }

    @Override
    public void validateAndSubmitUserForm(FormValidator validator, Customer customer) {
        List<ValidationError> errors = validator.validateForm();

        if (!checkUserAcceptedPaymentTerms(errors)) {
            mView.showAcceptRequirementDialog();
        }

        if (errors.isEmpty()) {
            saveCustomerAndGoToNextPage(customer);
        }
    }

    @Override
    public void showPaymentTerms() {
        mView.showPaymentTermsDialog();
    }

    private Payment create() {
        Payment payment = new Payment();
        payment.setSale(new Sale());
        payment.setCustomer(preferencesService.getCustomer());
        return payment;
    }

    private void setShelter(Shelter s) {
        shelter = s;
    }

    private boolean checkUserAcceptedPaymentTerms(List<ValidationError> errors) {
        return Collections2
                .filter(errors, e -> e.getValidationCode() == PaymentContract.FORM_VALIDATION_PAYMENT_TERMS)
                .isEmpty();
    }
}
