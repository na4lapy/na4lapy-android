package pl.kodujdlapolski.na4lapy.ui.main.activity;

import android.os.Bundle;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.Na4LapyApp;
import pl.kodujdlapolski.na4lapy.repository.RepositoryService;
import pl.kodujdlapolski.na4lapy.ui.AbstractDrawerActivity;

public class SplashActivity extends AbstractDrawerActivity {

    @Inject
    RepositoryService repositoryService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Na4LapyApp) getApplication()).getComponent().inject(this);
    }

}
