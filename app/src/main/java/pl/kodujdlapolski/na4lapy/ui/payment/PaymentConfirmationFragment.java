package pl.kodujdlapolski.na4lapy.ui.payment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.base.Joiner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.presenter.payment.PaymentContract;
import pl.kodujdlapolski.na4lapy.service.payments.model.Address;
import pl.kodujdlapolski.na4lapy.service.payments.model.Customer;
import pl.kodujdlapolski.na4lapy.service.payments.model.Payment;

public class PaymentConfirmationFragment extends PaymentFragment {

    @BindView(R.id.userDataText)
    TextView userDataText;

    @BindView(R.id.amountText)
    TextView amountText;

    @BindView(R.id.paymentTypeIcon)
    ImageView paymentTypeIcon;

    public static PaymentConfirmationFragment newInstance(PaymentContract.UserActionListener listener, int pageNumber) {
        PaymentConfirmationFragment fragment = new PaymentConfirmationFragment();
        fragment.setListener(listener);
        fragment.setPageNumber(pageNumber);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_payment_confirmation, container, false);
        ButterKnife.bind(this, rootView);
        Payment payment = getListener().getPayment();
        Customer customer = payment.getCustomer();
        Address address = customer.getAddress();

        String text = Joiner.on("\n").skipNulls()
                .join(customer.getName(), address.getStreetHouse(), address.getZip() + " " + address.getCity(), customer.getEmail());
        userDataText.setText(text);

        amountText.setText(getString(R.string.paymentAmountValue, (int)payment.getSale().getAmount()));
        paymentTypeIcon.setImageResource(payment.getPaymentType().getDrawableResId());
        return rootView;
    }

    @OnClick(R.id.makePaymentButton)
    public void chooseAmount() {
        getListener().makePayment();
    }

    @OnClick(R.id.amountCard)
    public void editAmount() {
        getActivity().getSupportFragmentManager()
                .popBackStack(String.valueOf(PaymentContract.PAGE_AMOUNT_CHOOSER), 0);
    }

    @OnClick(R.id.paymentTypeCard)
    public void editPaymentType() {
        getActivity().getSupportFragmentManager()
                .popBackStack(String.valueOf(PaymentContract.PAGE_PAYMENT_TYPE_CHOOSER), 0);
    }

    @OnClick(R.id.userDataCard)
    public void editUserData() {
        getActivity().getSupportFragmentManager()
                .popBackStack(String.valueOf(PaymentContract.PAGE_USER_FORM), 0);
    }
}
