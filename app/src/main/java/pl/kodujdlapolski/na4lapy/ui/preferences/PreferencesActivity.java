package pl.kodujdlapolski.na4lapy.ui.preferences;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.ui.drawer.AbstractDrawerActivity;

/**
 * Created by Malgorzata Syska on 2016-03-12.
 */
public class PreferencesActivity extends AbstractDrawerActivity {

    @BindView(R.id.content)
    FrameLayout drawerActivityContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        View preferencesActivityView = getLayoutInflater().inflate(R.layout.activity_preferences, null);
        if (drawerActivityContent != null)
            drawerActivityContent.addView(preferencesActivityView);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_drawer;
    }

}
