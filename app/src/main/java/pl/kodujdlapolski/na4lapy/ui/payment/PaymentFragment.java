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
