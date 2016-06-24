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
        if (animal.getPhotos() != null && animal.getPhotos().iterator().hasNext()) {
            sharingIntent.putExtra(Intent.EXTRA_TEXT, animal.getPhotos().iterator().next().getUrl());
        }
        return sharingIntent;
    }
}
