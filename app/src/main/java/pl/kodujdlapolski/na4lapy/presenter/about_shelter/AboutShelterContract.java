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

package pl.kodujdlapolski.na4lapy.presenter.about_shelter;

import android.app.Activity;
import android.content.Intent;

import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.ui.DataSynchronization;

public interface AboutShelterContract {
    interface View extends DataSynchronization {

        Long getShelterId();

        Activity getActivity();

        void populateView(Shelter shelter);

        String getFormattedTitle();

        String getFormattedInfoText();

        void setShareIntent(Intent shareIntent);
    }
}
