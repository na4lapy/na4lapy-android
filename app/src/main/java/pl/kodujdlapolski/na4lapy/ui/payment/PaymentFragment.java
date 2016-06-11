package pl.kodujdlapolski.na4lapy.ui.payment;

import android.support.v4.app.Fragment;

import pl.kodujdlapolski.na4lapy.presenter.payment.PaymentContract;

public abstract class PaymentFragment extends Fragment {

    private PaymentContract.UserActionListener mListener;
    private int mPageNumber;


    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(mPageNumber);
    }

    public void setPageNumber(int pageNumber) {
        mPageNumber = pageNumber;
    }

    public void setListener(PaymentContract.UserActionListener listener) {
        mListener = listener;
    }

    public PaymentContract.UserActionListener getListener() {
        return mListener;
    }
}
