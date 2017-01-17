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

package pl.kodujdlapolski.na4lapy.ui.settings;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.presenter.settings.WebPageTypes;
import pl.kodujdlapolski.na4lapy.ui.AbstractSingleActivity;

public class WebViewActivity extends AbstractSingleActivity {

    public static final String EXTRA_TYPE = "WebViewActivity.EXTRA_TYPE";
    @BindView(R.id.webView)
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
        WebSettings webSettings = webView.getSettings();
        // used for tests
        webSettings.setJavaScriptEnabled(true);
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
