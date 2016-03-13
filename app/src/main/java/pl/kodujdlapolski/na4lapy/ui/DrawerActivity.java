package pl.kodujdlapolski.na4lapy.ui;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.ui.main.activity.SplashActivity;
import pl.kodujdlapolski.na4lapy.ui.preferences.PreferencesActivity;

/**
 * Created by Gosia on 2016-03-12.
 */
public class DrawerActivity extends AbstractDrawerActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    protected DrawerLayout drawerLayout;
    protected Toolbar toolbar;
    protected ActionBarDrawerToggle drawerToggle;

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = null;
        Context context = getApplicationContext();
        int id = item.getItemId();
        Class<?> clazz = this.getClass();

        if (id == R.id.browser && !(clazz.equals(SplashActivity.class))) {
            intent = new Intent(context, SplashActivity.class);
        } else if (id == R.id.favourities) {

        } else if (id == R.id.preferences && !(clazz.equals(PreferencesActivity.class))) {
            intent = new Intent(context, PreferencesActivity.class);
        } else if (id == R.id.aboutShelter) {

        } else if (id == R.id.settings) {

        }
        if (intent!=null) {
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    @Override
    public void setDrawer() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}
