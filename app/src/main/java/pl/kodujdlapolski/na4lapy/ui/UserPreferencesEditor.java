package pl.kodujdlapolski.na4lapy.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.UserPreferences;

/**
 * Created by Gosia on 2016-03-06.
 */
public class UserPreferencesEditor {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static void saveUserPreferences(Context context, View view) {

        sharedPreferences = context.getSharedPreferences("User preferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editor.putBoolean("Dog type", ((ToggleImageButton) view.findViewById(R.id.type_dog)).isChecked());
        editor.putBoolean("Cat type", ((ToggleImageButton) view.findViewById(R.id.type_cat)).isChecked());
        editor.putBoolean("Other type", ((ToggleImageButton) view.findViewById(R.id.type_other)).isChecked());
        editor.putBoolean("Woman gender", ((ToggleImageButton) view.findViewById(R.id.gender_woman)).isChecked());
        editor.putBoolean("Man gender", ((ToggleImageButton) view.findViewById(R.id.gender_man)).isChecked());

        EditText ageMinPicker = (EditText) view.findViewById(R.id.age_min);
        Integer ageMin = Integer.valueOf(ageMinPicker.getText().toString());
        editor.putInt("Age minimum", ageMin);
        EditText ageMaxPicker = (EditText) view.findViewById(R.id.age_max);
        Integer ageMax = Integer.valueOf(ageMaxPicker.getText().toString());
        editor.putInt("Age maximum", ageMax);

        editor.putBoolean("Small size", ((ToggleImageButton) view.findViewById(R.id.size_small)).isChecked());
        editor.putBoolean("Medium size", ((ToggleImageButton) view.findViewById(R.id.size_medium)).isChecked());
        editor.putBoolean("Large size", ((ToggleImageButton) view.findViewById(R.id.size_large)).isChecked());
        editor.putBoolean("Activity low", ((ToggleImageButton) view.findViewById(R.id.activity_low)).isChecked());
        editor.putBoolean("Activity high", ((ToggleImageButton) view.findViewById(R.id.activity_high)).isChecked());

        editor.apply();
    }

    public static UserPreferences getUserPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences("User preferences", Context.MODE_PRIVATE);
        UserPreferences userPreferences = new UserPreferences(
                sharedPreferences.getBoolean("Dog type", true),
                sharedPreferences.getBoolean("Cat type", true),
                sharedPreferences.getBoolean("Other type", true),
                sharedPreferences.getBoolean("Woman gender", true),
                sharedPreferences.getBoolean("Man gender", true),
                sharedPreferences.getInt("Age minimum", 0),
                sharedPreferences.getInt("Age maximum", 16),
                sharedPreferences.getBoolean("Small size", true),
                sharedPreferences.getBoolean("Medium size", true),
                sharedPreferences.getBoolean("Large size", true),
                sharedPreferences.getBoolean("Activity low", true),
                sharedPreferences.getBoolean("Activity high", true));
        return userPreferences;
    }
}
