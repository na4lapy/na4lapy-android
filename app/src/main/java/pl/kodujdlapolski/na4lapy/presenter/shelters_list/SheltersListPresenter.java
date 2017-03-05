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
package pl.kodujdlapolski.na4lapy.presenter.shelters_list;

import java.util.List;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.Na4LapyApp;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.service.repository.RepositoryService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SheltersListPresenter {
    @Inject
    RepositoryService repositoryService;
    private SheltersListContract.View view;

    public SheltersListPresenter(SheltersListContract.View view) {
        this.view = view;
        ((Na4LapyApp) view.getActivity().getApplication()).getComponent().inject(this);
        startDownloadingData();
    }

    private void startDownloadingData() {
        view.showStateWaitingForData();
        getData();
    }
    private void onDataAvailable(List<Shelter> shelters) {
        if (shelters != null && !shelters.isEmpty()) {
            view.populateView(shelters);
            view.showStateDataIsAvailable();
        } else {
            view.showStateDataIsEmpty();
        }
    }
    private void getData() {
        repositoryService.getShelters()
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onDataAvailable, view::showStateError);
    }
}
