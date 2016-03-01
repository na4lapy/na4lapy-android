package pl.kodujdlapolski.na4lapy.ui.about_app;

import android.os.Bundle;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.ui.AbstractSingleActivity;

public class AboutAppActivity extends AbstractSingleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(R.string.about_page_title);
    }
}
