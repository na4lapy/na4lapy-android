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
package pl.kodujdlapolski.na4lapy;

import javax.inject.Singleton;

import dagger.Component;
import pl.kodujdlapolski.na4lapy.presenter.about_shelter.AboutShelterPresenter;
import pl.kodujdlapolski.na4lapy.presenter.details.AnimalDetailsPresenter;
import pl.kodujdlapolski.na4lapy.presenter.payment.PaymentPresenter;
import pl.kodujdlapolski.na4lapy.presenter.preferences.PreferencesPresenter;
import pl.kodujdlapolski.na4lapy.presenter.shelters_list.SheltersListPresenter;
import pl.kodujdlapolski.na4lapy.service.api.ApiModule;
import pl.kodujdlapolski.na4lapy.service.payments.PaymentsModule;
import pl.kodujdlapolski.na4lapy.service.preferences.PreferencesModule;
import pl.kodujdlapolski.na4lapy.service.repository.RepositoryModule;
import pl.kodujdlapolski.na4lapy.service.system.SystemModule;
import pl.kodujdlapolski.na4lapy.service.user.UserModule;
import pl.kodujdlapolski.na4lapy.ui.browse.BrowsePresenter;
import pl.kodujdlapolski.na4lapy.ui.browse.list.ListBrowseFragment;
import pl.kodujdlapolski.na4lapy.ui.details.ContentDetailsView;
import pl.kodujdlapolski.na4lapy.ui.details.DetailsActivity;
import pl.kodujdlapolski.na4lapy.ui.drawer.DrawerActivityHandler;
import pl.kodujdlapolski.na4lapy.ui.settings.SettingsFragment;
import pl.kodujdlapolski.na4lapy.ui.splash.SplashActivity;

@Singleton
@Component(modules = {
        Na4LapyModule.class,
        RepositoryModule.class,
        ApiModule.class,
        PaymentsModule.class,
        PreferencesModule.class,
        SystemModule.class,
        UserModule.class
})
public interface Na4LapyComponent {

    void inject(AboutShelterPresenter __);
    void inject(SheltersListPresenter __);
    void inject(SplashActivity __);
    void inject(BrowsePresenter __);
    void inject(PreferencesPresenter __);
    void inject(DetailsActivity __);
    void inject(ListBrowseFragment __);
    void inject(DrawerActivityHandler __);
    void inject(SettingsFragment __);
    void inject(PaymentPresenter __);
    void inject(ContentDetailsView __);
}
