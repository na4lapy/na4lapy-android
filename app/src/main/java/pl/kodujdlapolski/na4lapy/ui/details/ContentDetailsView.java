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

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.annotation.NonNull;
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

import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Years;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.kodujdlapolski.na4lapy.Na4LapyApp;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Photo;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.model.type.Gender;
import pl.kodujdlapolski.na4lapy.service.repository.RepositoryService;
import pl.kodujdlapolski.na4lapy.ui.CommonUI;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//TODO utworzyć presentera dla widoku
public class ContentDetailsView {

    private DetailsActivity ctx;
    private Animal animal;
    private static final int IMAGES_IN_ROW = 3;// defined in images_single_row.xml
    private static final int MAX_LINES_COLLAPSED = 5;

    @Inject
    RepositoryService repositoryService;

    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.expand_or_collapse_btn)
    Button expandOrCollapseBtn;
    @BindView(R.id.animal_size_image)
    ImageView sizeImage;
    @BindView(R.id.animal_gender_image)
    ImageView genderImage;
    @BindView(R.id.animal_activity_image)
    ImageView activityImage;
    @BindView(R.id.images_container)
    LinearLayout imagesContainer;

    @BindView(R.id.info_shelter_text)
    TextView infoShelterText;
    @BindView(R.id.info_shelter)
    TextView infoShelter;
    @BindView(R.id.info_activity_text)
    TextView infoActivityText;
    @BindView(R.id.info_activity)
    TextView infoActivity;
    @BindView(R.id.info_admittance_date_text)
    TextView infoAdmittanceDateText;
    @BindView(R.id.info_admittance_date)
    TextView infoAdmittanceDate;
    @BindView(R.id.info_chip_text)
    TextView infoChipText;
    @BindView(R.id.info_chip)
    TextView infoChip;
    @BindView(R.id.info_race_text)
    TextView infoRaceText;
    @BindView(R.id.info_race)
    TextView infoRace;
    @BindView(R.id.info_size_text)
    TextView infoSizeText;
    @BindView(R.id.info_size)
    TextView infoSize;
    @BindView(R.id.info_gender_text)
    TextView infoGenderText;
    @BindView(R.id.info_gender)
    TextView infoGender;
    @BindView(R.id.info_training_text)
    TextView infoTrainingText;
    @BindView(R.id.info_training)
    TextView infoTraining;
    @BindView(R.id.info_sterilization_text)
    TextView infoSterilizationText;
    @BindView(R.id.info_sterilization)
    TextView infoSterilization;
    @BindView(R.id.info_vaccination_text)
    TextView infoVaccinationText;
    @BindView(R.id.info_vaccination)
    TextView infoVaccination;

    public ContentDetailsView(DetailsActivity activity, Animal animal) {
        ((Na4LapyApp) activity.getApplication()).getComponent().inject(this);
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
        if (animal.getShelterid() != null)
            setShelterInfo(animal.getShelterid());

        if (animal.getAdmittanceDate() != null) {
            infoAdmittanceDate.setText(getShortDateTextFrom(animal.getAdmittanceDate()));
        } else { hideViewRow(infoAdmittanceDateText, infoAdmittanceDate); }

        if (animal.getActivity() != null) {
            infoActivity.setText(ctx.getString(animal.getActivity().getLabelResId()));
        } else { hideViewRow(infoActivityText, infoActivity); }

        if (animal.getChipId() != null) {
            infoChip.setText(animal.getChipId());
        } else { hideViewRow(infoChipText, infoChip); }

        if (animal.getRace() != null) {
            infoRace.setText(animal.getRace());
        } else { hideViewRow(infoRaceText, infoRace); }

        if (animal.getSize() != null) {
            infoSize.setText(ctx.getString(animal.getSize().getLabelResId()));
        } else { hideViewRow(infoSizeText, infoSize); }

        if (animal.getGender() != null) {
            if (animal.getGender().equals(Gender.FEMALE))
                infoSterilizationText.setText(R.string.details_sterilization);
            infoGender.setText(ctx.getString(animal.getGender().getLabelResId()));
        } else { hideViewRow(infoGenderText, infoGender); }

        if (animal.getTraining() != null) {
            infoTraining.setText(ctx.getString(animal.getTraining().getLabelResId()));
        } else { hideViewRow(infoTrainingText, infoTraining); }

        if (animal.getSterilization() != null) {
            infoSterilization.setText(ctx.getString(animal.getSterilization().getLabelResId()));
        } else { hideViewRow(infoSterilizationText, infoSterilization); }

        if (animal.getVaccination() != null) {
            infoVaccination.setText(ctx.getString(animal.getVaccination().getLabelResId()));
        } else { hideViewRow(infoVaccinationText, infoVaccination); }
    }

    private void hideViewRow(TextView label, TextView labelValue) {
        label.setVisibility(View.GONE);
        labelValue.setVisibility(View.GONE);
    }

    private void setShelterInfo(Long id) {
        repositoryService.getShelter(id).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onShelterAvailable, throwable -> CommonUI.onServerError(throwable, ctx));
    }

    private void onShelterAvailable(Shelter downloadedShelter) {
        if (downloadedShelter != null) {
            infoShelter.setText(downloadedShelter.toString());
        }
        else { hideViewRow(infoShelterText, infoShelter); }
    }

    private void initBasicInfoImagesAndDescription() {
        description.setText(animal.getDescription());
        description.setOnClickListener(v -> expandOrCollapseDescription());
        expandOrCollapseBtn.setText(R.string.more_info);
        expandOrCollapseBtn.setOnClickListener(v -> expandOrCollapseDescription());
        if (animal.getSize() != null) {
            sizeImage.setImageResource(animal.getSize().getDrawableResId());
        } else {
            sizeImage.setImageResource(R.drawable.animal_size_unknown_100dp);
        }
        if (animal.getActivity() != null) {
            activityImage.setImageResource(animal.getActivity().getDrawableResId());
        } else {
            activityImage.setImageResource(R.drawable.animal_activity_unknown_100dp);
        }
        if (animal.getGender() != null) {
            genderImage.setImageResource(animal.getGender().getDrawableResId());
        } else {
            genderImage.setImageResource(R.drawable.animal_gender_unknown_100dp);
        }
    }

    private void initImagesContainer() {
        ArrayList<Photo> photos = animal.getPhotos();
        if (photos != null) {
            LinearLayout.LayoutParams singleRowLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            singleRowLayoutParams.setMargins(0, getDipFromInt(2), 0, getDipFromInt(2));
            int restOfImagesCount = photos.size() % IMAGES_IN_ROW;
            for (int i = 0; i < photos.size()-restOfImagesCount; i = i + IMAGES_IN_ROW) {
                View singleRow = ctx.getLayoutInflater().inflate(R.layout.images_single_row, null);
                singleRow.setLayoutParams(singleRowLayoutParams);
                initImageView(singleRow, photos, i, R.id.image_1);
                initImageView(singleRow, photos, i + 1, R.id.image_2);
                initImageView(singleRow, photos, i + 2, R.id.image_3);
                imagesContainer.addView(singleRow);
            }

            if (restOfImagesCount >= 1) {
                View singleRow = ctx.getLayoutInflater().inflate(R.layout.images_single_row, null);
                singleRow.setLayoutParams(singleRowLayoutParams);
                initImageView(singleRow, photos, restOfImagesCount == 1 ? photos.size() - 1 : photos.size() - 2, R.id.image_1);
                if (restOfImagesCount == 2) {
                    initImageView(singleRow, photos, photos.size() - 1, R.id.image_2);
                }
                imagesContainer.addView(singleRow);
            }
        }
    }

    private void initImageView(View parent, ArrayList<Photo> photos, int index, int res) {
        ImageView image1 = (ImageView) parent.findViewById(res);
        image1.getLayoutParams().height = getGalleryPicHeight();
        Picasso.with(ctx).load(photos.get(index).getFullFileName()).into(image1);
        image1.setOnClickListener(v -> {
            Intent i = new Intent(ctx, AnimalGalleryActivity.class);
            i.putExtra(AnimalGalleryActivity.EXTRA_GALLERY, photos);
            i.putExtra(AnimalGalleryActivity.EXTRA_SELECTED_PIC, index);
            ctx.startActivity(i);
        });
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

    private String getShortDateTextFrom(@NonNull LocalDate date) {
        int age = Years.yearsBetween(date, LocalDate.now()).getYears();
        int fromStringRes = R.plurals.years_from;
        if (age == 0) {
            age = Months.monthsBetween(date, LocalDate.now()).getMonths();
            fromStringRes = R.plurals.months_from;
        }
        return ctx.getResources().getQuantityString(fromStringRes, age, age);
    }
}
