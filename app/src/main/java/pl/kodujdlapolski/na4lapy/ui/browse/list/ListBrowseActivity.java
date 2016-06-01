package pl.kodujdlapolski.na4lapy.ui.browse.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
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
    @BindView(R.id.error_message)
    TextView errorMessage;
    @BindView(R.id.error_container)
    ViewGroup errorContainer;

    FragmentPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.fav_animals_list_activity);
        }
        browsePresenter = new BrowsePresenter(this, true);
        adapter = new ListBrowsePagerAdapter(this, browsePresenter.getAnimals(), getSupportFragmentManager());
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_list_browse;
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

    @Override
    public void showStateWaitingForData() {
        progressBar.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.GONE);
        errorContainer.setVisibility(View.GONE);
    }

    @Override
    public void showStateNoInternetConnection() {
        Toast.makeText(getApplicationContext(), R.string.error_data_no_internet_connection, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showStateDataIsAvailable() {
        progressBar.setVisibility(View.GONE);
        viewPager.setVisibility(View.VISIBLE);
        errorContainer.setVisibility(View.GONE);
    }

    @Override
    public void showStateDataIsEmpty() {
        progressBar.setVisibility(View.GONE);
        viewPager.setVisibility(View.GONE);
        errorContainer.setVisibility(View.VISIBLE);
        errorMessage.setText(R.string.error_no_data_fav);
    }

    @Override
    public void showStateError(Throwable t) {
        //  Toast.makeText(getApplicationContext(), R.string.error_data_cannot_be_loaded, Toast.LENGTH_LONG).show();
        //todo 457  uncomment when new database is ready
        if (t != null && t.getMessage() != null) {
            Log.d(this.getClass().toString(), t.getMessage());
        }
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