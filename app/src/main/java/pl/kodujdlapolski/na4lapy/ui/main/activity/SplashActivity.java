package pl.kodujdlapolski.na4lapy.ui.main.activity;

import android.os.Bundle;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.ui.DrawerActivity;

public class SplashActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        super.setDrawer();

    }
}
