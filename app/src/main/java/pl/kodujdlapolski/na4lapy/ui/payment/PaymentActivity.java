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

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.presenter.payment.PaymentContract;
import pl.kodujdlapolski.na4lapy.presenter.payment.PaymentPresenter;

public class PaymentActivity extends AppCompatActivity implements PaymentContract.View {

    public final static String FRAGMENT_TAG = "PaymentActivity.FRAGMENT_TAG";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.content)
    FrameLayout fragmentContainer;

    private PaymentContract.UserActionListener listener;
    private String[] pageTitles;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        pageTitles = getResources().getStringArray(R.array.paymentPageTitles);
        listener = new PaymentPresenter(this, this);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG) instanceof PaymentSuccessFragment
                || getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG) instanceof PaymentAmountChooserFragment) {
            listener.closePaymentScreen();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void setPage(int page, PaymentContract.UserActionListener listener) {
        switch (page) {
            case PaymentContract.PAGE_AMOUNT_CHOOSER:
                setFragment(PaymentAmountChooserFragment.newInstance(listener, page), String.valueOf(PaymentContract.PAGE_AMOUNT_CHOOSER));
                break;
            case PaymentContract.PAGE_PAYMENT_TYPE_CHOOSER:
                setFragment(PaymentTypeChooserFragment.newInstance(listener, page), String.valueOf(PaymentContract.PAGE_PAYMENT_TYPE_CHOOSER));
                break;
            case PaymentContract.PAGE_USER_FORM:
                setFragment(PaymentUserFormFragment.newInstance(listener, page), String.valueOf(PaymentContract.PAGE_USER_FORM));
                break;
            case PaymentContract.PAGE_CONFIRMATION:
                setFragment(PaymentConfirmationFragment.newInstance(listener, page), null);
                break;
            case PaymentContract.PAGE_BANK_TRANSFER:
                setFragment(PaymentBankFragment.newInstance(listener, page), null);
                break;
            case PaymentContract.PAGE_PAYMENT_SUCCESS:
                setFragment(PaymentSuccessFragment.newInstance(listener, page), null);
                break;
        }
    }

    @Override
    public void setTitle(int page) {
        toolbar.setTitle(pageTitles[page]);
        if (page < PaymentContract.PAGE_PAYMENT_SUCCESS) {
            toolbar.setSubtitle(getString(R.string.paymentStep, page + 1));
        } else {
            toolbar.setSubtitle(R.string.paymentConfirmation);
        }
    }

    @Override
    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showConnectionErrorAndFinish() {
        Toast.makeText(this, R.string.payment_no_connection, Toast.LENGTH_LONG).show();
        finish();
    }

    private void setFragment(Fragment fragment, String name) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fade_in_short, R.anim.fade_out_short, R.anim.slide_in_left_short, R.anim.slide_out_right_short)
                .addToBackStack(name)
                .replace(R.id.content, fragment, FRAGMENT_TAG)
                .commit();
    }
}
