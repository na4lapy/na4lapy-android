package pl.kodujdlapolski.na4lapy.presenter.about_shelter;

import android.content.Intent;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.Na4LapyApp;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.repository.RepositoryService;
import pl.kodujdlapolski.na4lapy.ui.about_shelter.AboutShelterActivity;
import pl.kodujdlapolski.na4lapy.ui.about_shelter.AboutShelterFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Natalia on 2016-03-01.
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
 * Modified by Marek Wojtuszkiewicz on 2016-04-06
 */
public class AboutShelterPresenter {

    @Inject
    RepositoryService repositoryService;

    private AboutShelterFragment aboutShelterFragment;
    private AboutShelterActivity aboutShelterActivity;
    private final Long shelterId;
    private Shelter shelter;
    private boolean isAfterSynchronization = false;

    public AboutShelterPresenter(AboutShelterFragment aboutShelterFragment) {
        this.aboutShelterFragment = aboutShelterFragment;
        aboutShelterActivity = (AboutShelterActivity) aboutShelterFragment.getActivity();
        shelterId = aboutShelterActivity.getShelterId();
        ((Na4LapyApp) aboutShelterActivity.getApplication()).getComponent().inject(this);
        startDownloadingData();
    }

    public void startDownloadingData() {
        aboutShelterFragment.showProgressHideContent(true);
        isAfterSynchronization = false;
        getData();
    }

    private void onShelterAvailable() {
        if (shelter != null) {
            ((AboutShelterActivity) aboutShelterFragment.getActivity()).setShareIntent(getShareIntent());
            aboutShelterFragment.populateView(shelter);
            aboutShelterFragment.showProgressHideContent(false);
        } else {
            if (isAfterSynchronization) {
                aboutShelterFragment.showError();
            } else {
                aboutShelterFragment.showProgressHideContent(true);
            }
        }
    }

    private Intent getShareIntent() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, aboutShelterFragment.getFormattedTitle());
        sharingIntent.putExtra(Intent.EXTRA_TEXT, aboutShelterFragment.getFormattedInfoText());
        return sharingIntent;
    }

    private void getData() {
        repositoryService.getShelter(shelterId)
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    shelter = s;
                    onShelterAvailable();
                },
                t -> {/*TODO obsłużyć błąd*/});
    }
}
