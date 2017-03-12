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

package pl.kodujdlapolski.na4lapy.ui.shelters_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.presenter.shelters_list.SheltersListContract;
import pl.kodujdlapolski.na4lapy.presenter.shelters_list.SheltersListPresenter;
import pl.kodujdlapolski.na4lapy.ui.about_shelter.AboutShelterActivity;

public class SheltersListFragment extends Fragment implements SheltersListContract.View {
    @BindView(R.id.shelters_list) ListView sheltersList;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.error_message)
    TextView errorMessage;
    @BindView(R.id.error_container)
    ViewGroup errorContainer;
    ArrayList<Shelter> shelters = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shelters_list, container, false);
        ButterKnife.bind(this, view);
        initRecycler();
        return view;
    }

    private void initRecycler() {
        sheltersList.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, shelters));
        sheltersList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getActivity(), AboutShelterActivity.class);
            intent.putExtra(AboutShelterActivity.EXTRA_SHELTER_ID, shelters.get(position).getId());
            getActivity().startActivity(intent);
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new SheltersListPresenter(this);
    }

    @Override
    public void showStateWaitingForData() {
        progressBar.setVisibility(View.VISIBLE);
        sheltersList.setVisibility(View.GONE);
        errorContainer.setVisibility(View.GONE);
    }

    @Override
    public void showStateNoInternetConnection() {
        Toast.makeText(getContext(), R.string.error_data_no_internet_connection, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showStateDataIsAvailable() {
        progressBar.setVisibility(View.GONE);
        sheltersList.setVisibility(View.VISIBLE);
        errorContainer.setVisibility(View.GONE);
    }

    @Override
    public void showStateDataIsEmpty() {
        progressBar.setVisibility(View.GONE);
        sheltersList.setVisibility(View.GONE);
        errorContainer.setVisibility(View.VISIBLE);
        errorMessage.setText(R.string.error_no_data);
    }

    @Override
    public void showStateError(Throwable t) {
        Toast.makeText(getActivity(), R.string.error_data_cannot_be_loaded, Toast.LENGTH_LONG).show();
        if (t != null && t.getMessage() != null) {
            Log.d(this.getClass().toString(), t.getMessage());
        }
    }

    @Override
    public void populateView(List<Shelter> sheltersFromServer) {
        this.shelters.clear();
        this.shelters.addAll(sheltersFromServer);
    }
}
