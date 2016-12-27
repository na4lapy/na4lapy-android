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
package pl.kodujdlapolski.na4lapy.presenter.settings;

import pl.kodujdlapolski.na4lapy.R;

public enum WebPageTypes {
    POLICY("file:///android_asset/policy.html", R.string.privacy_policy_text),
    OPEN_SOURCE("file:///android_asset/opensource.html", R.string.open_source_libs_text),
    PAYMENT_TERMS("file:///android_asset/payments.html", -1);

    private String url;
    private int resPageName;

    WebPageTypes(String url, int resPageName) {
        this.url = url;
        this.resPageName = resPageName;

    }

    public String getUrl() {
        return url;
    }

    public int getResPageName() {
        return resPageName;
    }
}
