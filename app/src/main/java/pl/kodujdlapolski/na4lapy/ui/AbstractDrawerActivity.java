package pl.kodujdlapolski.na4lapy.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import pl.kodujdlapolski.na4lapy.R;

/**
 * Created by Gosia on 2016-03-12.
 */
public abstract class AbstractDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout drawerLayout;
    protected DrawerActivityHandler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        handler = new DrawerActivityHandler(this);
        handler.setDrawer();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return handler.onNavigationItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return handler.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_option, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void callSuperOnCreate(Bundle saved) {
        super.onCreate(saved);
    }
}
