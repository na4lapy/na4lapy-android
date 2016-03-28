package pl.kodujdlapolski.na4lapy.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.Na4LapyApp;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.repository.RepositoryService;
import pl.kodujdlapolski.na4lapy.ui.animals_list.AnimalsBrowseActivity;
import pl.kodujdlapolski.na4lapy.ui.animals_list.AnimalsListActivity;

public class SplashActivity extends AppCompatActivity {

    @Inject
    RepositoryService repositoryService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ((Na4LapyApp) getApplication()).getComponent().inject(this);
    }

    public void goToBrowse(View view) {
        Intent i = new Intent(SplashActivity.this, AnimalsBrowseActivity.class);
        i.putExtra(AnimalsListActivity.EXTRA_IS_FAV_LIST, false);
        i.putExtra(AnimalsListActivity.EXTRA_IS_SINGLE_ELEMENT_BROWSE, true);
        startActivity(i);
    }
}
