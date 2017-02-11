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
package pl.kodujdlapolski.na4lapy.service.system;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import pl.kodujdlapolski.na4lapy.model.Animal;
import rx.subjects.PublishSubject;

public class SystemServiceImpl implements SystemService {

    private Context mContext;
    private PublishSubject<Boolean> networkStatusPublisher;

    public SystemServiceImpl(Context context) {
        mContext = context;
        networkStatusPublisher = PublishSubject.create();
        mContext.registerReceiver(new NetworkReceiver(this, networkStatusPublisher), NetworkReceiver.getIntentFilter());
    }

    @Override
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    @Override
    public PublishSubject<Boolean> getNetworkStatusPublisher() {
        return networkStatusPublisher;
    }

    @Override
    public <T> Intent getShareIntent(T shareItem) {
        if (shareItem instanceof Animal) {
            return getAnimalShareIntent((Animal)shareItem);
        }

        return null;
    }

    private Intent getAnimalShareIntent(Animal animal) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, animal.getName() + " " + animal.getChipId());
        String url = animal.getProfilePicUrl();
        if (url != null) {
            sharingIntent.putExtra(Intent.EXTRA_TEXT, url);
        }
        return sharingIntent;
    }
}
