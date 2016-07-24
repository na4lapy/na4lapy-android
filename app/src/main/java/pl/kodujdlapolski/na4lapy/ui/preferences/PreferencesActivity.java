/*
 * Copyright (C) 2016 Stowarzyszenie Na4Łapy
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
package pl.kodujdlapolski.na4lapy.ui.preferences;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.ui.drawer.AbstractDrawerActivity;

public class PreferencesActivity extends AbstractDrawerActivity {

    @BindView(R.id.content)
    FrameLayout drawerActivityContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        View preferencesActivityView = getLayoutInflater().inflate(R.layout.activity_preferences, null);
        if (drawerActivityContent != null)
            drawerActivityContent.addView(preferencesActivityView);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_drawer;
    }

}
