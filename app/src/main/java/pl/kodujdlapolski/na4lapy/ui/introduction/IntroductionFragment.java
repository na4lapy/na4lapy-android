package pl.kodujdlapolski.na4lapy.ui.introduction;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
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
public class IntroductionFragment extends Fragment {
    public static final String EXTRA_INTRO = "extra_introduction_page";
    private IntroductionPage introductionPage;

    public static IntroductionFragment newInstance(IntroductionPage introductionPage) {
        IntroductionFragment fragment = new IntroductionFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_INTRO, introductionPage);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null && getArguments().getSerializable(EXTRA_INTRO) != null) {
            introductionPage = (IntroductionPage) getArguments().getSerializable(EXTRA_INTRO);
        }
        if (savedInstanceState != null && savedInstanceState.getSerializable(EXTRA_INTRO) != null && introductionPage == null) {
            introductionPage = (IntroductionPage) savedInstanceState.getSerializable(EXTRA_INTRO);
        }
        if (introductionPage != null) {
            View rootView = inflater.inflate(introductionPage.getLayout(), container, false);
            ((TextView) rootView.findViewById(R.id.introduction_text)).setText(introductionPage.getText());
            return rootView;
        } else {
            return inflater.inflate(R.layout.fragment_empty, container, false);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(EXTRA_INTRO, introductionPage);
        super.onSaveInstanceState(outState);
    }
}