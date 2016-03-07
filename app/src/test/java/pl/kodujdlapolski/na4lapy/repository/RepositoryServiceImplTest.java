package pl.kodujdlapolski.na4lapy.repository;

import android.util.Log;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.repository.database.DatabaseRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class RepositoryServiceImplTest {

    @Mock
    private DatabaseRepository databaseRepository;

    @Mock
    private RepositoryService.GetAnimalCallback getAnimalCallback;

    @Mock
    private RepositoryService.LoadAnimalsCallback loadAnimalsCallback;

    @Mock
    private RepositoryService.GetShelterCallback getShelterCallback;

    @Mock
    private RepositoryService.LoadSheltersCallback loadSheltersCallback;

    private RepositoryServiceImpl repositoryService;

    private Long animalId = 60L, shelterId = 61L;
    private Animal animal;
    private Shelter shelter;

    @Before
    public void setUp() throws Exception {
        repositoryService = new RepositoryServiceImpl(databaseRepository);
        mockStatic(Log.class);

        animal = new Animal();
        animal.setId(animalId);

        shelter = new Shelter();
        shelter.setId(shelterId);
    }

    @Test
    public void testGetAnimal() throws Exception {
        // given
        when(databaseRepository.findOneById(animalId, Animal.class)).thenReturn(animal);

        // when
        repositoryService.getAnimal(animalId, getAnimalCallback);

        // then
        verify(databaseRepository).findOneById(animalId, Animal.class);
        verify(getAnimalCallback).onAnimalLoaded(animal);
    }

    @Test
    public void testGetAnimalDatabaseRepositoryThrowsSQLException() throws Exception {
        // given
        when(databaseRepository.findOneById(animalId, Animal.class)).thenThrow(new SQLException());

        // when
        repositoryService.getAnimal(animalId, getAnimalCallback);

        // then
        verify(databaseRepository).findOneById(animalId, Animal.class);
        verify(getAnimalCallback).onAnimalLoaded(null);
    }

    @Test
    public void testGetAnimalsByShelterId() throws Exception {
        // given
        List animals = Lists.newArrayList(animal);
        when(databaseRepository.findAllByForeignId(shelterId, Animal.class, Shelter.class)).thenReturn(animals);

        // when
        repositoryService.getAnimalsByShelterId(shelterId, loadAnimalsCallback);

        // then
        verify(databaseRepository).findAllByForeignId(shelterId, Animal.class, Shelter.class);
        verify(loadAnimalsCallback).onAnimalsLoaded(animals);
    }

    @Test
    public void testGetAnimalsByShelterIdDatabaseRepositoryThrowsSQLException() throws Exception {
        // given
        when(databaseRepository.findAllByForeignId(shelterId, Animal.class, Shelter.class)).thenThrow(new SQLException());

        // when
        repositoryService.getAnimalsByShelterId(shelterId, loadAnimalsCallback);

        // then
        verify(databaseRepository).findAllByForeignId(shelterId, Animal.class, Shelter.class);
        verify(loadAnimalsCallback).onAnimalsLoaded(null);
    }

    @Test
    public void testGetAnimalsByFavourite() throws Exception {
        // given
        Map<String, Object> favouriteField = Maps.newHashMap();
        favouriteField.put(Animal.COLUMN_NAME_FAVOURITE, true);
        List animals = Lists.newArrayList(animal);
        when(databaseRepository.findAllByFields(favouriteField, Animal.class)).thenReturn(animals);

        // when
        repositoryService.getAnimalsByFavourite(loadAnimalsCallback);

        // then
        verify(databaseRepository).findAllByFields(favouriteField, Animal.class);
        verify(loadAnimalsCallback).onAnimalsLoaded(animals);
    }

    @Test
    public void testGetAnimalsByFavouriteDatabaseRepositoryThrowsSQLException() throws Exception {
        // given
        Map<String, Object> favouriteField = Maps.newHashMap();
        favouriteField.put(Animal.COLUMN_NAME_FAVOURITE, true);
        when(databaseRepository.findAllByFields(favouriteField, Animal.class)).thenThrow(new SQLException());

        // when
        repositoryService.getAnimalsByFavourite(loadAnimalsCallback);

        // then
        verify(databaseRepository).findAllByFields(favouriteField, Animal.class);
        verify(loadAnimalsCallback).onAnimalsLoaded(null);
    }

    @Test
    public void testGetShelter() throws Exception {
        // given
        when(databaseRepository.findOneById(shelterId, Shelter.class)).thenReturn(shelter);

        // when
        repositoryService.getShelter(shelterId, getShelterCallback);

        // then
        verify(databaseRepository).findOneById(shelterId, Shelter.class);
        verify(getShelterCallback).onShelterLoaded(shelter);
    }

    @Test
    public void testGetShelterDatabaseRepositoryThrowsSQLException() throws Exception {
        // given
        when(databaseRepository.findOneById(shelterId, Shelter.class)).thenThrow(new SQLException());

        // when
        repositoryService.getShelter(shelterId, getShelterCallback);

        // then
        verify(databaseRepository).findOneById(shelterId, Shelter.class);
        verify(getShelterCallback).onShelterLoaded(null);
    }

    @Test
    public void testGetShelters() throws Exception {
        // given
        List shelters = Lists.newArrayList(shelter);
        when(databaseRepository.findAll(Shelter.class)).thenReturn(shelters);

        // when
        repositoryService.getShelters(loadSheltersCallback);

        // then
        verify(databaseRepository).findAll(Shelter.class);
        verify(loadSheltersCallback).onSheltersLoaded(shelters);
    }

    @Test
    public void testGetSheltersDatabaseRepositoryThrowsSQLException() throws Exception {
        // given
        when(databaseRepository.findAll(Shelter.class)).thenThrow(new SQLException());

        // when
        repositoryService.getShelters(loadSheltersCallback);

        // then
        verify(databaseRepository).findAll(Shelter.class);
        verify(loadSheltersCallback).onSheltersLoaded(null);
    }
}