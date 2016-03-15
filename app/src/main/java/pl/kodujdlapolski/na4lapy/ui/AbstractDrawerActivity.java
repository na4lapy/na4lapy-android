package pl.kodujdlapolski.na4lapy.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.ui.about_shelter.AboutShelterActivity;
import pl.kodujdlapolski.na4lapy.ui.animals_list.AnimalsListActivity;
import pl.kodujdlapolski.na4lapy.ui.main.activity.SplashActivity;
import pl.kodujdlapolski.na4lapy.ui.preferences.PreferencesActivity;
import pl.kodujdlapolski.na4lapy.ui.settings.SettingsActivity;

/**
 * Created by Gosia on 2016-03-12.
 */
public abstract class AbstractDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout drawerLayout;
    protected Toolbar toolbar;
    protected ActionBarDrawerToggle drawerToggle;

    private DrawerActivityHandler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        setDrawer();
    }

/*    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Context context = getApplicationContext();
        Class<?> clazz = this.getClass();
        return handler.onNavigationItemSelected(item, context, clazz);
    }*/

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = null;
        Context context = getApplicationContext();
        int id = item.getItemId();
        Class<?> clazz = this.getClass();

        if (id == R.id.browser && !(clazz.equals(SplashActivity.class))) {
            intent = new Intent(context, SplashActivity.class);
        }
        else if (id == R.id.favourities && !(clazz.equals(AnimalsListActivity.class))) {
            intent = new Intent(context, AnimalsListActivity.class);
            intent.putExtra(AnimalsListActivity.EXTRA_IS_FAV_LIST, true);
        }
        else if (id == R.id.preferences && !(clazz.equals(PreferencesActivity.class))) {
            intent = new Intent(context, PreferencesActivity.class);
        }
        else if (id == R.id.aboutShelter && !(clazz.equals(AboutShelterActivity.class))) {
            intent = new Intent(context, AboutShelterActivity.class);
            Shelter shelter = new Shelter();
            shelter.setId(1L);
            intent.putExtra(AboutShelterActivity.EXTRA_SHELTER_ID, 1l);
        }
        else if (id == R.id.settings && !(clazz.equals(SettingsActivity.class))) {
            intent = new Intent(context, SettingsActivity.class);
        }
        if (intent!=null) {
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        setDrawer();
        return true;
    }

    protected void setDrawer() {
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

}
