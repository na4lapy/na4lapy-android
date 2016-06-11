package pl.kodujdlapolski.na4lapy.ui.payment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.presenter.payment.PaymentContract;

public class PaymentTypeChooserFragment extends PaymentFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static PaymentTypeChooserFragment newInstance(PaymentContract.UserActionListener listener, int pageNumber) {
        PaymentTypeChooserFragment f = new PaymentTypeChooserFragment();
        f.setListener(listener);
        f.setPageNumber(pageNumber);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_payment_type, container, false);
        ButterKnife.bind(this, rootView);
        recyclerView.setAdapter(new BankListAdapter(getListener()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.addItemDecoration(new BankListDecoration(getContext(), 3));
        return rootView;
    }
}
