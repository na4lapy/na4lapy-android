package pl.kodujdlapolski.na4lapy.ui.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Photo;
import pl.kodujdlapolski.na4lapy.presenter.details.AnimalGalleryPresenter;

/**
 * Created by Malgorzata Syska on 2016-03-29.
 */
public class AnimalGalleryPlaceholderFragment extends Fragment {

    private static final String ARG_SELECTED_PHOTO = "ARG_SELECTED_PHOTO";
    private static final String ARG_PIC_POSITION = "ARG_PIC_POSITION";
    private static final String ARG_GALLERY_SIZE = "ARG_GALLERY_SIZE";
    private Photo animalPic;
    private int position = -1;
    private int gallerySize = -1;
    @BindView(R.id.photo_author)
    TextView photoAuthor;
    @BindView(R.id.photo_number)
    TextView photoNumber;

    public AnimalGalleryPlaceholderFragment() {
    }

    public static AnimalGalleryPlaceholderFragment newInstance(
            Photo selectedPic, AnimalGalleryPresenter presenter, int position, int gallerySize) {
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
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadPhoto(savedInstanceState);
    }

    @OnClick(R.id.animal_gallery_fragment)
    protected void getBack(){
        getActivity().onBackPressed();
    }

    private void loadPhoto(Bundle savedInstanceState) {
        if (getArguments() != null
                && (animalPic == null)
                && getArguments().getSerializable(ARG_SELECTED_PHOTO) instanceof Photo) {
            animalPic = (Photo) (getArguments().getSerializable(ARG_SELECTED_PHOTO));
        }
        if (animalPic == null && savedInstanceState != null && savedInstanceState.getSerializable(ARG_SELECTED_PHOTO) instanceof Photo) {
            animalPic = (Photo) savedInstanceState.getSerializable(ARG_SELECTED_PHOTO);
        }

        View view = getView();
        if (view != null) {
            ImageView imageView = (ImageView) view.findViewById(R.id.animal_pic_in_gallery);
            setPhoto(imageView, savedInstanceState);
        }
    }

    private void setPhoto (ImageView imageView, Bundle savedInstanceState) {
        String selectedPicUrl = animalPic.getUrl();
        if (TextUtils.isEmpty(selectedPicUrl)) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.vector_drawable_error_dog));
            return;
        }
        Picasso.with(getContext()).load(selectedPicUrl).into(imageView);
        setPhotoAuthor();
        setPhotoNumber(savedInstanceState);
    }

    private void setPhotoAuthor() {
        String aboutAuthor = animalPic.getAuthor();
        if (!TextUtils.isEmpty(aboutAuthor)) {
            photoAuthor.setText(getString(R.string.photo_by, aboutAuthor));
        }
    }

    private void setPhotoNumber(Bundle savedInstanceState) {

        if ((getArguments() != null) && (this.position == -1)) {
            position = getArguments().getInt(ARG_PIC_POSITION) + 1;
        }
        if ((getArguments() != null) && (this.gallerySize == -1)) {
            gallerySize = getArguments().getInt(ARG_GALLERY_SIZE);
        }
        if (gallerySize == 0 && savedInstanceState != null) {
            gallerySize = savedInstanceState.getInt(ARG_GALLERY_SIZE);
        }

        if (position == 1) {
            photoNumber.setText(getString(R.string.number_of_photo_first, position, gallerySize));
        } else if (position == gallerySize) {
            photoNumber.setText(getString(R.string.number_of_photo_last, position, gallerySize));
        } else {
            photoNumber.setText(getString(R.string.number_of_photo_default, position, gallerySize));
        }
    }
}