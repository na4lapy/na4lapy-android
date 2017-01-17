/*
 *	Copyright 2017 Stowarzyszenie Na4Łapy
 *
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *	http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */

package pl.kodujdlapolski.na4lapy.ui.introduction;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class IntroductionPagerAdapter extends FragmentPagerAdapter {
    private final List<IntroductionPage> introductionPages;

    public IntroductionPagerAdapter(FragmentManager supportFragmentManager, List<IntroductionPage> introductionPages) {
        super(supportFragmentManager);
        this.introductionPages = introductionPages;
    }

    @Override
    public Fragment getItem(int position) {
        return IntroductionFragment.newInstance(introductionPages.get(position));
    }

    @Override
    public int getCount() {
        return introductionPages.size();
    }
}