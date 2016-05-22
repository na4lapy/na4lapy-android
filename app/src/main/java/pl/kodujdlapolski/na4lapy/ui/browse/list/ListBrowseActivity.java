package pl.kodujdlapolski.na4lapy.ui.browse.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.ui.browse.BrowseContract;
import pl.kodujdlapolski.na4lapy.ui.browse.BrowsePresenter;
import pl.kodujdlapolski.na4lapy.ui.details.DetailsActivity;
import pl.kodujdlapolski.na4lapy.ui.drawer.AbstractDrawerActivity;
import pl.kodujdlapolski.na4lapy.ui.drawer.DrawerActivityHandler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.sync.receiver.SynchronizationReceiver;
import pl.kodujdlapolski.na4lapy.ui.browse.BrowseContract;
import pl.kodujdlapolski.na4lapy.ui.browse.BrowsePresenter;
import pl.kodujdlapolski.na4lapy.ui.details.DetailsActivity;
import pl.kodujdlapolski.na4lapy.ui.drawer.AbstractDrawerActivity;
import pl.kodujdlapolski.na4lapy.ui.drawer.DrawerActivityHandler;

/**
 * Created by Natalia Wróblewska on 2016-03-16.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class ListBrowseActivity extends AbstractDrawerActivity implements BrowseContract.View {

    private BrowseContract.Presenter browsePresenter;
    @BindView(R.id.container)
    ViewPager viewPager;
    @BindView(R.id.animals_list_progress)
    ProgressBar progressBar;

    FragmentPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        callSuperOnCreate(savedInstanceState);
        setContentView(R.layout.activity_list_browse);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        handler = new DrawerActivityHandler(this);
        handler.setDrawer();

        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.fav_animals_list_activity);
        }
        browsePresenter = new BrowsePresenter(this, true);
        adapter = new ListBrowsePagerAdapter(this, browsePresenter.getAnimals(), getSupportFragmentManager());
    }


    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        if (tabLayout != null)
            tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public BrowseContract.Adapter getAdapter() {
        return (BrowseContract.Adapter) adapter;
    }

    @Override
    public Activity getActivity() {
        return this;
    }


    public void showProgressHideContent(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        viewPager.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DetailsActivity.REQUEST_CODE_ANIMAL && resultCode == Activity.RESULT_OK) {
            Long changedAnimalId = data.getLongExtra(DetailsActivity.EXTRA_ANIMAL_ID, -1);
            if (changedAnimalId != -1)
                browsePresenter.onChangedAnimalAvailable(changedAnimalId);
        }
    }

    public BrowseContract.Presenter getPresenter() {
        return browsePresenter;
    }
}
    private boolean isAlive = true;
    @BindView(R.id.container)
    ViewPager viewPager;
    @BindView(R.id.error_container)
    LinearLayout errorContainer;
    @BindView(R.id.animals_list_progress)
    ProgressBar progressBar;

    @SuppressWarnings("unused")
    @OnClick(R.id.try_again_btn)
    public void onTryAgainClick() {
        errorContainer.setVisibility(View.GONE);
        browsePresenter.startDownloadingData();
    }

    FragmentPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        callSuperOnCreate(savedInstanceState);
        setContentView(R.layout.activity_abstract_browse);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        handler = new DrawerActivityHandler(this);
        handler.setDrawer();

        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.fav_animals_list_activity );
        }
        browsePresenter = new BrowsePresenter(this, true);
        adapter = new ListBrowsePagerAdapter(this, browsePresenter.getAnimals(), getSupportFragmentManager());
    }


    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        if (tabLayout != null)
            tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            isAlive = false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver(browsePresenter.getSynchronizationReceiver(), SynchronizationReceiver.getIntentFilter());
    }

    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(browsePresenter.getSynchronizationReceiver());
        super.onStop();
    }

    public void showError() {
        progressBar.setVisibility(View.GONE);
        viewPager.setVisibility(View.GONE);
        // todo synchronization fail shouldn't be called
        //  errorContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public BrowseContract.Adapter getAdapter() {
        return (BrowseContract.Adapter) adapter;
    }

    @Override
    public Activity getActivity() {
        return this;
    }


    public void showProgressHideContent(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        viewPager.setVisibility(show ? View.GONE : View.VISIBLE);
        errorContainer.setVisibility(View.GONE);
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DetailsActivity.REQUEST_CODE_ANIMAL && resultCode == Activity.RESULT_OK) {
            Long changedAnimalId = data.getLongExtra(DetailsActivity.EXTRA_ANIMAL_ID, -1);
            if (changedAnimalId != -1)
                browsePresenter.onChangedAnimalAvailable(changedAnimalId);
        }
    }

    public BrowseContract.Presenter getPresenter() {
        return browsePresenter;
    }
}