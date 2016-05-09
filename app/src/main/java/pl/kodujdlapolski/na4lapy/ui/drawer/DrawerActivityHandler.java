package pl.kodujdlapolski.na4lapy.ui.drawer;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.ui.about_shelter.AboutShelterActivity;
import pl.kodujdlapolski.na4lapy.ui.browse.AbstractBrowseActivity;
import pl.kodujdlapolski.na4lapy.ui.browse.single.SingleBrowseActivity;
import pl.kodujdlapolski.na4lapy.ui.browse.list.ListBrowseActivity;
import pl.kodujdlapolski.na4lapy.ui.preferences.PreferencesActivity;
import pl.kodujdlapolski.na4lapy.ui.settings.SettingsActivity;

/**
 * Created by Malgorzata Syska on 2016-03-14.
 */
public class DrawerActivityHandler {

    private DrawerLayout drawerLayout;
    private Context context;
    private AbstractDrawerActivity activity;
    private NavigationView navigationView;

    public DrawerActivityHandler(AbstractDrawerActivity activity) {
        this.activity = activity;
        context = this.activity.getApplicationContext();
    }

    protected boolean onNavigationItemSelected(MenuItem item) {

        Intent intent = null;
        int id = item.getItemId();
        Class<?> clazz = activity.getClass();

        if (id == R.id.browser && !(clazz.equals(SingleBrowseActivity.class))) {
            intent = new Intent(context, SingleBrowseActivity.class);
            intent.putExtra(AbstractBrowseActivity.EXTRA_IS_FAV_LIST, false);
            intent.putExtra(AbstractBrowseActivity.EXTRA_IS_SINGLE_ELEMENT_BROWSE, true);
        } else if (id == R.id.favourites && !(clazz.equals(ListBrowseActivity.class))) {
            intent = new Intent(context, ListBrowseActivity.class);
            intent.putExtra(ListBrowseActivity.EXTRA_IS_FAV_LIST, true);
            intent.putExtra(ListBrowseActivity.EXTRA_IS_SINGLE_ELEMENT_BROWSE, false);
        } else if (id == R.id.preferences && !(clazz.equals(PreferencesActivity.class))) {
            intent = new Intent(context, PreferencesActivity.class);
        } else if (id == R.id.aboutShelter && !(clazz.equals(AboutShelterActivity.class))) {
            intent = new Intent(context, AboutShelterActivity.class);
            Shelter shelter = new Shelter();
            shelter.setId(1L);
            intent.putExtra(AboutShelterActivity.EXTRA_SHELTER_ID, 1l);

        } else if (id == R.id.settings && !(clazz.equals(SettingsActivity.class))) {
            intent = new Intent(context, SettingsActivity.class);
        }
        if (intent != null) {
            int flags = Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;
            if (clazz.equals(SingleBrowseActivity.class)) {
                flags = flags | (Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            intent.setFlags(flags);
            activity.startActivity(intent);
            activity.overridePendingTransition(0, 0);
        }
        return true;
    }

    public void setDrawer() {
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                activity, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView = (NavigationView) activity.findViewById(R.id.nav_view);
        if (navigationView != null)
            navigationView.setNavigationItemSelectedListener(activity);

    }

    public void onResume() {
        Class clazz = activity.getClass();
        if (clazz.equals(SingleBrowseActivity.class)) {
            navigationView.getMenu().getItem(0).setChecked(true);
        } else if (clazz.equals(ListBrowseActivity.class)) {
            navigationView.getMenu().getItem(1).setChecked(true);
        } else if (clazz.equals(PreferencesActivity.class)) {
            navigationView.getMenu().getItem(2).setChecked(true);
        }
    }
}
