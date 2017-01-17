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
