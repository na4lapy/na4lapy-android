package pl.kodujdlapolski.na4lapy.ui.introduction;

import android.animation.ArgbEvaluator;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
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

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.kodujdlapolski.na4lapy.R;

/**
 * Created by Natalia Wróblewska on 2016-04-20.
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

public class IntroductionActivity extends AppCompatActivity {

    int currentPage = 0;
    private final int LAST_VISIBLE_INTRO = 4;
    private final int LAST_INTRO = 5;
    @Bind(R.id.introduction_viewpager)
    ViewPager viewPager;
    @Bind(R.id.introduction_container)
    CoordinatorLayout introductionContainer;
    @Bind(R.id.intro_btn_finish)
    AppCompatButton finishBtn;
    @Bind(R.id.intro_btn_next)
    ImageButton nextBtn;
    @Bind(R.id.intro_btn_skip)
    Button skipBtn;
    @Bind({R.id.intro_indicator_0, R.id.intro_indicator_1, R.id.intro_indicator_2, R.id.intro_indicator_3, R.id.intro_indicator_4})
    ImageView[] indicators;

    @SuppressWarnings("unused")
    @OnClick({R.id.intro_btn_skip, R.id.intro_btn_finish})
    public void onSkipButtonClicked() {
        onFinish();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.intro_btn_next)
    public void onNextButtonClicked() {
        currentPage += 1;
        viewPager.setCurrentItem(currentPage, true);
    }

    private List<IntroductionPage> introductionPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        ButterKnife.bind(this);
        initIntroductionPages();
        initViewPager();
        initPageChangeListener();
    }

    private void initPageChangeListener() {
        final ArgbEvaluator evaluator = new ArgbEvaluator();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int fromColor = position == LAST_INTRO ? android.R.color.transparent : introductionPages.get(position).getBgColor();
                int toColor = position == LAST_INTRO ? android.R.color.transparent : introductionPages.get(position == LAST_VISIBLE_INTRO ? position : position + 1).getBgColor();

                int colorUpdate = (Integer) evaluator.evaluate(positionOffset, fromColor, toColor);
                introductionContainer.setBackgroundColor(colorUpdate);
            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                updateIndicators(currentPage);
                if (position == LAST_INTRO) {
                    nextBtn.setVisibility(View.GONE);
                    finishBtn.setVisibility(View.GONE);
                    skipBtn.setVisibility(View.GONE);
                    onFinish();
                } else {
                    introductionContainer.setBackgroundColor(introductionPages.get(position).getBgColor());
                    boolean lastPage = position == LAST_VISIBLE_INTRO;
                    nextBtn.setVisibility(lastPage ? View.GONE : View.VISIBLE);
                    finishBtn.setVisibility(lastPage ? View.VISIBLE : View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initIntroductionPages() {
        introductionPages = new ArrayList<>();
        introductionPages.add(new IntroductionPage(getString(R.string.introduction_page_1), R.layout.fragment_introduction, ContextCompat.getColor(IntroductionActivity.this, android.R.color.holo_red_dark)));
        introductionPages.add(new IntroductionPage(getString(R.string.introduction_page_2), R.layout.fragment_introduction, ContextCompat.getColor(IntroductionActivity.this, android.R.color.holo_green_dark)));
        introductionPages.add(new IntroductionPage(getString(R.string.introduction_page_3), R.layout.fragment_introduction, ContextCompat.getColor(IntroductionActivity.this, android.R.color.holo_blue_dark)));
        introductionPages.add(new IntroductionPage(getString(R.string.introduction_page_4), R.layout.fragment_introduction, ContextCompat.getColor(IntroductionActivity.this, android.R.color.holo_orange_dark)));
        introductionPages.add(new IntroductionPage(getString(R.string.introduction_page_5), R.layout.fragment_introduction, ContextCompat.getColor(IntroductionActivity.this, android.R.color.holo_purple)));
    }

    private void initViewPager() {
        IntroductionPagerAdapter mSectionsPagerAdapter = new IntroductionPagerAdapter(getSupportFragmentManager(), introductionPages);
        viewPager.setAdapter(mSectionsPagerAdapter);
        viewPager.setCurrentItem(currentPage);
        updateIndicators(currentPage);
    }

    private void updateIndicators(int currentPage) {
        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setBackgroundResource(i == currentPage ? R.drawable.introduction_indicator_selected : R.drawable.introduction_indicator_not_selected);
        }
    }

    private void saveIntroductionWasShowed() {
        //TODO
    }

    @Override
    public void onBackPressed() {
        onFinish();
    }

    private void onFinish() {
        saveIntroductionWasShowed();
        finish();
        overridePendingTransition(0, 0);
    }
}
