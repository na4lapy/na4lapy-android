package pl.kodujdlapolski.na4lapy.ui.preferences;

import android.os.Bundle;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.ui.DrawerActivity;

public class PreferencesActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        super.setDrawer();

    }
}
