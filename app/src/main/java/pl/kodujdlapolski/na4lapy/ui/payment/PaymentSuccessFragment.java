package pl.kodujdlapolski.na4lapy.ui.payment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.presenter.payment.PaymentContract;
import pl.kodujdlapolski.na4lapy.service.payments.model.Payment;
import pl.kodujdlapolski.na4lapy.service.payments.model.Sale;

public class PaymentSuccessFragment extends PaymentFragment {

    @BindView(R.id.shelterName)
    TextView shelterName;

    @BindView(R.id.summary)
    TextView summary;

    public static PaymentSuccessFragment newInstance(PaymentContract.UserActionListener listener, int pageNumber) {
        PaymentSuccessFragment fragment = new PaymentSuccessFragment();
        fragment.setListener(listener);
        fragment.setPageNumber(pageNumber);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_payment_success, container, false);
        ButterKnife.bind(this, rootView);
        Payment payment = getListener().getPayment();
        Shelter shelter = getListener().getShelter();
        if (payment !=null && shelter != null) {
            shelterName.setText(shelter.getName());
            Sale sale = payment.getSale();
            summary.setText(getString(R.string.paymentSuccessSummary, (int)payment.getSale().getAmount(), sale.getCurrency()));
        }
        return rootView;
    }

    @OnClick(R.id.close)
    public void closePaymentScreen() {
        getListener().closePaymentScreen();
    }
}
