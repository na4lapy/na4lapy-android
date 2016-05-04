package pl.kodujdlapolski.na4lapy;

import android.content.Intent;
import android.os.Bundle;
import android.support.test.espresso.web.webdriver.Locator;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pl.kodujdlapolski.na4lapy.presenter.settings.WebPageTypes;
import pl.kodujdlapolski.na4lapy.ui.settings.WebViewActivity;

import static android.support.test.espresso.web.sugar.Web.onWebView;
import static android.support.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static android.support.test.espresso.web.webdriver.DriverAtoms.findElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.getText;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by Natalia Wróblewska on 2016-03-08.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class WebViewActivityTest {

    @Rule
    public ActivityTestRule<WebViewActivity> mActivityRule = new ActivityTestRule<WebViewActivity>(WebViewActivity.class, false, false){
        @Override
        protected void afterActivityLaunched() {
            onWebView().forceJavascriptEnabled();
        }
    };
    @Test
    public void isOpenSourceDisplayed() {
        testByType(WebPageTypes.OPEN_SOURCE);
    }

    @Test
    public void isPrivacyPolicyDisplayed() {
        testByType(WebPageTypes.POLICY);
    }


    private void testByType(WebPageTypes type) {
        mActivityRule.launchActivity(getIntentByType(type));
        onWebView()
                .withElement(findElement(Locator.ID, "content"))
                .check(webMatches(getText(), containsString(getAssetText(type))));
    }

    private static Intent getIntentByType(WebPageTypes type) {
        Intent i = new Intent();
        Bundle b = new Bundle();
        b.putSerializable(WebViewActivity.EXTRA_TYPE, type);
        i.putExtras(b);
        return i;
    }

    private String getAssetText(WebPageTypes webPageTypes) {
        String result = "";
        BufferedReader reader = null;
        String [] pageUrl = webPageTypes.getUrl().split("/");

        try {
            reader = new BufferedReader(
                    new InputStreamReader(mActivityRule.getActivity().getAssets().open(pageUrl[pageUrl.length-1]), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                result = result + line;
            }
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String[] preContent = result.split("<p id=\"content\">");
        String[] postContent = preContent[1].split("</p>");
        return postContent[0];
    }
}