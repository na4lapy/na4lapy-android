package pl.kodujdlapolski.na4lapy.ui.details;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Photo;
import pl.kodujdlapolski.na4lapy.presenter.details.AnimalGalleryPresenter;
import pl.kodujdlapolski.na4lapy.ui.AbstractSingleActivity;

/**
 * Created by Malgorzata Syska on 2016-03-28.
 */
public class AnimalGalleryActivity extends AbstractSingleActivity {

    public static final String EXTRA_GALLERY = "EXTRA_GALLERY";
    public static final String EXTRA_SELECTED_PIC = "EXTRA_SELECTED_PIC";

    private List<Photo> gallery;
    private Integer selectedPicNumber;

    private AnimalGallerySectionsPagerAdapter mAnimalGallerySectionsPagerAdapter;
    private AnimalGalleryPresenter presenter;

    @BindView(R.id.gallery_container)
    GalleryViewPager mViewPager;

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_gallery);

        ButterKnife.bind(this);

        gallery = (ArrayList<Photo>) getIntent().getExtras().getSerializable(EXTRA_GALLERY);
        if (gallery == null) {
            onBackPressed();
        }
        selectedPicNumber = getIntent().getExtras().getInt(EXTRA_SELECTED_PIC);
        if (selectedPicNumber == null) {
            selectedPicNumber = 0;
        }

        presenter = new AnimalGalleryPresenter(this, gallery, mViewPager);
        mAnimalGallerySectionsPagerAdapter = presenter.getAdapter();

        if (mViewPager != null) {
            mViewPager.setAdapter(mAnimalGallerySectionsPagerAdapter);
            mViewPager.setCurrentItem(selectedPicNumber);
            mViewPager.setActivity(this);
        }
    }

}
