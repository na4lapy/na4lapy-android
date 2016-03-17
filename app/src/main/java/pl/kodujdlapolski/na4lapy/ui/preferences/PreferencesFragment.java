package pl.kodujdlapolski.na4lapy.ui.preferences;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.UserPreferences;
import pl.kodujdlapolski.na4lapy.presenter.PreferencesContract;
import pl.kodujdlapolski.na4lapy.presenter.PreferencesPresenter;
import pl.kodujdlapolski.na4lapy.ui.ToggleImageButton;

public class PreferencesFragment extends Fragment implements PreferencesContract.View {

    private ScrollView view;
    private Context context;
    private LayoutInflater inflater;

    PreferencesPresenter presenter;
    UserPreferences userPreferences;

    @Bind(R.id.type_dog)
    ToggleImageButton typeDogPreference;
    @Bind(R.id.type_cat)
    ToggleImageButton typeCatPreference;
    @Bind(R.id.type_other)
    ToggleImageButton typeOtherPreference;
    @Bind(R.id.gender_woman)
    ToggleImageButton genderWomanPreference;
    @Bind(R.id.gender_man)
    ToggleImageButton genderManPreference;
    @Bind(R.id.age_min)
    EditText ageMinPreference;
    @Bind(R.id.age_max)
    EditText ageMaxPreference;
    @Bind(R.id.size_small)
    ToggleImageButton sizeSmallPreference;
    @Bind(R.id.size_medium)
    ToggleImageButton sizeMediumPreference;
    @Bind(R.id.size_large)
    ToggleImageButton sizeLargePreference;
    @Bind(R.id.activity_low)
    ToggleImageButton activityLowPreference;
    @Bind(R.id.activity_high)
    ToggleImageButton activityHighPreference;

    NumberPicker ageNumberPicker;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = (ScrollView) inflater.inflate(R.layout.fragment_preferences, container, false);
        this.inflater = inflater;
        ButterKnife.bind(this, view);
        presenter = new PreferencesPresenter(getActivity(), this);
        context = getContext();

        return view;
    }

    @OnClick(R.id.age_min)
    void displayMinAgePickerDialog() {
        displayAgePickerDialog(true);
    }

    @OnClick(R.id.age_max)
    void displayMaxAgePickerDialog() {
        displayAgePickerDialog(false);
    }

    private void displayAgePickerDialog(final boolean isMinAgePicker) {

        View v1 = inflater.inflate(R.layout.dialog_age_picker_preference, null);
        ageNumberPicker = (NumberPicker) v1.findViewById(R.id.numberPicker);

        ageNumberPicker.setMinValue(0);
        ageNumberPicker.setMaxValue(20);
        ageNumberPicker.setWrapSelectorWheel(false);

        if(isMinAgePicker) {
            ageNumberPicker.setValue(Integer.valueOf(ageMinPreference.getText().toString()));
        }
        else {
            ageNumberPicker.setValue(Integer.valueOf(ageMaxPreference.getText().toString()));
        }

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setView(v1);

        dialogBuilder.setTitle(R.string.age);
        dialogBuilder.setPositiveButton(R.string.choose, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (isMinAgePicker) {
                    ageMinPreference.setText(String.valueOf(ageNumberPicker.getValue()));
                } else {
                    ageMaxPreference.setText(String.valueOf(ageNumberPicker.getValue()));
                }
                dialog.dismiss();
            }
        });
        dialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        Dialog agePickerDialog = dialogBuilder.create();
        agePickerDialog.show();

    //    agePickerDialog.getWindow().setBackgroundDrawableResource(R.color.colorPrimaryLight);     //sets color for whole dialog window
    }

    @Override
    public void showUserPreferences(UserPreferences userPreferences) {
        this.userPreferences = userPreferences;

        typeDogPreference.setChecked(this.userPreferences.isTypeDog());
        typeCatPreference.setChecked(this.userPreferences.isTypeCat());
        typeOtherPreference.setChecked(this.userPreferences.isTypeOther());
        genderWomanPreference.setChecked(this.userPreferences.isGenderWoman());
        genderManPreference.setChecked(this.userPreferences.isGenderMan());
        ageMinPreference.setText(String.valueOf(this.userPreferences.getAgeMin()));
        ageMaxPreference.setText(String.valueOf(this.userPreferences.getAgeMax()));
        sizeSmallPreference.setChecked(this.userPreferences.isSizeSmall());
        sizeMediumPreference.setChecked(this.userPreferences.isSizeMedium());
        sizeLargePreference.setChecked(this.userPreferences.isSizeLarge());
        activityLowPreference.setChecked(this.userPreferences.isActivityLow());
        activityHighPreference.setChecked(this.userPreferences.isActivityHigh());
    }

    @OnClick(R.id.save_preferences)
    void save() {

        this.userPreferences.setTypeDog(typeDogPreference.isChecked());
        this.userPreferences.setTypeCat(typeCatPreference.isChecked());
        this.userPreferences.setTypeOther(typeOtherPreference.isChecked());
        this.userPreferences.setGenderWoman(genderWomanPreference.isChecked());
        this.userPreferences.setGenderMan(genderManPreference.isChecked());
        this.userPreferences.setAgeMin(Integer.valueOf(ageMinPreference.getText().toString()));
        this.userPreferences.setAgeMax(Integer.valueOf(ageMaxPreference.getText().toString()));
        this.userPreferences.setSizeSmall(sizeSmallPreference.isChecked());
        this.userPreferences.setSizeMedium(sizeMediumPreference.isChecked());
        this.userPreferences.setSizeLarge(sizeLargePreference.isChecked());
        this.userPreferences.setActivityLow(activityLowPreference.isChecked());
        this.userPreferences.setActivityHigh(activityHighPreference.isChecked());

        presenter.savePreferences(userPreferences);
        Toast.makeText(context, R.string.save_preferences_message, Toast.LENGTH_SHORT).show();
    }

}