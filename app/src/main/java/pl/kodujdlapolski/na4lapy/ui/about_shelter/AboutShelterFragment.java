package pl.kodujdlapolski.na4lapy.ui.about_shelter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.presenter.about_shelter.AboutShelterPresenter;

/**
 * Created by Natalia Wróblewska on 2016-03-01.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
public class AboutShelterFragment extends Fragment {

    private AboutShelterPresenter presenter;
    private ActionBar actionBar;

    @BindView(R.id.shelter_account_label)
    TextView shelterAccountLabel;
    @BindView(R.id.shelter_account_number)
    TextView shelterAccountNumber;

    @BindView(R.id.shelter_address_label)
    TextView shelterAddressLabel;
    @BindView(R.id.shelter_address)
    TextView shelterAddress;

    @BindView(R.id.shelter_email_label)
    TextView shelterEmailLabel;
    @BindView(R.id.shelter_email_address)
    TextView shelterEmailAddress;

    @BindView(R.id.shelter_phone_label)
    TextView shelterPhoneLabel;
    @BindView(R.id.shelter_phone_number)
    TextView shelterPhoneNumber;

    @BindView(R.id.shelter_www_label)
    TextView shelterWwwLabel;
    @BindView(R.id.shelter_www)
    TextView shelterWWW;

    @BindView(R.id.shelter_adoption_rules)
    TextView adoptionRules;


    @BindView(R.id.about_shelter_progress)
    ProgressBar progressBar;
    @BindView(R.id.about_shelter_content)
    ScrollView aboutShelterContent;

    @BindView(R.id.error_container)
    LinearLayout errorContainer;

    @SuppressWarnings("unused")
    @OnClick(R.id.try_again_btn)
    void onTryAgainClick() {
        errorContainer.setVisibility(View.GONE);
        presenter.startDownloadingData();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_shelter, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new AboutShelterPresenter(this);
    }

    public void populateView(Shelter shelter) {
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(String.format(getString(R.string.about_shelter_page_title), shelter.getName()));
        }
        shelterAccountNumber.setText(getFormattedAccount(shelter.getAccountNumber()));
        shelterWWW.setText(shelter.getWebsite());
        shelterEmailAddress.setText(shelter.getEmail());
        shelterPhoneNumber.setText(shelter.getPhoneNumber());
        if (shelter.getStreet() != null && shelter.getBuildingNumber() != null && shelter.getPostalCode() != null && shelter.getCity() != null) {
            shelterAddress.setText(String.format(getString(R.string.shelter_address_template),
                    shelter.getStreet(),
                    shelter.getBuildingNumber(),
                    shelter.getPostalCode(),
                    shelter.getCity()));
        }
        adoptionRules.setText(shelter.getAdoptionRules());
    }

    private String getFormattedAccount(String accountNumber) {
        if (accountNumber != null) {
            String accountPart1 = accountNumber.substring(0, 2);
            String accountPart2 = accountNumber.substring(2);
            String shelterFormatted = splitStringEvery(accountPart2, 4);
            return accountPart1 + " " + shelterFormatted;
        }
        return "";
    }

    private String splitStringEvery(String s, int interval) {
        StringBuilder result = new StringBuilder();
        int arrayLength = (int) Math.ceil(((s.length() / (double) interval)));
        int lastIndex = arrayLength - 1;

        int j = 0;
        for (int i = 0; i < lastIndex; i++) {
            result.append(s.substring(j, j + interval));
            result.append(" ");
            j += interval;
        }
        result.append(s.substring(j));
        return result.toString();
    }

    public void showProgressHideContent(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        aboutShelterContent.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    public void showError() {
        progressBar.setVisibility(View.GONE);
        aboutShelterContent.setVisibility(View.GONE);
        errorContainer.setVisibility(View.VISIBLE);
    }


    public String getFormattedInfoText() {
        String result = "";

        result += (shelterAddressLabel.getText());
        result += ("\n");
        result += (shelterAddress.getText());
        result += ("\n\n");

        result += (shelterEmailLabel.getText());
        result += ("\n");
        result += (shelterEmailAddress.getText());
        result += ("\n\n");

        result += (shelterPhoneLabel.getText());
        result += ("\n");
        result += (shelterPhoneNumber.getText());
        result += ("\n\n");

        result += (shelterWwwLabel.getText());
        result += ("\n");
        result += (shelterWWW.getText());
        result += ("\n\n");

        result += (shelterAccountLabel.getText());
        result += ("\n");
        result += (shelterAccountNumber.getText());

        return result;
    }

    public String getFormattedTitle() {
        if (actionBar != null && actionBar.getTitle() != null) {
            return actionBar.getTitle().toString();
        } else return "";
    }
}
