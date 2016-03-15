package pl.kodujdlapolski.na4lapy.ui.main.activity;

import android.os.Bundle;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.Na4LapyApp;
import pl.kodujdlapolski.na4lapy.repository.RepositoryService;
import pl.kodujdlapolski.na4lapy.ui.AbstractDrawerActivity;

public class SplashActivity extends AbstractDrawerActivity {

    @Inject
    RepositoryService repositoryService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Na4LapyApp) getApplication()).getComponent().inject(this);
    }

//todo can be removed
/*    public void aboutTestAction(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }


    public void listTestAction(View view) {
        Intent i = new Intent(SplashActivity.this, AnimalsListActivity.class);
        i.putExtra(AnimalsListActivity.EXTRA_IS_FAV_LIST, true);
        i.putExtra(AnimalsListActivity.EXTRA_IS_SINGLE_ELEMENT_BROWSE, false);
        startActivity(i);
    }

    public void browseTestAction(View view) {
        Intent i = new Intent(SplashActivity.this, AnimalsListActivity.class);
        i.putExtra(AnimalsListActivity.EXTRA_IS_FAV_LIST, false);
        i.putExtra(AnimalsListActivity.EXTRA_IS_SINGLE_ELEMENT_BROWSE, true);
        startActivity(i);
    }

    public void aboutShelterAction(View view) {
        Intent i = new Intent(SplashActivity.this, AboutShelterActivity.class);
        Shelter shelter = new Shelter();
        shelter.setId(1L);
    }*/
}
