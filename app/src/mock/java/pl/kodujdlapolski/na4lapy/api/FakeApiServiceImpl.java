package pl.kodujdlapolski.na4lapy.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.model.type.ActivityAnimal;
import pl.kodujdlapolski.na4lapy.model.type.Attitude;
import pl.kodujdlapolski.na4lapy.model.type.Gender;
import pl.kodujdlapolski.na4lapy.model.type.Size;
import pl.kodujdlapolski.na4lapy.model.type.Species;
import pl.kodujdlapolski.na4lapy.model.type.Training;

public class FakeApiServiceImpl implements ApiService {

    private Random random;
    private Shelter shelter;

    public FakeApiServiceImpl() {
        random = new Random(System.currentTimeMillis());

        shelter = new Shelter();
        shelter.setId(1L);
        shelter.setName("Promyk");
        shelter.setStreet("ul. Przyrodników");
        shelter.setBuildingNumber("14");
        shelter.setCity("Gdańsk - Kokoszki");
        shelter.setPostalCode("80-298");
        shelter.setEmail("p.swiniarski@schroniskopromyk.pl");
        shelter.setPhoneNumber("58 522-37-27");
        shelter.setWebsite("http://schroniskopromyk.pl/");
        shelter.setAccountNumber("18124012681111001038597629");
    }

    @Override
    public Shelter getShelter() throws IOException {
        return shelter;
    }

    @Override
    public List<Animal> getAnimalList() throws IOException {
        List<Animal> animals = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            animals.add(generateAnimal((long) i));
        }
        return animals;
    }

    @Override
    public List<Animal> getAnimalList(int page, int size) throws IOException {
        List<Animal> animals = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            animals.add(generateAnimal((long) i));
        }
        return animals;
    }

    @Override
    public Animal getAnimal(Long id) throws IOException {
        return generateAnimal(id);
    }

    private Animal generateAnimal(Long id) {
        Animal animal = new Animal();
        animal.setId(id);
        animal.setShelter(shelter);
        animal.setAdmittanceDate(1457214300000L - (long) (random.nextFloat() * 63072000000L));
        animal.setName("Animal_" + random.nextInt(9999));
        animal.setBirthDate(random.nextLong());
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
        return animal;
    }
}
