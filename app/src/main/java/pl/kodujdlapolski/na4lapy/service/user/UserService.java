package pl.kodujdlapolski.na4lapy.service.user;

import android.support.annotation.NonNull;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.UserPreferences;

public interface UserService {

    void saveCurrentUserPreferences(@NonNull UserPreferences userPreferences);

    @NonNull UserPreferences loadCurrentUserPreferences();

    int getPreferencesComplianceLevel(Animal animal);

    boolean isComplianceLevelAvailable();

    void addToFavourite(Animal animal);
    void removeFromFavourite(Animal animal);
    boolean isFavourite(Animal animal);
    List<Animal> sortByUserPreferences(List<Animal> animals);

}
