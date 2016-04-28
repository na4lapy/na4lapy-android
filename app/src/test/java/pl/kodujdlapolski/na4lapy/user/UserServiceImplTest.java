package pl.kodujdlapolski.na4lapy.user;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.UserPreferences;
import pl.kodujdlapolski.na4lapy.model.type.ActivityAnimal;
import pl.kodujdlapolski.na4lapy.model.type.Gender;
import pl.kodujdlapolski.na4lapy.model.type.Size;
import pl.kodujdlapolski.na4lapy.model.type.Species;
import pl.kodujdlapolski.na4lapy.preferences.PreferencesService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class UserServiceImplTest {

    @Mock
    private PreferencesService preferencesService;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl(preferencesService);
    }

    @Test
    public void testSaveCurrentUserPreferences() throws Exception {
        // given
        UserPreferences userPreferences = new UserPreferences();

        // when
        userService.saveCurrentUserPreferences(userPreferences);

        // then
        verify(preferencesService).setUserPreferences(userPreferences);
    }

    @Test
    public void testLoadCurrentUserPreferences() throws Exception {
        // given
        UserPreferences userPreferences = new UserPreferences();
        when(preferencesService.getUserPreferences()).thenReturn(userPreferences);

        // when
        UserPreferences result = userService.loadCurrentUserPreferences();

        // then
        assertEquals(result, userPreferences);
    }

    @Test
    public void testGetPreferencesComplianceLevelShouldReturnFullCompliance() throws Exception {
        // given
        Animal animal = new Animal();
        animal.setSpecies(Species.DOG);
        animal.setGender(Gender.MALE);
        animal.setBirthDate(new DateTime(2016, 1, 1, 0, 0).getMillis());
        animal.setSize(Size.LARGE);
        animal.setActivity(ActivityAnimal.HIGH);
        UserPreferences userPreferences = new UserPreferences();
        userPreferences.setTypeDog(true);
        userPreferences.setGenderMan(true);
        userPreferences.setAgeMin(0);
        userPreferences.setAgeMax(20);
        userPreferences.setSizeLarge(true);
        userPreferences.setActivityHigh(true);
        when(preferencesService.getUserPreferences()).thenReturn(userPreferences);
        userService = new UserServiceImpl(preferencesService);

        // when
        int result = userService.getPreferencesComplianceLevel(animal);

        // then
        assertEquals(5, result);
    }

    @Test
    public void testGetPreferencesComplianceLevelShouldReturnZero() throws Exception {
        // given
        Animal animal = null;

        // when
        int result = userService.getPreferencesComplianceLevel(animal);

        // then
        assertEquals(0, result);
    }
}