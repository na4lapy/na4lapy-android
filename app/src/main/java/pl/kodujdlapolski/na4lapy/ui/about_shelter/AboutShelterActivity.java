package pl.kodujdlapolski.na4lapy.ui.about_shelter;

import android.os.Bundle;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.ui.AbstractSingleActivity;

public class AboutShelterActivity extends AbstractSingleActivity {

    public static final String EXTRA_SHELTER_ID = "EXTRA_SHELTER_ID";
    private Long shelterId;
// TODO many shelters handling 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_shelter);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            shelterId = b.getLong(EXTRA_SHELTER_ID);
        }
        if (shelterId == null && savedInstanceState != null) {
            shelterId = savedInstanceState.getLong(EXTRA_SHELTER_ID);
        }
        if (shelterId == null)
            finish(); // as nothing can be done without it
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putLong(EXTRA_SHELTER_ID, shelterId);
        super.onSaveInstanceState(outState);
    }

    public Long getShelterId() {
        return shelterId;
    }
}
