package pl.kodujdlapolski.na4lapy.ui;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.ui.about_shelter.AboutShelterActivity;
import pl.kodujdlapolski.na4lapy.ui.animals_list.AnimalsBrowseActivity;
import pl.kodujdlapolski.na4lapy.ui.animals_list.AnimalsFavListActivity;
import pl.kodujdlapolski.na4lapy.ui.animals_list.AnimalsListActivity;
import pl.kodujdlapolski.na4lapy.ui.main.activity.SplashActivity;
import pl.kodujdlapolski.na4lapy.ui.preferences.PreferencesActivity;
import pl.kodujdlapolski.na4lapy.ui.settings.SettingsActivity;

/**
 * Created by Gosia on 2016-03-14.
 */
public class DrawerActivityHandler {

    private DrawerLayout drawerLayout;
    private Context context;
    private AbstractDrawerActivity activity;

    public DrawerActivityHandler(AbstractDrawerActivity activity) {
        this.activity = activity;
        context = this.activity.getApplicationContext();
    }

    protected boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = null;
        int id = item.getItemId();
        Class<?> clazz = activity.getClass();

        if (id == R.id.browser && !(clazz.equals(AnimalsBrowseActivity.class))) {
            intent = new Intent(context, AnimalsBrowseActivity.class);
            intent.putExtra(AnimalsBrowseActivity.EXTRA_IS_FAV_LIST, false);
            intent.putExtra(AnimalsBrowseActivity.EXTRA_IS_SINGLE_ELEMENT_BROWSE, true);
        }
        else if (id == R.id.favourities && !(clazz.equals(AnimalsFavListActivity.class))) {
            intent = new Intent(context, AnimalsFavListActivity.class);
            intent.putExtra(AnimalsFavListActivity.EXTRA_IS_FAV_LIST, true);
            intent.putExtra(AnimalsFavListActivity.EXTRA_IS_SINGLE_ELEMENT_BROWSE, false);
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
            activity.startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    protected boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return false;
    }

    public void setDrawer() {
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        if (activity.getSupportActionBar()!=null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                activity, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        NavigationView navigationView = (NavigationView) activity.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(activity);
    }
}
