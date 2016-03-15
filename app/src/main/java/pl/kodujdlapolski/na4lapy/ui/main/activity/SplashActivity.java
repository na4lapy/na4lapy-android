package pl.kodujdlapolski.na4lapy.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.Na4LapyApp;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.repository.RepositoryService;
import pl.kodujdlapolski.na4lapy.ui.animals_list.AnimalsListActivity;
import pl.kodujdlapolski.na4lapy.ui.settings.SettingsActivity;
import pl.kodujdlapolski.na4lapy.ui.about_shelter.AboutShelterActivity;

public class SplashActivity extends AppCompatActivity {

    @Inject
    RepositoryService repositoryService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ((Na4LapyApp) getApplication()).getComponent().inject(this);
    }

    public void aboutTestAction(View view) {
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
        i.putExtra(AboutShelterActivity.EXTRA_SHELTER_ID, 1l);
        startActivity(i);

    }
}
