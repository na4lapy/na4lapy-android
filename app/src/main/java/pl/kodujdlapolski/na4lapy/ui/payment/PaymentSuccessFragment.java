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
