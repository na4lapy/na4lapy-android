package pl.kodujdlapolski.na4lapy.ui.browse.single;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import net.hockeyapp.android.CrashManager;

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
 * Created by Natalia Wróblewska on 2016-05-22.
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
public class SingleBrowseActivity extends AbstractDrawerActivity implements BrowseContract.View {

    @BindView(R.id.animals_list_progress)
    ProgressBar progressBar;
    @BindView(R.id.list)
    RecyclerViewPager recyclerView;
    private BrowseContract.Presenter browsePresenter;
    private SingleBrowseAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        callSuperOnCreate(savedInstanceState);
        setContentView(R.layout.activity_single_browse);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        handler = new DrawerActivityHandler(this);
        handler.setDrawer();

        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.animals_list_activity);
        }
        browsePresenter = new BrowsePresenter(this, false);
        initRecyclerView();
    }

    @Override
    public BrowseContract.Adapter getAdapter() {
        return adapter;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showProgressHideContent(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.onResume();
        CrashManager.register(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DetailsActivity.REQUEST_CODE_ANIMAL && resultCode == Activity.RESULT_OK) {
            Long changedAnimalId = data.getLongExtra(DetailsActivity.EXTRA_ANIMAL_ID, -1);
            if (changedAnimalId != -1)
                browsePresenter.onChangedAnimalAvailable(changedAnimalId);
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layout);
        adapter = new SingleBrowseAdapter(browsePresenter.getAnimals(), browsePresenter, browsePresenter.getUserService());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new SingleBrowseRecyclerScrollListener());
        recyclerView.addOnLayoutChangeListener(new SingleBrowseOnLayoutChangeListener(recyclerView));
    }
}
