package pl.kodujdlapolski.na4lapy.service.api;

import android.util.Log;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import org.joda.time.LocalDate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.service.api.model.PagedAnimalListDto;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Photo;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.model.type.ActivityAnimal;
import pl.kodujdlapolski.na4lapy.model.type.Gender;
import pl.kodujdlapolski.na4lapy.model.type.Size;
import pl.kodujdlapolski.na4lapy.model.type.Species;
import pl.kodujdlapolski.na4lapy.model.type.Sterilization;
import pl.kodujdlapolski.na4lapy.model.type.Training;
import pl.kodujdlapolski.na4lapy.model.type.Vaccination;
import pl.kodujdlapolski.na4lapy.service.repository.database.DatabaseHelper;
import rx.Observable;

public class FakeApiServiceImpl implements ApiService {

    private DatabaseHelper mDatabaseHelper;

    private Random random;
    private Shelter shelter;

    private String[] photoGallery1 = new String[]{
        "http://schroniskopromyk.pl/wp-content/uploads/2016/05/Niunia-1-150x150.jpg",
        "http://schroniskopromyk.pl/wp-content/uploads/2016/05/Niunia-2-150x150.jpg",
        "http://schroniskopromyk.pl/wp-content/uploads/2016/05/Niunia-3-150x150.jpg",
        "http://schroniskopromyk.pl/wp-content/uploads/2016/05/Niunia-4-150x150.jpg"
    };

    private String[] photoGallery2 = new String[]{
            "http://schroniskopromyk.pl/wp-content/uploads/2016/05/Chrupek-1-150x150.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/05/Chrupek-2.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/05/Junior-5.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/05/Chrupek-4-150x150.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/05/Chrupek-5-150x150.jpg"
    };

    private String photoGalleryAuthor = "Łapą w Obiektyw";

    @Inject
    public FakeApiServiceImpl(DatabaseHelper databaseHelper) {
        mDatabaseHelper = databaseHelper;
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
        shelter.setAdoptionRules("Przed podjęciem decyzji o adopcji pomyśl czy jesteś w stanie :\n" +
                "\n" +
                "1. dobrze karmić i poić zwierzę każdego dnia,\n" +
                "2. zapewnić psu spacery, minimum trzy razy dziennie,\n" +
                "3. Jeśli potrzebujesz psa do pilnowania Twojej posesji, zaopatrzyć go w wygodną, ciepłą budę, kojec,\n" +
                "4. profilaktycznie szczepić psa, kota, a jeśli zachoruje pójść z nim do lekarza weterynarii,\n" +
                "5. zapewnić mu opiekę na czas Twoich wyjazdów ( wakacje, ferie, wyjazdy służbowe),\n" +
                "6. poświęcić mu czas na zabawę i pielęgnację,\n" +
                "7. zabezpieczyć swojego pupila przed niepotrzebnym rozmnażaniem.");
    }

    @Override
    public Observable<Shelter> getShelter() {
        return Observable.just(shelter);
    }

    @Override
    public Observable<List<Animal>> getAnimalList() {
        List<Animal> animals = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            animals.add(generateAnimal((long) i));
        }
        return Observable.just(animals);
    }

    @Override
    public Observable<PagedAnimalListDto> getAnimalList(int page, int size) {
        List<Animal> animals = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            animals.add(generateAnimal((long) i));
        }
        PagedAnimalListDto dto = new PagedAnimalListDto();
        dto.setData(animals);
        dto.setTotal(animals.size());
        return Observable.just(dto);
    }

    @Override
    public Observable<Animal> getAnimal(Long id) {
        return Observable.just(generateAnimal(id));
    }

    private Animal generateAnimal(Long id) {
        Animal animal = new Animal();
        animal.setId(id);
        animal.setShelter(shelter);
        animal.setAdmittanceDate(new LocalDate(random.nextInt(16) + 2000, random.nextInt(11) + 1, random.nextInt(28) + 1));
        animal.setBirthDate(new LocalDate(random.nextInt(16) + 2000, random.nextInt(11) + 1, random.nextInt(28) + 1));
        animal.setSpecies(Species.values()[random.nextInt(Species.values().length)]);
        animal.setGender(Gender.values()[random.nextInt(Gender.values().length)]);
        animal.setName("Animal_" + random.nextInt(9999) + (random.nextBoolean() ? "_" + animal.getSpecies() + "_" + animal.getGender(): ""));
        animal.setSize(Size.values()[random.nextInt(Gender.values().length)]);
        animal.setRace("rasa_" + random.nextInt(99));
        animal.setActivity(ActivityAnimal.values()[random.nextInt(ActivityAnimal.values().length)]);
        animal.setTraining(Training.values()[random.nextInt(Training.values().length)]);
        animal.setSterilization(Sterilization.values()[random.nextInt(Sterilization.values().length)]);
        animal.setChipId("CHIP_ID_" + id);
        animal.setVaccination(Vaccination.values()[random.nextInt(Vaccination.values().length)]);
        animal.setFavourite(random.nextBoolean());
        animal.setDescription("„Witam,\n" +
                "W schronisku jestem od 2011 roku. Trafiłem tutaj jako ok. półroczne, całkowicie dzikie szczenię złapane na ul.Orłowskiej. Pierwszą pracę ze mną podjęła  Julita i powoli robiłem postępy. Niedawno dołączyła do tej współpracy Monika i moja socjalizacja poczyniła spore postępy. Już chodzę w szelkach i wychodzę na smyczy na krótkie spacery. Tan ostatni na sesję zdjęciową był już naprawdę nie lada wyczynem. Nie sądzę, żebym tak od razu znalazł dom, ale chciałem pokazać, że taka praca u podstaw realizowana min. przez wolontariuszy potrafi przynieść niesamowite efekty, chociaż czasem jak w moim przypadku jest to rozciągnięte w czasie. 30 marca, chyba po raz pierwszy miałem okazję  przebywać w pomieszczeniach zamkniętych (sala dydaktyczna). Owszem związany był z tym spory stres, ale jeszcze pól roku temu byłoby to zupełnie nierealne. Przede mną jeszcze długa droga, ale postaram się Was informować na bieżącą o czynionych postępach i być może przyjdzie dzień, w którym pojadę do nowego domu.” Pozdrawiam, Kokos.");
        try {
            generateAnimalPhotoGallery(animal);
        } catch (SQLException e) {
            Log.w("n4l", e);
        }
        return animal;
    }

    private void generateAnimalPhotoGallery(Animal animal) throws SQLException {
        ConnectionSource connectionSource = new AndroidConnectionSource(mDatabaseHelper);
        Dao<Animal, Long> dao = DaoManager.createDao(connectionSource, Animal.class);
        dao.assignEmptyForeignCollection(animal, "photos");

        String[] gallery = random.nextBoolean() ? photoGallery1 : photoGallery2;
        if (animal.getPhotos() != null) {
            for (String aPicturesSample1 : gallery) {
                Photo photo = new Photo();
                photo.setUrl(aPicturesSample1);
                photo.setAuthor(photoGalleryAuthor);
                animal.getPhotos().add(photo);
            }
        }
    }
}
