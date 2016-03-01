package pl.kodujdlapolski.na4lapy.ui.about_app;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.presenter.WebPageTypes;

/**
 * Created by Natalia on 2016-03-01.
 */
public class AboutAppFragment extends Fragment {
    @Bind(R.id.version_name)
    TextView versionName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_app, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            versionName.setText(
                    String.format(getResources().getString(R.string.version),
                            getActivity().getPackageManager()
                                    .getPackageInfo(getActivity().getPackageName(), 0).versionName));
        } catch (PackageManager.NameNotFoundException e) {
            versionName.setVisibility(View.GONE);
        }
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.privacy_policy)
    void onPrivacyPolicyClick() {
        runWebViewActivity(WebPageTypes.POLICY);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.regulations)
    void onRegulationsClick() {
        runWebViewActivity(WebPageTypes.REGULATIONS);
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
