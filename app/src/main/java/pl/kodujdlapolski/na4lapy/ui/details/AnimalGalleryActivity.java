package pl.kodujdlapolski.na4lapy.ui.details;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
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
    public static final String EXRTA_ANIMAL_NAME = "EXTRA_ANIMAL_NAME";

    private List<Photo> gallery;
    private Integer selectedPicNumber;

    private AnimalGallerySectionsPagerAdapter mAnimalGallerySectionsPagerAdapter;
    private AnimalGalleryPresenter presenter;

    @Bind(R.id.gallery_container)
    GalleryViewPager mViewPager;

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_gallery);

        ButterKnife.bind(this);

 //       gallery = (ArrayList<Photo>) savedInstanceState.getSerializable("EXTRA_GALLERY");
 //       selectedPicNumber = savedInstanceState.getInt("EXTRA_SELECTED_PIC");
 //       setTitle(savedInstanceState.getString("EXTRA_ANIMAL_NAME"));
//todo po przygotowaniu odpowiedniej aktywnosci (karty zwierzaka) - zastapic tymczasowy kod - docelowym (powyzej)
 // tymczasowo
        gallery = new ArrayList<>();
        Photo photo = new Photo();
        photo.setUrl("http://cdn23.se.smcloud.net/t/photos/t/389279/labrador-retriever-pies_23597760.jpg");
        gallery.add(photo);
        photo = new Photo();
        photo.setUrl("http://www.dobrylekarz.info/files/images/pies-sennik-sen-tlumaczenie-interpretacja-snu-pies.jpg");
        gallery.add(photo);
        photo = new Photo();
        photo.setUrl("http://bi.gazeta.pl/im/48/ec/f7/z16247880Q,Pies-byl-pierwszym-zwierzeciem-idomowionym-przez-c.jpg");
        gallery.add(photo);
        photo = new Photo();
        photo.setUrl("http://schroniskopromyk.pl/wp-content/uploads/2016/02/Morus-5-150x150.jpg");
        gallery.add(photo);
 //tymczasowo
        selectedPicNumber = 1;
 //tymczasowo
        setTitle("Bodek");

        presenter = new AnimalGalleryPresenter(this, gallery);
        mAnimalGallerySectionsPagerAdapter = presenter.getAdapter();

        mViewPager = (GalleryViewPager) findViewById(R.id.gallery_container);
        mViewPager.setAdapter(mAnimalGallerySectionsPagerAdapter);
        mViewPager.setCurrentItem(selectedPicNumber);
        mViewPager.setActivity(this);
    }


}
