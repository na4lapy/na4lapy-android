package pl.kodujdlapolski.na4lapy.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.Na4LapyApp;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.service.preferences.PreferencesService;
import pl.kodujdlapolski.na4lapy.ui.browse.single.SingleBrowseActivity;
import pl.kodujdlapolski.na4lapy.ui.introduction.IntroductionActivity;

public class SplashActivity extends AppCompatActivity {
    @Inject
    PreferencesService preferencesService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ((Na4LapyApp) getApplication()).getComponent().inject(this);
        goToBrowse();
    }

    public void goToBrowse() {
        TaskStackBuilder b = TaskStackBuilder.create(this);
        Intent browse = new Intent(SplashActivity.this, SingleBrowseActivity.class);
        b.addNextIntent(browse);
        if (preferencesService.shouldIntroductionBeShown()) {
            Intent intent1 = new Intent(this, IntroductionActivity.class);
            b.addNextIntent(intent1);
        }
        b.startActivities();
        finish();
    }
}
