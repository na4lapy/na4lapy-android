/*
 *	Copyright 2017 Stowarzyszenie Na4Łapy
 *
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *	http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */
package pl.kodujdlapolski.na4lapy.service.user;

import org.joda.time.LocalDate;
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
import pl.kodujdlapolski.na4lapy.service.preferences.PreferencesService;

import static org.junit.Assert.assertEquals;
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
        UserPreferences userPreferences = new UserPreferences();
        userService.saveCurrentUserPreferences(userPreferences);
        verify(preferencesService).setUserPreferences(userPreferences);
    }

    @Test
    public void testLoadCurrentUserPreferences() throws Exception {
        UserPreferences userPreferences = new UserPreferences();
        when(preferencesService.getUserPreferences()).thenReturn(userPreferences);
        UserPreferences result = userService.loadCurrentUserPreferences();
        assertEquals(result, userPreferences);
    }

    @Test
    public void testGetPreferencesComplianceLevelShouldReturnFullCompliance() throws Exception {
        Animal animal = new Animal();
        animal.setSpecies(Species.DOG);
        animal.setGender(Gender.MALE);
        animal.setBirthDate(new LocalDate(2016, 1, 1));
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

        int result = userService.getPreferencesComplianceLevel(animal);
        assertEquals(5, result);
    }

    @Test
    public void testGetPreferencesComplianceLevelShouldReturnZero() throws Exception {
        Animal animal = null;
        int result = userService.getPreferencesComplianceLevel(animal);
        assertEquals(0, result);
    }
}