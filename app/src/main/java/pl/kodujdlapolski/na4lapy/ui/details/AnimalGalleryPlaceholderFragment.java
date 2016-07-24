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
package pl.kodujdlapolski.na4lapy.ui.details;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Photo;
import pl.kodujdlapolski.na4lapy.presenter.details.AnimalGalleryContract;
import pl.kodujdlapolski.na4lapy.presenter.details.AnimalGalleryPresenter;

public class AnimalGalleryPlaceholderFragment extends Fragment implements AnimalGalleryContract.View{

    private static final String ARG_SELECTED_PHOTO = "ARG_SELECTED_PHOTO";
    private static final String ARG_PIC_POSITION = "ARG_PIC_POSITION";
    private static final String ARG_GALLERY_SIZE = "ARG_GALLERY_SIZE";

    private AnimalGalleryPresenter presenter;
    private Photo animalPic;
    private int galleryPosition;
    private int gallerySize;

    @BindView(R.id.photo_author)
    TextView photoAuthor;
    @BindView(R.id.photo_number)
    TextView photoFullNumber;
    @BindView(R.id.animal_pic_in_gallery)
    ImageView imageView;
    @BindView(R.id.gallery_navigation_arrow_back)
    ImageButton galleryNavigationArrowBack;
    @BindView(R.id.gallery_navigation_arrow_next)
    ImageButton galleryNavigationArrowNext;

    public AnimalGalleryPlaceholderFragment() {
    }

    public static AnimalGalleryPlaceholderFragment newInstance(
            Photo selectedPic, int position, int gallerySize) {
        AnimalGalleryPlaceholderFragment fragment = new AnimalGalleryPlaceholderFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SELECTED_PHOTO, selectedPic);
        args.putInt(ARG_PIC_POSITION, position);
        args.putInt(ARG_GALLERY_SIZE, gallerySize);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animal_gallery, container, false);
        ButterKnife.bind(this, view);
        presenter = new AnimalGalleryPresenter((AnimalGalleryActivity) getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getView() == null) {
            return;
        }
        getPhotoFromArguments (savedInstanceState);
        loadPhotoIntoImageView(savedInstanceState);
        displayNavigationArrows();
    }

    @Override
    public void getPhotoFromArguments (Bundle savedInstanceState) {
        if (getArguments() != null
                && (animalPic == null)
                && getArguments().getSerializable(ARG_SELECTED_PHOTO) instanceof Photo) {
            animalPic = (Photo) (getArguments().getSerializable(ARG_SELECTED_PHOTO));
        }
        if (animalPic == null && savedInstanceState != null && savedInstanceState.getSerializable(ARG_SELECTED_PHOTO) instanceof Photo) {
            animalPic = (Photo) savedInstanceState.getSerializable(ARG_SELECTED_PHOTO);
        }

    }

    @Override
    public void loadPhotoIntoImageView(Bundle savedInstanceState) {
        String selectedPicUrl = animalPic.getUrl();
        if (TextUtils.isEmpty(selectedPicUrl)) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.pic_error_dog_256dp));
            return;
        }
        Picasso.with(getContext()).load(selectedPicUrl).into(imageView);
        setPhotoAuthor();
        setDifferentLayoutParamsIfApiIsJellyBean();
        setPhotoNumber(savedInstanceState);
    }

    private void setPhotoAuthor() {
        String aboutAuthor = animalPic.getAuthor();
        if (!TextUtils.isEmpty(aboutAuthor)) {
            photoAuthor.setText(getString(R.string.photo_by, aboutAuthor));
        }
    }

    //todo delete this comment before next update of app
    // If API is 16, layout width param is set to "match parent" and scale type is set to "fit center",
    // there is a need to change layout width to "wrap content" to properly display text view with author of photo,
    // because API 16 does not scale image view to fit the screen
    private void setDifferentLayoutParamsIfApiIsJellyBean() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            return;
          }
        RelativeLayout.LayoutParams layoutParams =
                (RelativeLayout.LayoutParams)imageView.getLayoutParams();
        layoutParams.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
        imageView.setLayoutParams(layoutParams);
    }

    private void setPhotoNumber(Bundle savedInstanceState) {
        if (getArguments() == null) {
            return;
        }
        galleryPosition = getArguments().getInt(ARG_PIC_POSITION) + 1;
        gallerySize = getArguments().getInt(ARG_GALLERY_SIZE);

        if (gallerySize == 0 && savedInstanceState != null) {
            gallerySize = savedInstanceState.getInt(ARG_GALLERY_SIZE);
        }
        photoFullNumber.setText(getString(R.string.number_of_photo, galleryPosition, gallerySize));
    }

    @Override
    public void displayNavigationArrows() {
        if (galleryPosition == 1) {
            galleryNavigationArrowBack.setVisibility(View.GONE);
            galleryNavigationArrowBack.setClickable(false);
        }
        if (galleryPosition == gallerySize) {
            galleryNavigationArrowNext.setVisibility(View.GONE);
            galleryNavigationArrowNext.setClickable(false);
        }
    }

    @OnClick(R.id.gallery_navigation_arrow_next)
    void setNextPhoto() {
        presenter.setNextPhoto();
    }

    @OnClick(R.id.gallery_navigation_arrow_back)
    void setPreviousPhoto() {
        presenter.setPreviousPhoto();
    }

    @OnClick(R.id.animal_gallery_fragment)
    protected void getBack(){
        presenter.setOnBackPressed();
    }
}