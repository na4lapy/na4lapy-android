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
package pl.kodujdlapolski.na4lapy.service.payments.model.type;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import pl.kodujdlapolski.na4lapy.R;

public enum PaymentType {
    AB(R.string.paymentTypeAB, R.drawable.payment_ab),
    AS(R.string.paymentTypeAS, R.drawable.payment_as),
    MT(R.string.paymentTypeMT, R.drawable.payment_mt),
    IN(R.string.paymentTypeIN, R.drawable.payment_in),
    IP(R.string.paymentTypeIP, R.drawable.payment_ip),
    DB(R.string.paymentTypeDB, R.drawable.payment_db),
    MI(R.string.paymentTypeMI, R.drawable.payment_mi),
    CA(R.string.paymentTypeCA, R.drawable.payment_ca),
    PP(R.string.paymentTypePP, R.drawable.payment_pp),
    PCZ(R.string.paymentTypePCZ, R.drawable.payment_pcz),
    BP(R.string.paymentTypeBP, R.drawable.payment_bp),
    IB(R.string.paymentTypeIB, R.drawable.payment_ib),
    PO(R.string.paymentTypePO, R.drawable.payment_po),
    GB(R.string.paymentTypeGB, R.drawable.payment_gb),
    IG(R.string.paymentTypeIG, R.drawable.payment_ig),
    WB(R.string.paymentTypeWB, R.drawable.payment_wb),
    BG(R.string.paymentTypeBG, R.drawable.payment_bg),
    PB(R.string.paymentTypePB, R.drawable.payment_bg),
    OH(R.string.paymentTypeOH, R.drawable.payment_oh);

    private int mNameResId;
    private int mDrawableResId;

    PaymentType(@StringRes int nameResId, @DrawableRes int drawableResId) {
        mNameResId = nameResId;
        mDrawableResId = drawableResId;
    }

    public int getNameResourceId() {
        return mNameResId;
    }

    public int getDrawableResId() {
        return mDrawableResId;
    }
}
