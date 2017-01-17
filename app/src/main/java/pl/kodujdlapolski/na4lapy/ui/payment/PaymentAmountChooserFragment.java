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
import pl.kodujdlapolski.na4lapy.presenter.payment.PaymentContract;
import pl.kodujdlapolski.na4lapy.service.payments.PaymentsService;

public class PaymentAmountChooserFragment extends PaymentFragment {

    @BindView(R.id.shelterAddressText)
    TextView shelterText;

    public static PaymentAmountChooserFragment newInstance(PaymentContract.UserActionListener listener, int pageNumber) {
        PaymentAmountChooserFragment fragment = new PaymentAmountChooserFragment();
        fragment.setListener(listener);
        fragment.setPageNumber(pageNumber);
        return fragment;
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
