package pl.kodujdlapolski.na4lapy.ui.animals_list;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;

public class AnimalsListActivity extends AppCompatActivity {

    private boolean isFavList = false;
    private AnimalsPagerAdapter mSectionsPagerAdapter;
    private AnimalsListPresenter animalsListPresenter;
    private boolean isAlive = true;
    private ArrayList<Animal> animals;
    @Bind(R.id.container)
    ViewPager mViewPager;
    @Bind(R.id.error_container)
    LinearLayout errorContainer;
    @Bind(R.id.animals_list_progress)
    ProgressBar progressBar;

    @SuppressWarnings("unused")
    @OnClick(R.id.try_again_btn)
    public void onTryAgainClick(){
        errorContainer.setVisibility(View.GONE);
        animalsListPresenter.startDownloadingData();
    }

    public static String EXTRA_IS_FAV_LIST = "extraIsFavList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals_list);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ButterKnife.bind(this);
        animals = new ArrayList<>();
        mSectionsPagerAdapter = new AnimalsPagerAdapter(this, animals, getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        ((TabLayout) findViewById(R.id.tabs)).setupWithViewPager(mViewPager);

        isFavList = getIntent().getBooleanExtra(EXTRA_IS_FAV_LIST, false);
        if (!isFavList && savedInstanceState != null) {
            isFavList = savedInstanceState.getBoolean(EXTRA_IS_FAV_LIST);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(isFavList ? R.string.fav_animals_list_activity : R.string.animals_list_activity);
        }
        animalsListPresenter = new AnimalsListPresenter(this, isFavList);
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
        animalsListPresenter.onActivityStart();
    }

    @Override
    protected void onStop() {
        animalsListPresenter.onActivityStop();
        super.onStop();
    }

    public void showError() {
        progressBar.setVisibility(View.GONE);
        mViewPager.setVisibility(View.GONE);
        errorContainer.setVisibility(View.VISIBLE);
    }


    public void showProgressHideContent(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        mViewPager.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    public void updateAnimals(List<Animal> animals) {
        this.animals.clear();
        this.animals.addAll(animals);
        mSectionsPagerAdapter.notifyDataSetChanged();
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(EXTRA_IS_FAV_LIST, isFavList);
        super.onSaveInstanceState(outState);
    }
}
