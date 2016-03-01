package pl.kodujdlapolski.na4lapy.presenter;

import pl.kodujdlapolski.na4lapy.R;

/**
 * Created by Natalia on 2016-03-01.
 */
public enum WebPageTypes {
    POLICY("file:///android_asset/policy.html", R.string.privacy_policy_text),
    REGULATIONS("file:///android_asset/regulations.html", R.string.regulations_text),
    OPEN_SOURCE("file:///android_asset/opensource.html", R.string.open_source_libs_text);

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
