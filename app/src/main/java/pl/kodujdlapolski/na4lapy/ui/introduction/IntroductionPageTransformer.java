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

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import pl.kodujdlapolski.na4lapy.R;

public class IntroductionPageTransformer implements ViewPager.PageTransformer {
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        View text = view.findViewById(R.id.introduction_text);
        ImageView tut_a_3 = (ImageView) view.findViewById(R.id.tut_a_3);
        ImageView tut_a_2 = (ImageView) view.findViewById(R.id.tut_a_2);

        ImageView tut_b_3 = (ImageView) view.findViewById(R.id.tut_b_3);
        ImageView tut_b_2 = (ImageView) view.findViewById(R.id.tut_b_2);

        ImageView tut_c_2 = (ImageView) view.findViewById(R.id.tut_c_2);
        ImageView tut_c_3 = (ImageView) view.findViewById(R.id.tut_c_3);

        ImageView tut_d_2 = (ImageView) view.findViewById(R.id.tut_d_2);
        ImageView tut_d_3 = (ImageView) view.findViewById(R.id.tut_d_3);
        ImageView tut_d_4 = (ImageView) view.findViewById(R.id.tut_d_4);

        ImageView tut_e_2 = (ImageView) view.findViewById(R.id.tut_e_2);
        ImageView tut_e_3 = (ImageView) view.findViewById(R.id.tut_e_3);
        ImageView tut_e_4 = (ImageView) view.findViewById(R.id.tut_e_4);
        RelativeLayout lastIntroContainer = (RelativeLayout) view.findViewById(R.id.last_intro_container);

        if (position <= -1.0f || position >= 1.0f) {
        } else if (position == 0.0f) {
        } else {
            if (lastIntroContainer != null && position < 0) { // set alpha for fading last element to only one side
                lastIntroContainer.setAlpha(1.0f - Math.abs(position));
            }
            if (text != null) {
                text.setAlpha(1.0f - Math.abs(position));
            }
            if (tut_a_2 != null) {
                tut_a_2.setAlpha(1.0f - Math.abs(position * 2));
                tut_a_2.setTranslationY(pageWidth / 20 * position);
            }
            if (tut_a_3 != null) {
                tut_a_3.setTranslationX(pageWidth / 10 * position);
            }

            if (tut_a_2 != null) {
                tut_a_2.setAlpha(1.0f - Math.abs(position * 2));
            }
            if (tut_b_2 != null) {
                tut_b_2.setAlpha(1.0f - Math.abs(position * 2));
            }
            if (tut_b_3 != null) {
                tut_b_3.setTranslationX(pageWidth / 20 * position * -1);
            }
            if (tut_c_2 != null) {
                tut_c_2.setTranslationX(pageWidth / 20 * position * -1);
            }
            if (tut_c_3 != null) {
                tut_c_3.setTranslationX(pageWidth / 10 * position);
            }
            if (tut_d_2 != null) {
                tut_d_2.setTranslationX(pageWidth / 10 * position * -1);
            }
            if (tut_d_3 != null) {
                tut_d_3.setTranslationX(pageWidth / 10 * position);
            }
            if (tut_d_4 != null) {
                tut_d_4.setAlpha(1.0f - Math.abs(position * 2));
                tut_d_4.setTranslationY(pageWidth / 20 * position);
            }
            if (tut_e_2 != null) {
                tut_e_2.setTranslationX(pageWidth / 10 * position);
            }
            if (tut_e_3 != null) {
                tut_e_3.setTranslationX(pageWidth / 10 * position);
                tut_e_3.setAlpha(1.0f - Math.abs(position));
            }
            if (tut_e_4 != null) {
                tut_e_4.setTranslationY(pageWidth / 20 * position * -1);

            }
        }
    }
}