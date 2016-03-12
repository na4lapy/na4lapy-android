package pl.kodujdlapolski.na4lapy.sync;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.api.ApiService;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.repository.database.DatabaseRepository;

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
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    Shelter shelter = mApiService.getShelter();
                    mDatabaseRepository.save(shelter);
                    List<Animal> animalList = mApiService.getAnimalList();
                    mDatabaseRepository.saveAll(animalList);
                } catch (Exception e) {
                    Log.w(getClass().getSimpleName(), e);
                    return false;
                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean success) {
                Intent broadcastIntent = new Intent(SYNCHRONIZATION_FINISHED_ACTION);
                broadcastIntent.putExtra(SYNCHRONIZATION_RESULT_KEY, success);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(broadcastIntent);
            }
        }.execute();
    }
}
