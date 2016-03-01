package pl.kodujdlapolski.na4lapy.ui.about_app;

import android.os.Bundle;
import android.webkit.WebView;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.presenter.WebPageTypes;
import pl.kodujdlapolski.na4lapy.ui.AbstractSingleActivity;

public class WebViewActivity extends AbstractSingleActivity {

    public static final String EXTRA_TYPE = "WebViewActivity.EXTRA_TYPE";
    @Bind(R.id.webView)
    WebView webView;
    private WebPageTypes type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        Bundle b = getIntent().getExtras();
        type = (WebPageTypes) b.get(EXTRA_TYPE);
        if (type != null) {
            openUrlAndSetPageName(type);
        } else {
            if (savedInstanceState != null)
                openUrlAndSetPageName((WebPageTypes) savedInstanceState.get(EXTRA_TYPE));
            else
                finish();
        }
    }

    private void openUrlAndSetPageName(WebPageTypes webPageTypes) {
        if (webPageTypes != null) {
            webView.loadUrl(webPageTypes.getUrl());
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle(webPageTypes.getResPageName());
        } else {
            finish();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(EXTRA_TYPE, type);
        super.onSaveInstanceState(outState);
    }
}
