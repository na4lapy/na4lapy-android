package pl.kodujdlapolski.na4lapy;

import android.app.Application;

public class Na4LapyApp extends Application {

    private Na4LapyComponent na4LapyComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        na4LapyComponent = DaggerNa4LapyComponent.builder()
                .na4LapyModule(new Na4LapyModule(this))
                .build();
    }

    public Na4LapyComponent getComponent() {
        return na4LapyComponent;
    }
}
