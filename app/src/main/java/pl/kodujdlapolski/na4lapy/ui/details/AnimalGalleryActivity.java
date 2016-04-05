package pl.kodujdlapolski.na4lapy.ui.details;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Photo;
import pl.kodujdlapolski.na4lapy.presenter.AnimalGalleryPresenter;
import pl.kodujdlapolski.na4lapy.ui.AbstractSingleActivity;

public class AnimalGalleryActivity extends AbstractSingleActivity {

    public static final String EXTRA_GALLERY = "EXTRA_GALLERY";
    public static final String EXTRA_SELECTED_PIC = "EXTRA_SELECTED_PIC";
    public static final String EXRTA_ANIMAL_NAME = "EXTRA_ANIMAL_NAME";

    private List<Photo> gallery;
    private Integer selectedPicNumber;

    private AnimalGallerySectionsPagerAdapter mAnimalGallerySectionsPagerAdapter;
    private AnimalGalleryPresenter presenter;

    @Bind(R.id.gallery_container)
    ViewPager mViewPager;

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
        photo.setUrl("http://schroniskopromyk.pl/wp-content/uploads/2016/03/Bodek-1-150x150.jpg");
        gallery.add(photo);
        photo = new Photo();
        photo.setUrl("http://schroniskopromyk.pl/wp-content/uploads/2016/03/Bobo-1-150x150.jpg");
        gallery.add(photo);
        photo = new Photo();
        photo.setUrl("http://schroniskopromyk.pl/wp-content/uploads/2016/03/Fado-5-150x150.jpg");
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

        mViewPager = (ViewPager) findViewById(R.id.gallery_container);
        mViewPager.setAdapter(mAnimalGallerySectionsPagerAdapter);
        mViewPager.setCurrentItem(selectedPicNumber);

    }


}
