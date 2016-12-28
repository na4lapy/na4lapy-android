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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import rx.subjects.PublishSubject;

public class NetworkReceiver extends BroadcastReceiver {

    private SystemService mSystemService;
    private PublishSubject<Boolean> mSubject;

    public NetworkReceiver(SystemService systemService, PublishSubject<Boolean> subject) {
        mSystemService = systemService;
        mSubject = subject;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mSubject.onNext(mSystemService.isOnline());
    }

    public static IntentFilter getIntentFilter() {
        return new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    }
}
