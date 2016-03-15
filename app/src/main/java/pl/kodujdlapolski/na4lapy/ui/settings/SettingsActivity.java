package pl.kodujdlapolski.na4lapy.ui.settings;

import android.os.Bundle;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.ui.AbstractSingleActivity;

public class SettingsActivity extends AbstractSingleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(R.string.settings_page_title);
    }
}
