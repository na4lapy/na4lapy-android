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
package pl.kodujdlapolski.na4lapy.ui.details;

import android.os.Bundle;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Photo;
import pl.kodujdlapolski.na4lapy.presenter.details.AnimalGalleryPresenter;
import pl.kodujdlapolski.na4lapy.ui.AbstractSingleActivity;

public class AnimalGalleryActivity extends AbstractSingleActivity {

    public static final String EXTRA_GALLERY = "EXTRA_GALLERY";
    public static final String EXTRA_SELECTED_PIC = "EXTRA_SELECTED_PIC";

    @Getter
    @BindView(R.id.gallery_container)
    GalleryViewPager mViewPager;

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AnimalGalleryPresenter presenter = new AnimalGalleryPresenter(this);
        List<Photo> gallery = presenter.getAnimalGallery();
        Integer selectedPicNumber = presenter.getSelectedPhotoNumber();

        setContentView(R.layout.activity_animal_gallery);
        ButterKnife.bind(this);

        AnimalGallerySectionsPagerAdapter mAnimalGallerySectionsPagerAdapter = new AnimalGallerySectionsPagerAdapter(
                getSupportFragmentManager(), gallery);

        if (mViewPager != null) {
            mViewPager.setAdapter(mAnimalGallerySectionsPagerAdapter);
            mViewPager.setCurrentItem(selectedPicNumber);
            mViewPager.setActivity(this);
        }
    }

}
