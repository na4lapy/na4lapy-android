package pl.kodujdlapolski.na4lapy.ui.preferences;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.ui.ToggleImageButton;
import pl.kodujdlapolski.na4lapy.ui.UserPreferences;
import pl.kodujdlapolski.na4lapy.ui.UserPreferencesEditor;

public class PreferencesFragment extends Fragment {

    private ScrollView view;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (ScrollView) inflater.inflate(R.layout.fragment_preferences, container, false);
        context = getContext();
        setUserPreferencesToView();

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

        EditText ageMinPreference = (EditText) view.findViewById(R.id.age_min);
        ageMinPreference.setText(userPreferences.getAgeMin().toString());

        EditText ageMaxPreference = (EditText) view.findViewById(R.id.age_max);
        ageMaxPreference.setText(userPreferences.getAgeMax().toString());

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