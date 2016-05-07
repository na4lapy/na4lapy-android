package pl.kodujdlapolski.na4lapy.sync;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.sql.SQLException;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.api.ApiService;
import pl.kodujdlapolski.na4lapy.repository.database.DatabaseRepository;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SynchronizationServiceImpl implements SynchronizationService {

    private ApiService mApiService;
    private DatabaseRepository mDatabaseRepository;
    private Context mContext;

    @Inject
    public SynchronizationServiceImpl(ApiService apiService, DatabaseRepository databaseRepository, Context context) {
        mApiService = apiService;
        mDatabaseRepository = databaseRepository;
        mContext = context;
    }

    @Override
    public void synchronize() {
        mApiService.getShelter()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                s -> {
                    try {
                        mDatabaseRepository.save(s);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                },
                t -> Log.w(getClass().getSimpleName(), t)
        );
        mApiService.getAnimalList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                l -> {
                    try {
                        mDatabaseRepository.saveAll(l);
                        broadcastSyncResult(true);
                    } catch (Exception e) {
                        Log.w(getClass().getSimpleName(), e);
                        broadcastSyncResult(false);
                    }
                },
                t -> {
                    Log.w(getClass().getSimpleName(), t);
                    broadcastSyncResult(false);
                }
        );
    }

    private void broadcastSyncResult(boolean success) {
        Intent broadcastIntent = new Intent(SYNCHRONIZATION_FINISHED_ACTION);
        broadcastIntent.putExtra(SYNCHRONIZATION_RESULT_KEY, success);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(broadcastIntent);
    }
}
