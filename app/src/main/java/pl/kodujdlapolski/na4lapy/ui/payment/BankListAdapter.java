package pl.kodujdlapolski.na4lapy.ui.payment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.presenter.payment.PaymentContract;
import pl.kodujdlapolski.na4lapy.service.payments.model.type.PaymentType;

public class BankListAdapter extends RecyclerView.Adapter<BankListAdapter.ViewHolder> {

    private static PaymentContract.UserActionListener mListener;
    private PaymentType[] paymentTypes;

    public BankListAdapter(PaymentContract.UserActionListener listener) {
        mListener = listener;
        paymentTypes = PaymentType.values();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_payment_type, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(paymentTypes[position]);
    }

    @Override
    public int getItemCount() {
        return paymentTypes.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.icon)
        ImageView iconView;

        @BindView(R.id.name)
        TextView nameView;

        private PaymentType mPaymentType;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setData(PaymentType paymentType) {
            mPaymentType = paymentType;
            iconView.setImageResource(paymentType.getDrawableResId());
            nameView.setText(paymentType.getNameResourceId());
        }

        @Override
        public void onClick(View v) {
            mListener.setPaymentType(mPaymentType);
        }
    }
}
