package pl.kodujdlapolski.na4lapy.ui.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Photo;
import pl.kodujdlapolski.na4lapy.presenter.details.AnimalGalleryPresenter;

/**
 * Created by Malgorzata Syska on 2016-03-29.
 */
public class AnimalGalleryPlaceholderFragment extends Fragment {

    private static final String ARG_PIC_NUMBER = "ARG_PIC_NUMBER";
    private Photo animalPic;

    public AnimalGalleryPlaceholderFragment() {
    }

    public static AnimalGalleryPlaceholderFragment newInstance(Photo selectedPic, AnimalGalleryPresenter presenter) {
        AnimalGalleryPlaceholderFragment fragment = new AnimalGalleryPlaceholderFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PIC_NUMBER, selectedPic);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_animal_gallery, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadPicture(savedInstanceState);
    }

    private void loadPicture (Bundle savedInstanceState) {
        if (getArguments() != null && (animalPic == null)) {
            if (getArguments().getSerializable(ARG_PIC_NUMBER) instanceof Photo) {
                animalPic = (Photo) (getArguments().getSerializable(ARG_PIC_NUMBER));
            }
        }
        if (animalPic == null && savedInstanceState != null && savedInstanceState.getSerializable(ARG_PIC_NUMBER) instanceof Photo) {
            animalPic = (Photo) savedInstanceState.getSerializable(ARG_PIC_NUMBER);
        }

        ImageView imageView = (ImageView) getView().findViewById(R.id.animal_pic_in_gallery);
        if (imageView != null) {
            setPicture(imageView);
        }
    }

    private void setPicture(ImageView imageView) {
        String selectedPicUrl = animalPic.getUrl();
        if (selectedPicUrl == null) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.pic_404dog));
            return;
        }
        Picasso.with(getContext()).load(selectedPicUrl).into(imageView);
    }
}