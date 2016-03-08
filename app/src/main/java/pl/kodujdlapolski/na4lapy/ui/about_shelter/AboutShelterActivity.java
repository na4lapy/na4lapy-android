package pl.kodujdlapolski.na4lapy.ui.about_shelter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.ui.AbstractSingleActivity;

public class AboutShelterActivity extends AbstractSingleActivity {

    public static final String EXTRA_SHELTER_ID = "EXTRA_SHELTER_ID";
    private Long shelterId;
    private ShareActionProvider mShareActionProvider;
    private Intent mShareIntent;
    AboutShelterFragment aboutShelterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_shelter);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            shelterId = b.getLong(EXTRA_SHELTER_ID);
        }
        if (shelterId == null && savedInstanceState != null) {
            shelterId = savedInstanceState.getLong(EXTRA_SHELTER_ID);
        }
        if (shelterId == null)
            finish(); // as nothing can be done without it
        aboutShelterFragment = (AboutShelterFragment) getSupportFragmentManager().findFragmentById(R.id.about_shelter_app_fragment);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putLong(EXTRA_SHELTER_ID, shelterId);
        super.onSaveInstanceState(outState);
    }

    public Long getShelterId() {
        return shelterId;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_shelter_menu, menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        if (mShareIntent != null) {
            mShareActionProvider.setShareIntent(mShareIntent);
        }
        return true;
    }

    public void setShareIntent(Intent shareIntent) {
        mShareIntent = shareIntent;
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        aboutShelterFragment.onActivityStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        aboutShelterFragment.onActivityStop();
    }
}
