package pl.kodujdlapolski.na4lapy.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import pl.kodujdlapolski.na4lapy.Na4LapyApp;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.ui.browse.AbstractBrowseActivity;
import pl.kodujdlapolski.na4lapy.ui.browse.single.SingleBrowseActivity;
import pl.kodujdlapolski.na4lapy.ui.introduction.IntroductionActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ((Na4LapyApp) getApplication()).getComponent().inject(this);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.goToBrowse).performClick();
            }
        }, 100);

    }

    public void goToBrowse(View view) {
        TaskStackBuilder b = TaskStackBuilder.create(this);
        Intent browse = new Intent(SplashActivity.this, SingleBrowseActivity.class);
        browse.putExtra(AbstractBrowseActivity.EXTRA_IS_FAV_LIST, false);
        browse.putExtra(AbstractBrowseActivity.EXTRA_IS_SINGLE_ELEMENT_BROWSE, true);
        Intent intent1 = new Intent(this, IntroductionActivity.class);
        b.addNextIntent(browse);
        b.addNextIntent(intent1);
        b.startActivities();
        finish();
    }
}
