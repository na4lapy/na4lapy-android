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

package pl.kodujdlapolski.na4lapy.ui.payment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.presenter.payment.PaymentContract;
import pl.kodujdlapolski.na4lapy.service.payments.model.Address;
import pl.kodujdlapolski.na4lapy.service.payments.model.Customer;
import pl.kodujdlapolski.na4lapy.utils.formvalidator.FormValidator;
import pl.kodujdlapolski.na4lapy.utils.formvalidator.rule.CheckedRule;
import pl.kodujdlapolski.na4lapy.utils.formvalidator.rule.NotEmptyRule;

/**
 * @author Marek Wojtuszkiewicz
 */
public class PaymentUserFormFragment extends PaymentFragment {

    @BindView(R.id.nameInputLayout)
    TextInputLayout nameInputLayout;

    @BindView(R.id.nameInput)
    TextInputEditText fullNameInput;

    @BindView(R.id.streetHouseInputLayout)
    TextInputLayout streetHouseLayout;

    @BindView(R.id.streetHouseInput)
    TextInputEditText streetHouseInput;

    @BindView(R.id.zipCodeInputLayout)
    TextInputLayout zipCodeLayout;

    @BindView(R.id.zipCodeInput)
    TextInputEditText zipCodeInput;

    @BindView(R.id.cityInputLayout)
    TextInputLayout cityLayout;

    @BindView(R.id.cityInput)
    TextInputEditText cityInput;

    @BindView(R.id.emailInputLayout)
    TextInputLayout emailLayout;

    @BindView(R.id.emailInput)
    TextInputEditText emailInput;

    @BindView(R.id.acceptPaymentTerms)
    CheckBox acceptTerms;

    private FormValidator validator;

    public static PaymentUserFormFragment newInstance(PaymentContract.UserActionListener listener, int pageNumber) {
        PaymentUserFormFragment fragment = new PaymentUserFormFragment();
        fragment.setListener(listener);
        fragment.setPageNumber(pageNumber);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_payment_userform, container, false);
        ButterKnife.bind(this, rootView);
        fullNameInput.addTextChangedListener(new NotEmptyTextWatcher(nameInputLayout));
        streetHouseInput.addTextChangedListener(new NotEmptyTextWatcher(streetHouseLayout));
        zipCodeInput.addTextChangedListener(new NotEmptyTextWatcher(zipCodeLayout));
        cityInput.addTextChangedListener(new NotEmptyTextWatcher(cityLayout));
        emailInput.addTextChangedListener(new NotEmptyTextWatcher(emailLayout));
        setSavedCustomerData(getListener().getCustomer());
        validator = prepareFormValidator();
        return rootView;
    }

    @OnClick(R.id.submitUserForm)
    public void submitUserForm() {
        getListener().validateAndSubmitUserForm(validator, getPaymentUserData());
    }

    @OnClick(R.id.showPaymentTerms)
    public void showPaymentTerms() {
        getListener().showPaymentTerms();
    }

    public void setSavedCustomerData(@Nullable Customer customer) {
        if (customer == null || customer.getAddress() == null) {
            return;
        }
        fullNameInput.setText(customer.getName());
        emailInput.setText(customer.getEmail());
        streetHouseInput.setText(customer.getAddress().getStreetHouse());
        zipCodeInput.setText(customer.getAddress().getZip());
        cityInput.setText(customer.getAddress().getCity());
    }

    private Customer getPaymentUserData() {
        Customer customer = new Customer();
        Address address = new Address();
        customer.setName(fullNameInput.getText().toString());
        customer.setEmail(emailInput.getText().toString());
        address.setStreetHouse(streetHouseInput.getText().toString());
        address.setZip(zipCodeInput.getText().toString());
        address.setCity(cityInput.getText().toString());
        customer.setAddress(address);
        return customer;
    }

    private FormValidator prepareFormValidator() {
        return new FormValidator(getContext())
                .addRule(new CheckedRule(acceptTerms, PaymentContract.FORM_VALIDATION_PAYMENT_TERMS))
                .addRule(new NotEmptyRule(zipCodeInput, null, PaymentContract.FORM_VALIDATION_TEXT_INPUT))
                .addRule(new NotEmptyRule(fullNameInput, nameInputLayout, PaymentContract.FORM_VALIDATION_TEXT_INPUT))
                .addRule(new NotEmptyRule(streetHouseInput, streetHouseLayout, PaymentContract.FORM_VALIDATION_TEXT_INPUT))
                .addRule(new NotEmptyRule(cityInput, cityLayout, PaymentContract.FORM_VALIDATION_TEXT_INPUT))
                .addRule(new NotEmptyRule(emailInput, emailLayout, PaymentContract.FORM_VALIDATION_TEXT_INPUT));
    }

    public class NotEmptyTextWatcher implements TextWatcher {

        private TextInputLayout mLayout;

        public NotEmptyTextWatcher(TextInputLayout layout) {
            mLayout = layout;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count > 0) {
                mLayout.setError(null);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }
}
