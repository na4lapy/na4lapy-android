/*
 * Copyright (C) 2016 [name of copyright owner]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.kodujdlapolski.na4lapy.ui.drawer;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import pl.kodujdlapolski.na4lapy.Na4LapyApp;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.presenter.payment.PaymentContract;
import pl.kodujdlapolski.na4lapy.ui.about_shelter.AboutShelterActivity;
import pl.kodujdlapolski.na4lapy.ui.browse.list.ListBrowseActivity;
import pl.kodujdlapolski.na4lapy.ui.browse.single.SingleBrowseActivity;
import pl.kodujdlapolski.na4lapy.ui.payment.PaymentActivity;
import pl.kodujdlapolski.na4lapy.ui.preferences.PreferencesActivity;
import pl.kodujdlapolski.na4lapy.ui.settings.SettingsActivity;
import pl.kodujdlapolski.na4lapy.user.UserService;

public class DrawerActivityHandler {

    @Inject
    UserService userService;

    private DrawerLayout drawerLayout;
    private Context context;
    private AbstractDrawerActivity activity;
    private NavigationView navigationView;

    public DrawerActivityHandler(AbstractDrawerActivity activity) {
        ((Na4LapyApp)activity.getApplication()).getComponent().inject(this);
        this.activity = activity;
        context = this.activity.getApplicationContext();
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

    protected boolean onNavigationItemSelected(MenuItem item) {

        Intent intent = null;
        int id = item.getItemId();
        Class<?> clazz = activity.getClass();

        if (id == R.id.browser && !(clazz.equals(SingleBrowseActivity.class))) {
            intent = new Intent(context, SingleBrowseActivity.class);
        } else if (id == R.id.favourites && !(clazz.equals(ListBrowseActivity.class))) {
            intent = new Intent(context, ListBrowseActivity.class);
        } else if (id == R.id.preferences && !(clazz.equals(PreferencesActivity.class))) {
            intent = new Intent(context, PreferencesActivity.class);
        } else if (id == R.id.aboutShelter && !(clazz.equals(AboutShelterActivity.class))) {
            intent = new Intent(context, AboutShelterActivity.class);
            Shelter shelter = new Shelter();
            shelter.setId(1L);
            intent.putExtra(AboutShelterActivity.EXTRA_SHELTER_ID, 1l);
        } else if (id == R.id.makePayment && !(clazz.equals(PaymentActivity.class))) {
            intent = new Intent(context, PaymentActivity.class);
            intent.putExtra(PaymentContract.KEY_SHELTER_ID, 1L);
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
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(activity);
        }

        setDrawerHeader();
    }

    private void setDrawerHeader() {
        View headerView =  navigationView.getHeaderView(0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            headerView.setPadding(
                    headerView.getPaddingLeft(),
                    (int)(headerView.getPaddingTop() + activity.getResources().getDimension(R.dimen.status_bar)),
                    headerView.getPaddingRight(),
                    headerView.getPaddingBottom());
        }

        ImageView drawerHeaderPhoto = (ImageView)headerView.findViewById(R.id.drawer_header_photo);
        TextView drawerHeaderUserName = (TextView)headerView.findViewById(R.id.drawer_header_user_name);

        if (!userService.isLogged()) {
            drawerHeaderUserName.setText(R.string.app_name);
            drawerHeaderPhoto.setImageResource(R.drawable.application_logo);
            return;
        }

        setUserPhotoInHeader(drawerHeaderPhoto);
        setUserNameInHeader(drawerHeaderUserName);

    }

    private void setUserNameInHeader(TextView drawerHeaderUserName) {
        String fullUserName = userService.getUserFirstName();
        if (TextUtils.isEmpty(fullUserName)) {
            drawerHeaderUserName.setText(R.string.app_name);
        } else {
            drawerHeaderUserName.setText(fullUserName);
        }
    }

    private void setUserPhotoInHeader(ImageView drawerHeaderPhoto) {
        String userPhotoUrl = userService.getUserPhotoUrl();
        if (TextUtils.isEmpty(userPhotoUrl)) {
            drawerHeaderPhoto.setImageResource(R.drawable.application_logo);
        }
        else {
            Picasso.with(activity.getApplication().getApplicationContext())
                    .load(userPhotoUrl)
                    .transform(new CropCircleTransformation())
                    .into(drawerHeaderPhoto);
        }
    }

}
