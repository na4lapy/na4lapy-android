package pl.kodujdlapolski.na4lapy.ui.payment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.presenter.payment.PaymentContract;
import pl.kodujdlapolski.na4lapy.service.payments.PaymentsService;
import pl.kodujdlapolski.na4lapy.service.payments.model.PaymentResponse;

public class PaymentBankFragment extends PaymentFragment {

    @BindView(R.id.bankWebView)
    WebView bankWebView;

    @BindView(R.id.progressBar)
    View progressBar;

    public static PaymentBankFragment newInstance(PaymentContract.UserActionListener listener, int pageNumber) {
        PaymentBankFragment fragment = new PaymentBankFragment();
        fragment.setListener(listener);
        fragment.setPageNumber(pageNumber);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_payment_bank, container, false);
        ButterKnife.bind(this, rootView);
        init(bankWebView);
        openBankPage(getListener().getPaymentResponse());
        return rootView;
    }

    public void openBankPage(PaymentResponse paymentResponse) {
        if (paymentResponse == null || !paymentResponse.isSuccess() || TextUtils.isEmpty(paymentResponse.getUrl())) {
            return;
        }

        bankWebView.loadUrl(paymentResponse.getUrl());
    }

    private void init(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains(PaymentsService.BACK_URL)) {
                    getListener().onPaymentCompleted();
                    return true;
                }

                return false;
            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
