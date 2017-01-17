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

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.kodujdlapolski.na4lapy.Na4LapyApp;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.presenter.settings.WebPageTypes;
import pl.kodujdlapolski.na4lapy.service.user.UserService;

public class SettingsFragment extends Fragment {
    @BindView(R.id.version_name)
    TextView versionName;

    @BindView(R.id.app_website_link)
    TextView appWebsiteLink;

    @BindView(R.id.app_facebook_link)
    TextView appFacebookLink;

    @Inject
    UserService userService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((Na4LapyApp) getActivity().getApplication()).getComponent().inject(this);
        try {
            versionName.setText(
                    String.format(getResources().getString(R.string.version),
                            getActivity().getPackageManager()
                                    .getPackageInfo(getActivity().getPackageName(), 0).versionName));
        } catch (PackageManager.NameNotFoundException e) {
            versionName.setVisibility(View.GONE);
        }
        setLinksToWebsites();
    }

    private void setLinksToWebsites() {
        appWebsiteLink.setMovementMethod(LinkMovementMethod.getInstance());
        appWebsiteLink.setLinkTextColor(getResources().getColor(R.color.colorPrimaryDark));
        appFacebookLink.setMovementMethod(LinkMovementMethod.getInstance());
        appFacebookLink.setLinkTextColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.privacy_policy)
    void onPrivacyPolicyClick() {
        runWebViewActivity(WebPageTypes.POLICY);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.openSourceLibs)
    void onOpenSourceClick() {
        runWebViewActivity(WebPageTypes.OPEN_SOURCE);
    }

    void runWebViewActivity(WebPageTypes type) {
        Intent i = new Intent(getActivity(), WebViewActivity.class);
        i.putExtra(WebViewActivity.EXTRA_TYPE, type);
        getActivity().startActivity(i);
    }
}
