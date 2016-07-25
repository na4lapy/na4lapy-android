package pl.kodujdlapolski.na4lapy.ui.payment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.CheckBox;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.presenter.payment.PaymentContract;
import pl.kodujdlapolski.na4lapy.presenter.settings.WebPageTypes;
import pl.kodujdlapolski.na4lapy.service.payments.model.Address;
import pl.kodujdlapolski.na4lapy.service.payments.model.Customer;

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

    @BindViews({ R.id.nameInputLayout, R.id.streetHouseInputLayout, R.id.cityInputLayout, R.id.emailInputLayout })
    List<TextInputLayout> notEmptyValidationGroupLayout;

    @BindViews({ R.id.nameInput, R.id.streetHouseInput, R.id.cityInput, R.id.emailInput })
    List<TextInputEditText> notEmptyValidationGroupInput;

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
        return rootView;
    }

    @OnClick(R.id.submitUserForm)
    public void chooseAmount() {
        if (!validateForm()) {
            return;
        }

        if (acceptTerms.isChecked()) {
            getListener().saveCustomer(getPaymentUserData());
        } else {
            showAcceptRequirementDialog();
        }
    }

    @OnClick(R.id.showPaymentTerms)
    public void showPaymentTerms() {
        showPaymentTermsDialog();
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

    private void showAcceptRequirementDialog() {
        new AlertDialog.Builder(getContext(), R.style.SimpleDialog)
                .setMessage(R.string.paymentTermsNotAccepted).setPositiveButton(R.string.buttonClose, null).create().show();
    }

    private void showPaymentTermsDialog() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_payment_terms, null);
        ((WebView)view.findViewById(R.id.webView)).loadUrl(WebPageTypes.PAYMENT_TERMS.getUrl());
        AlertDialog dialog = new AlertDialog.Builder(getActivity(), R.style.SimpleDialog)
                .setView(view).setPositiveButton(R.string.buttonClose, null).create();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.show();
    }

    boolean validateForm() {
        boolean zipCodeIsValid = true;
        if (TextUtils.isEmpty(zipCodeInput.getText().toString())) {
            zipCodeInput.setError(getString(R.string.validation_error_empty));
            zipCodeIsValid = false;
        }

        return zipCodeIsValid && Lists.newArrayList(Iterables.filter(notEmptyValidationGroupInput,
                input -> checkIfEmpty(input, notEmptyValidationGroupLayout.get(notEmptyValidationGroupInput.indexOf(input))))).isEmpty();
    }

    boolean checkIfEmpty(TextInputEditText input, TextInputLayout layout) {
        if (!TextUtils.isEmpty(input.getText().toString())) {
            return false;
        }

        layout.setError(getString(R.string.validation_error_empty));
        return true;
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
