package pl.kodujdlapolski.na4lapy.ui.preferences;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.Toast;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.ui.ToggleImageButton;
import pl.kodujdlapolski.na4lapy.ui.UserPreferences;
import pl.kodujdlapolski.na4lapy.ui.UserPreferencesEditor;

public class PreferencesFragment extends Fragment {

    private ScrollView view;
    private Context context;
    private EditText ageMinPreference;
    private EditText ageMaxPreference;
    private NumberPicker ageNumberPicker;
    int age;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (ScrollView) inflater.inflate(R.layout.fragment_preferences, container, false);
        context = getContext();

        ageMinPreference = (EditText) view.findViewById(R.id.age_min);
        ageMaxPreference = (EditText) view.findViewById(R.id.age_max);

        setUserPreferencesToView();

        ageMinPreference.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAgePickerDialog(1, inflater);
                ageMinPreference.setText(String.valueOf(age));
            }
        });

        ageMaxPreference.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAgePickerDialog(2, inflater);
                ageMaxPreference.setText(String.valueOf(age));
            }
        });

        ImageButton saveButton = (ImageButton) view.findViewById(R.id.save_preferences);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserPreferencesEditor.saveUserPreferences(context, view);
                Toast.makeText(context, "Ustawienia zostały zapisane", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void displayAgePickerDialog(final int variable, LayoutInflater inflater) {

        //TODO poprawic wczytywanie widoku do dialogu
    //    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v1 = inflater.inflate(R.layout.dialog_age_picker_preference, null);

        ageNumberPicker = (NumberPicker) v1.findViewById(R.id.numberPicker);
        ageNumberPicker.setMinValue(0);
        ageNumberPicker.setMinValue(20);
        ageNumberPicker.setWrapSelectorWheel(false);
        switch (variable) {
            case 1:
                ageNumberPicker.setValue(Integer.valueOf(ageMinPreference.getText().toString()));
                break;
            case 2:
                ageNumberPicker.setValue(Integer.valueOf(ageMaxPreference.getText().toString()));
                break;
            default:
                break;
        }

        //TODO poprawic wczytywanie widoku do dialogu
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(PreferencesFragment.this.context);
        dialogBuilder.setView(v1);

        dialogBuilder.setTitle(R.string.age);
        dialogBuilder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                age = ageNumberPicker.getValue();
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

   //     agePickerDialog.setContentView(R.layout.dialog_age_picker_preference);
   //     agePickerDialog.setCanceledOnTouchOutside(false);
    }

    private void setUserPreferencesToView () {
        UserPreferences userPreferences = UserPreferencesEditor.getUserPreferences(context);

        ToggleImageButton typeDogPreference = (ToggleImageButton) view.findViewById(R.id.type_dog);
        typeDogPreference.setChecked(userPreferences.isTypeDog());

        ToggleImageButton typeCatPreference = (ToggleImageButton) view.findViewById(R.id.type_cat);
        typeCatPreference.setChecked(userPreferences.isTypeCat());

        ToggleImageButton typeOtherPreference = (ToggleImageButton) view.findViewById(R.id.type_other);
        typeOtherPreference.setChecked(userPreferences.isTypeOther());

        ToggleImageButton genderWomanPreference = (ToggleImageButton) view.findViewById(R.id.gender_woman);
        genderWomanPreference.setChecked(userPreferences.isGenderWoman());

        ToggleImageButton genderManPreference = (ToggleImageButton) view.findViewById(R.id.gender_man);
        genderManPreference.setChecked(userPreferences.isGenderMan());

        ageMinPreference.setText(String.valueOf(userPreferences.getAgeMin()));

        ageMaxPreference.setText(String.valueOf(userPreferences.getAgeMax()));

        ToggleImageButton sizeSmallPreference = (ToggleImageButton) view.findViewById(R.id.size_small);
        sizeSmallPreference.setChecked(userPreferences.isSizeSmall());

        ToggleImageButton sizeMediumPreference = (ToggleImageButton) view.findViewById(R.id.size_medium);
        sizeMediumPreference.setChecked(userPreferences.isSizeMedium());

        ToggleImageButton sizeLargePreference = (ToggleImageButton) view.findViewById(R.id.size_large);
        sizeLargePreference.setChecked(userPreferences.isSizeLarge());

        ToggleImageButton activityLowPreference = (ToggleImageButton) view.findViewById(R.id.activity_low);
        activityLowPreference.setChecked(userPreferences.isActivityLow());

        ToggleImageButton activityHighPreference = (ToggleImageButton) view.findViewById(R.id.activity_high);
        activityHighPreference.setChecked(userPreferences.isActivityHigh());

    }
}