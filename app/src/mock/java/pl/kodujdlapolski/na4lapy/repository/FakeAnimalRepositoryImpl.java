package pl.kodujdlapolski.na4lapy.repository;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Photo;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.model.type.ActivityAnimal;
import pl.kodujdlapolski.na4lapy.model.type.Attitude;
import pl.kodujdlapolski.na4lapy.model.type.Gender;
import pl.kodujdlapolski.na4lapy.model.type.Size;
import pl.kodujdlapolski.na4lapy.model.type.Species;
import pl.kodujdlapolski.na4lapy.model.type.Training;

public class FakeAnimalRepositoryImpl implements AnimalRepository {

    private Random random;
    private Shelter shelter;

    public FakeAnimalRepositoryImpl() {
        random = new Random(System.currentTimeMillis());
        shelter = new Shelter();
        shelter.setId(1L);
    }

    @Override
    public Animal findOne(Long id) {
        return generateAnimal();
    }

    @Override
    public List<Animal> findAll() {
        List<Animal> animals = new ArrayList<>();
        for (int i=0; i<20; i++) {
            animals.add(generateAnimal());
        }
        return animals;
    }

    @Override
    public List<Animal> findAllByShelter(Shelter shelter) {
        List<Animal> animals = new ArrayList<>();
        for (int i=0; i<20; i++) {
            animals.add(generateAnimal());
        }
        return animals;
    }

    @Override
    public Animal save(Animal animal) {
        animal.setId(999L);
        return animal;
    }

    @Override
    public void delete(Animal animal) {
    }

    private Animal generateAnimal() {
        Animal animal = new Animal();
        animal.setShelter(shelter);
        animal.setHomelessnessnessDuration(random.nextInt(10));
        animal.setName("Animal_" + random.nextInt(9999));
        animal.setAge(random.nextInt(21));
        animal.setSpecies(Species.values()[random.nextInt(Species.values().length)]);
        animal.setGender(Gender.values()[random.nextInt(Gender.values().length)]);
        animal.setSize(Size.values()[random.nextInt(Gender.values().length)]);
        animal.setRace("rasa_" + random.nextInt(99));
        animal.setActivity(ActivityAnimal.values()[random.nextInt(ActivityAnimal.values().length)]);
        animal.setTraining(Training.values()[random.nextInt(Training.values().length)]);
        animal.setSterilization(random.nextBoolean());
        animal.setChip(random.nextBoolean());
        animal.setVaccination(random.nextBoolean());
        animal.setAttitudeTowardsPeople(Attitude.values()[random.nextInt(Attitude.values().length)]);
        animal.setAttitudeTowardsChildren(Attitude.values()[random.nextInt(Attitude.values().length)]);
        animal.setAttitudeTowardsDogs(Attitude.values()[random.nextInt(Attitude.values().length)]);
        animal.setAttitudeTowardsCats(Attitude.values()[random.nextInt(Attitude.values().length)]);
        animal.setFavourite(random.nextBoolean());
        Photo photo = new Photo();
        photo.setUrl("http://schroniskopromyk.pl/wp-content/uploads/2015/05/Mero-str.jpg");
        animal.setPhotos(Lists.newArrayList(photo));
        return animal;
    }
}
