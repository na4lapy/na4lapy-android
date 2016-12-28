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

package pl.kodujdlapolski.na4lapy.ui.about_shelter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.ui.AbstractSingleActivity;

public class AboutShelterActivity extends AbstractSingleActivity {

    public static final String EXTRA_SHELTER_ID = "EXTRA_SHELTER_ID";
    private Long shelterId;
    private ShareActionProvider mShareActionProvider;
    private Intent mShareIntent;
    AboutShelterFragment aboutShelterFragment;
    MenuItem shareMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_shelter);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            shelterId = b.getLong(EXTRA_SHELTER_ID);
        }
        if (shelterId == null && savedInstanceState != null) {
            shelterId = savedInstanceState.getLong(EXTRA_SHELTER_ID);
        }
        if (shelterId == null)
            finish(); // as nothing can be done without it
        aboutShelterFragment = (AboutShelterFragment) getSupportFragmentManager().findFragmentById(R.id.about_shelter_app_fragment);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putLong(EXTRA_SHELTER_ID, shelterId);
        super.onSaveInstanceState(outState);
    }

    public Long getShelterId() {
        return shelterId;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_shelter_menu, menu);
        shareMenuItem = menu.findItem(R.id.menu_item_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareMenuItem);
        if (mShareIntent != null) {
            mShareActionProvider.setShareIntent(mShareIntent);
        }
        return true;
    }

    public void setShareIntent(Intent shareIntent) {
        if (shareMenuItem != null) {
            shareMenuItem.setVisible(true);
        }
        mShareIntent = shareIntent;
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
}
