/*
 * Copyright (C) 2016 [name of copyright owner]
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
package pl.kodujdlapolski.na4lapy.presenter.details;

import java.util.ArrayList;
import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Photo;
import pl.kodujdlapolski.na4lapy.ui.details.AnimalGalleryActivity;
import pl.kodujdlapolski.na4lapy.ui.details.GalleryViewPager;

public class AnimalGalleryPresenter implements AnimalGalleryContract.UserActionListener{

    public static final String EXTRA_GALLERY = AnimalGalleryActivity.EXTRA_GALLERY;
    public static final String EXTRA_SELECTED_PIC = AnimalGalleryActivity.EXTRA_SELECTED_PIC;

    private AnimalGalleryActivity activity;
    private GalleryViewPager galleryViewPager;

    public AnimalGalleryPresenter (AnimalGalleryActivity activity) {
        this.activity = activity;
        galleryViewPager = this.activity.getMViewPager();
    }

    @SuppressWarnings("unchecked")
    public List<Photo> getAnimalGallery() {
        List<Photo> gallery = (ArrayList<Photo>) activity.getIntent().getExtras().getSerializable(EXTRA_GALLERY);

        if (gallery == null || gallery.isEmpty()) {
            setOnBackPressed();
        }
        return gallery;
    }

    @Override
    public Integer getSelectedPhotoNumber() {
        return activity.getIntent().getExtras().getInt(EXTRA_SELECTED_PIC, 0);
    }

    @Override
    public void setNextPhoto() {
        galleryViewPager.setCurrentItem(galleryViewPager.getCurrentItem() + 1, true);
    }

    @Override
    public void setPreviousPhoto() {
        galleryViewPager.setCurrentItem(galleryViewPager.getCurrentItem() - 1, true);
    }

    @Override
    public void setOnBackPressed(){
        activity.onBackPressed();
    }
}
