package pl.kodujdlapolski.na4lapy.ui.details;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Photo;
import pl.kodujdlapolski.na4lapy.utils.AnimalUtils;

/**
 * Created by Natalia Wróblewska on 2016-04-13.
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
public class ContentDetailsView {

    private DetailsActivity ctx;
    private Animal animal;
    private static final int IMAGES_IN_ROW = 3;// defined in images_single_row.xml
    private static final int MAX_LINES_COLLAPSED = 5;

    @Bind(R.id.description)
    TextView description;
    @Bind(R.id.expand_or_collapse_btn)
    Button expandOrCollapseBtn;
    @Bind(R.id.animal_size_image)
    ImageView sizeImage;
    @Bind(R.id.animal_gender_image)
    ImageView genderImage;
    @Bind(R.id.animal_activity_image)
    ImageView activityImage;
    @Bind(R.id.images_container)
    LinearLayout imagesContainer;

    @Bind(R.id.info_activity)
    TextView infoActivity;
    @Bind(R.id.info_admittance_date)
    TextView infoAdmittanceDate;
    @Bind(R.id.info_chip)
    TextView infoChip;
    @Bind(R.id.info_race)
    TextView infoRace;
    @Bind(R.id.info_size)
    TextView infoSize;
    @Bind(R.id.info_gender)
    TextView infoGender;
    @Bind(R.id.info_training)
    TextView infoTraining;
    @Bind(R.id.info_sterilization)
    TextView infoSterilization;
    @Bind(R.id.info_vaccination)
    TextView infoVaccination;

    String[] picturesSample1 = new String[]{"http://2.bp.blogspot.com/-uI_rgOFxmT0/Vw6lkKwdTDI/AAAAAAAABLw/T0d2NW0Uc-MsYe1y6u6zvdUdCJxFv4uwACK4B/s1600/pies1_5.jpg",
            "http://4.bp.blogspot.com/-oMb5TMvvpRc/Vw6lkHw7n6I/AAAAAAAABL4/r2hdfKttHpUMPhtFGYgt7KkxqUOFNFVOACK4B/s1600/pies1_2.jpg",
            "http://2.bp.blogspot.com/-aud6eS52mpY/Vw6lkLZhTVI/AAAAAAAABLM/-GZ-1ZKRWJEoapaoSq6eKMbQdpS3Dn5ewCK4B/s1600/pies1_1.jpg",
            "http://3.bp.blogspot.com/-rm-MDinVQLM/Vw6lkJXGqBI/AAAAAAAABLQ/_RsLLleqWPcKt1WbqvQktYD8bVaOQ2tSACK4B/s1600/pies1_3.jpg",
            "http://1.bp.blogspot.com/-te0nTQS396o/Vw6lkE2QITI/AAAAAAAABLs/lmI7bApOE7UNubhXK8b45F1-W3bufZyJgCK4B/s1600/pies1_4.jpg"};
    String[] picturesSample2 = new String[]{"http://4.bp.blogspot.com/-zjCctidFhyA/Vw6lkEpvejI/AAAAAAAABLA/7Y375FdJBSgnNhHQLqj922KoJtRVQBDqACK4B/s1600/pies2_1.jpg",
            "http://4.bp.blogspot.com/-mJATJc3q74U/Vw6lkPHbZbI/AAAAAAAABK8/2ACbebDNoNU-6lH43d0OuvR5GRz5SdWugCK4B/s1600/pies2_2.jpg",
            "http://3.bp.blogspot.com/-SQ2Kvg-gnmg/Vw6lkIJmskI/AAAAAAAABLc/agy0Sp1pgeg6HgcGhw0HnXQruR0RWb-VgCK4B/s1600/pies2_3.jpg",
            "http://2.bp.blogspot.com/-wGjFf1_oGBw/Vw6lkLrVP2I/AAAAAAAABLg/Eybc3TCN2O476_03bn6q4uhGJxE726mnACK4B/s1600/pies2_4.jpg",
            "http://4.bp.blogspot.com/-EMbMfdKPh2s/Vw6lj-f8DrI/AAAAAAAABKw/PFgMwqg3-_IBTWz72bq_iwIytr4BPWKUACK4B/s1600/pies2_5.jpg"};

    public ContentDetailsView(DetailsActivity activity, Animal animal) {
        ctx = activity;
        this.animal = animal;
    }

    public View getView() {
        View content = ctx.getLayoutInflater().inflate(R.layout.content_details, null);
        imagesContainer = (LinearLayout) content.findViewById(R.id.images_container);
        ButterKnife.bind(this, content);
        initImagesContainer();
        initBasicInfoImagesAndDescription();
        initMoreInfoTable();
        return content;
    }

    private void initMoreInfoTable() {
        java.util.Date date = new java.util.Date(animal.getAdmittanceDate());
        infoActivity.setText(ctx.getString(animal.getActivity().resId));
        infoAdmittanceDate.setText(android.text.format.DateFormat.format("dd.MM.yyyy", date));
        infoChip.setText(animal.getChipId());
        infoRace.setText(animal.getRace());
        infoSize.setText(ctx.getString(animal.getSize().resId));
        infoGender.setText(ctx.getString(animal.getGender().resId));
        infoTraining.setText(ctx.getString(animal.getTraining().resId));
        infoSterilization.setText(animal.getSterilization() ? ctx.getString(R.string.yes) : ctx.getString(R.string.no));
        infoVaccination.setText(ctx.getString(animal.getVaccination().resId));
    }

    private void initBasicInfoImagesAndDescription() {
        description.setText(animal.getDescription());
        description.setOnClickListener(v -> expandOrCollapseDescription());
        expandOrCollapseBtn.setText(R.string.more_info);
        expandOrCollapseBtn.setOnClickListener(v -> expandOrCollapseDescription());
        sizeImage.setImageResource(AnimalUtils.getSizeImage(animal));
        activityImage.setImageResource(AnimalUtils.getActivityImage(animal));
        genderImage.setImageResource(AnimalUtils.getGenderImage(animal));
    }

    private void initImagesContainer() {
        // todo to be changed to real images
        String[] picturesSample = animal.getId() % 2 == 0 ? picturesSample1 : picturesSample2;
        LinearLayout.LayoutParams singleRowLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        singleRowLayoutParams.setMargins(0, getDipFromInt(8), 0, getDipFromInt(8));
        for (int i = 0; i < picturesSample.length / IMAGES_IN_ROW; i = i + IMAGES_IN_ROW) {
            View singleRow = ctx.getLayoutInflater().inflate(R.layout.images_single_row, null);
            singleRow.setLayoutParams(singleRowLayoutParams);
            initImageView(singleRow, picturesSample, i, R.id.image_1);
            initImageView(singleRow, picturesSample, i + 1, R.id.image_2);
            initImageView(singleRow, picturesSample, i + 2, R.id.image_3);
            imagesContainer.addView(singleRow);
        }
        int restOfImagesCount = picturesSample.length % IMAGES_IN_ROW;
        if (restOfImagesCount >= 1) {
            View singleRow = ctx.getLayoutInflater().inflate(R.layout.images_single_row, null);
            singleRow.setLayoutParams(singleRowLayoutParams);
            initImageView(singleRow, picturesSample, restOfImagesCount == 1 ? picturesSample.length - 1 : picturesSample.length - 2, R.id.image_1);
            if (restOfImagesCount == 2) {
                initImageView(singleRow, picturesSample, picturesSample.length - 1, R.id.image_2);
            }
            imagesContainer.addView(singleRow);
        }
    }

    private void initImageView(View parent, String[] urls, int index, int res) {
        ImageView image1 = (ImageView) parent.findViewById(res);
        image1.getLayoutParams().height = getGalleryPicHeight();
        Picasso.with(ctx).load(urls[index]).into(image1);
        image1.setOnClickListener(v -> {
            Intent i = new Intent(ctx, AnimalGalleryActivity.class);
            i.putExtra(AnimalGalleryActivity.EXTRA_GALLERY, getGallery(urls));
            i.putExtra(AnimalGalleryActivity.EXTRA_SELECTED_PIC, index);
            ctx.startActivity(i);
        });
    }

    private ArrayList<Photo> getGallery(String[] urls) {
        ArrayList<Photo> photos = new ArrayList<>();
        for (String s : urls) {
            Photo p = new Photo();
            p.setUrl(s);
            photos.add(p);
        }
        return photos;
    }

    private int getDipFromInt(int value) {
        return (int) (value * TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, ctx.getResources()
                .getDisplayMetrics()));
    }

    private int getGalleryPicHeight() {
        Display display = ((WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int margin = ((int) (ctx.getResources().getDimension(R.dimen.activity_horizontal_margin))) * 4; // because of scrollview margin and inner images margin
        int actualWidth = width - margin;
        return actualWidth / IMAGES_IN_ROW;
    }

    private void expandOrCollapseDescription() {
        boolean isExpanded = description.getMaxLines() == MAX_LINES_COLLAPSED;
        ObjectAnimator animation = ObjectAnimator.ofInt(description, "maxLines",
                isExpanded ? description.getLineCount() : MAX_LINES_COLLAPSED);
        animation.setDuration(200).start();
        expandOrCollapseBtn.setText(isExpanded ? R.string.less_info : R.string.more_info);
    }
}
