package pl.kodujdlapolski.na4lapy.ui.preferences;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.ui.AbstractDrawerActivity;

public class PreferencesActivity extends AbstractDrawerActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout drawerActivityContent = (FrameLayout)findViewById(R.id.content);
        View preferencesActivityView = getLayoutInflater().inflate(R.layout.activity_preferences, null);
        drawerActivityContent.addView(preferencesActivityView);
    }

}
