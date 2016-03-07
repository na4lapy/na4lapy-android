package pl.kodujdlapolski.na4lapy.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.Na4LapyApp;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.repository.RepositoryService;
import pl.kodujdlapolski.na4lapy.ui.about_app.AboutAppActivity;
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
        startActivity(new Intent(this, AboutAppActivity.class));
    }

    public void aboutShelterAction(View view) {
        repositoryService.getShelters(new RepositoryService.LoadSheltersCallback() {
            @Override
            public void onSheltersLoaded(List<Shelter> shelters) {
                //  Intent i = new Intent(SplashActivity.this, AboutShelterActivity.class);
                //  i.putExtra(AboutShelterActivity.EXTRA_SHELTER_ID, shelters.get(0).getId());
                //  startActivity(i);
            }
        });
    }
}
