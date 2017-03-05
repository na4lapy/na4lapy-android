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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.presenter.shelters_list.SheltersListContract;
import pl.kodujdlapolski.na4lapy.presenter.shelters_list.SheltersListPresenter;

public class SheltersListFragment extends Fragment implements SheltersListContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shelters_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new SheltersListPresenter(this);
    }

    @Override
    public void showStateWaitingForData() {

    }

    @Override
    public void showStateNoInternetConnection() {

    }

    @Override
    public void showStateDataIsAvailable() {

    }

    @Override
    public void showStateDataIsEmpty() {

    }

    @Override
    public void showStateError(Throwable t) {

    }

    @Override
    public void populateView(List<Shelter> shelters) {

    }
}
