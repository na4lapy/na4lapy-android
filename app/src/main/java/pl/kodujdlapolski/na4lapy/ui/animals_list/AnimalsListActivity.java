package pl.kodujdlapolski.na4lapy.ui.animals_list;

import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;

public class AnimalsListActivity extends AppCompatActivity {

    private boolean isFavList = false;
    private AnimalsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private AnimalsListPresenter animalsListPresenter;
    private boolean isAlive = true;
    private ArrayList<Animal> animals;

    public static String EXTRA_IS_FAV_LIST = "extraIsFavList";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        animals = new ArrayList<>();
        mSectionsPagerAdapter = new AnimalsPagerAdapter(this, animals, getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        isFavList = getIntent().getBooleanExtra(EXTRA_IS_FAV_LIST, false);
        if (!isFavList && savedInstanceState != null) {
            isFavList = savedInstanceState.getBoolean(EXTRA_IS_FAV_LIST);
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

    }


    public void showProgressHideContent(boolean show) {

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
