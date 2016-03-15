package pl.kodujdlapolski.na4lapy.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.Toolbar;

/**
 * Created by Kath on 2016-03-14.
 */
public class DrawerActivityHandler extends Activity {

    protected DrawerLayout drawerLayout;
    protected Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;

    public boolean onNavigationItemSelected(MenuItem item, Context context, Class clazz) {

        /*Intent intent = null;

        int id = item.getItemId();

        if (id == R.id.browser && !(clazz.equals(SplashActivity.class))) {
            intent = new Intent(context, SplashActivity.class);
        } else if (id == R.id.favourities) {
            intent = new Intent(context, AnimalsListActivity.class);
            intent.putExtra(AnimalsListActivity.EXTRA_IS_FAV_LIST, true);
        } else if (id == R.id.preferences && !(clazz.equals(PreferencesActivity.class))) {
            intent = new Intent(context, PreferencesActivity.class);
        } else if (id == R.id.aboutShelter) {
            intent = new Intent(context, AboutShelterActivity.class);
            Shelter shelter = new Shelter();
            shelter.setId(1L);
            intent.putExtra(AboutShelterActivity.EXTRA_SHELTER_ID, 1l);
        } else if (id == R.id.settings) {
            intent = new Intent(context, SettingsActivity.class);
        }
        if (intent != null) {
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
    */
    return true;
    }
}
