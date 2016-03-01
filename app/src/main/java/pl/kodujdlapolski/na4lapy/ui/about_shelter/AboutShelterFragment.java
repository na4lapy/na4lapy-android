package pl.kodujdlapolski.na4lapy.ui.about_shelter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.presenter.AboutShelterPresenter;

/**
 * Created by Natalia on 2016-03-01.
 */
public class AboutShelterFragment extends Fragment {
    private AboutShelterPresenter presenter;
    @Bind(R.id.shelter_account_number)
    TextView shelterAccountNumber;
    @Bind(R.id.shelter_address)
    TextView shelterAddress;
    @Bind(R.id.shelter_email_address)
    TextView shelterEmailAddress;
    @Bind(R.id.shelter_phone_number)
    TextView shelterPhoneNumber;
    @Bind(R.id.shelter_www)
    TextView shelterWWW;

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
        android.support.v7.app.ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(String.format(getString(R.string.about_shelter_page_title), shelter.getName()));
        }
        shelterAccountNumber.setText(shelter.getAccountNumber());
        shelterWWW.setText(shelter.getWebsite());
        shelterEmailAddress.setText(shelter.getEmail());
        shelterPhoneNumber.setText(shelter.getPhoneNumber());
        shelterAddress.setText(String.format(getString(R.string.shelter_address_template),
                shelter.getStreet(),
                shelter.getBuildingNumber(),
                shelter.getPostalCode(),
                shelter.getCity()));
    }
}
