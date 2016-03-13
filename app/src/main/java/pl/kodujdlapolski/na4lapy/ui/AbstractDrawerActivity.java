package pl.kodujdlapolski.na4lapy.ui;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Gosia on 2016-03-12.
 */
public abstract class AbstractDrawerActivity extends AppCompatActivity {

    abstract boolean onNavigationItemSelected(MenuItem item);

    public abstract boolean onCreateOptionsMenu(Menu menu);

    abstract void setDrawer();

    public void onBackPressed() {
        super.onBackPressed();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
