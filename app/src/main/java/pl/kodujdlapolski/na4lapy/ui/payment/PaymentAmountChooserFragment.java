package pl.kodujdlapolski.na4lapy.ui.payment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.base.Joiner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.payments.PaymentsService;
import pl.kodujdlapolski.na4lapy.presenter.payment.PaymentContract;

public class PaymentAmountChooserFragment extends PaymentFragment {

    @BindView(R.id.shelterAddressText)
    TextView shelterText;

    public static PaymentAmountChooserFragment newInstance(PaymentContract.UserActionListener listener, int pageNumber) {
        PaymentAmountChooserFragment f = new PaymentAmountChooserFragment();
        f.setListener(listener);
        f.setPageNumber(pageNumber);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_payment_amountchooser, container, false);
        ButterKnife.bind(this, rootView);
        Shelter shelter = getListener().getShelter();
        if (shelter != null) {
            shelterText.setText(Joiner.on("\n").skipNulls()
                    .join(shelter.getName(), shelter.getStreet() + " " + shelter.getBuildingNumber(),
                            shelter.getPostalCode() + " " + shelter.getCity()));
        }
        return rootView;
    }

    @OnClick({ R.id.amountButton1, R.id.amountButton2, R.id.amountButton3 })
    public void chooseAmount(View view) {
        switch (view.getId()) {
            case R.id.amountButton1:
                getListener().setPaymentAmount(PaymentsService.PAYMENT_AMOUNT_1);
                break;
            case R.id.amountButton2:
                getListener().setPaymentAmount(PaymentsService.PAYMENT_AMOUNT_2);
                break;
            case R.id.amountButton3:
                getListener().setPaymentAmount(PaymentsService.PAYMENT_AMOUNT_3);
                break;
        }
    }
}
