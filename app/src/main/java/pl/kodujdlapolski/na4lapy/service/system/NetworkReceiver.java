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
