package pl.kodujdlapolski.na4lapy.sync.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import pl.kodujdlapolski.na4lapy.sync.SynchronizationService;

public class SynchronizationReceiver extends BroadcastReceiver {

    private final static IntentFilter SYNC_INTENT_FILTER =
            new IntentFilter(SynchronizationService.SYNCHRONIZATION_FINISHED_ACTION);

    private SynchronizationReceiverCallback mListener;

    public SynchronizationReceiver(SynchronizationReceiverCallback listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean success = intent.getBooleanExtra(SynchronizationService.SYNCHRONIZATION_RESULT_KEY, false);
        if (success) {
            mListener.onSynchronizationSuccess();
        } else {
            mListener.onSynchronizationFail();
        }
    }

    public static IntentFilter getIntentFilter() {
        return SYNC_INTENT_FILTER;
    }

    public interface SynchronizationReceiverCallback {
        void onSynchronizationSuccess();
        void onSynchronizationFail();
    }
}
