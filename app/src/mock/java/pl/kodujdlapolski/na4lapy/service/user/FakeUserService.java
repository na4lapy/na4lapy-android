package pl.kodujdlapolski.na4lapy.service.user;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.UserPreferences;

public class FakeUserService implements UserService {

    public FakeUserService() {
    }

    @Override
    public void saveCurrentUserPreferences(@NonNull UserPreferences userPreferences) {
    }

    @NonNull
    @Override
    public UserPreferences loadCurrentUserPreferences() {
        UserPreferences up = new UserPreferences();
        up.setTypeDog(true);
        up.setGenderMan(true);
        up.setSizeLarge(true);
        return up;
    }

    @Override
    public int getPreferencesComplianceLevel(Animal animal) {
        Random random = new Random();
        return random.nextInt(6);
    }

    @Override
    public boolean isComplianceLevelAvailable() {
        return true;
    }

    @Override
    public void addToFavourite(Animal animal) {

    }

    @Override
    public void removeFromFavourite(Animal animal) {

    }

    @Override
    public boolean isFavourite(Animal animal) {
        return false;
    }

    @Override
    public List<Animal> sortByUserPreferences(List<Animal> animals) {
        return new ArrayList<>();
    }
}
