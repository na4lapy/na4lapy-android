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

public class NullApiServiceImpl implements ApiService {

    private DatabaseHelper mDatabaseHelper;

    private Random random;
    private Shelter shelter;

    private String[] photoGallery1 = new String[]{
            "http://schroniskopromyk.pl/wp-content/uploads/2016/05/Junior-1.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/05/Junior-2.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/05/Junior-3.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/05/Junior-4.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/05/Junior-5.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/05/Junior-6.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/05/Junior-7.jpg"
    };

    private String[] photoGallery2 = new String[]{
            "http://schroniskopromyk.pl/wp-content/uploads/2016/04/Erni-1.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/04/Erni-2.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/04/Erni-3.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/04/Erni-4.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/04/Erni-5.jpg",
            "http://schroniskopromyk.pl/wp-content/uploads/2016/04/Erni-6.jpg"
    };

    private String photoGalleryAuthor = "Łapą w Obiektyw";

    @Inject
    public NullApiServiceImpl(DatabaseHelper databaseHelper) {
        mDatabaseHelper = databaseHelper;
        random = new Random(System.currentTimeMillis());

        shelter = new Shelter();
        shelter.setId(1L);
        shelter.setName(random.nextBoolean() ? "Promyk" : null);
        shelter.setStreet(random.nextBoolean() ? "ul. Przyrodników" : null);
        shelter.setBuildingNumber(random.nextBoolean() ? "14" : null);
        shelter.setCity(random.nextBoolean() ? "Gdańsk - Kokoszki" : null);
        shelter.setPostalCode(random.nextBoolean() ? "80-298" : null);
        shelter.setEmail(random.nextBoolean() ? "p.swiniarski@schroniskopromyk.pl" : null);
        shelter.setPhoneNumber(random.nextBoolean() ? "58 522-37-27" : null);
        shelter.setWebsite(random.nextBoolean() ? "http://schroniskopromyk.pl/" : null);
        shelter.setAccountNumber(random.nextBoolean() ? "18124012681111001038597629" : null);
        shelter.setAdoptionRules(random.nextBoolean() ? "Przed podjęciem decyzji o adopcji pomyśl czy jesteś w stanie :\n" +
                "\n" +
                "1. dobrze karmić i poić zwierzę każdego dnia,\n" +
                "2. zapewnić psu spacery, minimum trzy razy dziennie,\n" +
                "3. Jeśli potrzebujesz psa do pilnowania Twojej posesji, zaopatrzyć go w wygodną, ciepłą budę, kojec,\n" +
                "4. profilaktycznie szczepić psa, kota, a jeśli zachoruje pójść z nim do lekarza weterynarii,\n" +
                "5. zapewnić mu opiekę na czas Twoich wyjazdów ( wakacje, ferie, wyjazdy służbowe),\n" +
                "6. poświęcić mu czas na zabawę i pielęgnację,\n" +
                "7. zabezpieczyć swojego pupila przed niepotrzebnym rozmnażaniem." : null);
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
        animal.setAdmittanceDate(random.nextBoolean() ? new LocalDate(random.nextInt(16) + 2000, random.nextInt(11) + 1, random.nextInt(28) + 1) : null);
        animal.setBirthDate(random.nextBoolean() ? new LocalDate(random.nextInt(16) + 2000, random.nextInt(11) + 1, random.nextInt(28) + 1) : null);
        animal.setName(random.nextBoolean() ? "Animal_" + random.nextInt(9999) : null);
        animal.setSpecies(random.nextBoolean() ? Species.values()[random.nextInt(Species.values().length)] : null);
        animal.setGender(random.nextBoolean() ? Gender.values()[random.nextInt(Gender.values().length)] : null);
        animal.setSize(random.nextBoolean() ? Size.values()[random.nextInt(Gender.values().length)] : null);
        animal.setRace(random.nextBoolean() ? "rasa_" + random.nextInt(99) : null);
        animal.setActivity(random.nextBoolean() ? ActivityAnimal.values()[random.nextInt(ActivityAnimal.values().length)] : null);
        animal.setTraining(random.nextBoolean() ? Training.values()[random.nextInt(Training.values().length)] : null);
        animal.setSterilization(random.nextBoolean() ? Sterilization.values()[random.nextInt(Sterilization.values().length)] : null);
        animal.setChipId(random.nextBoolean() ? "CHIP_ID_" + id : null);
        animal.setVaccination(random.nextBoolean() ? Vaccination.values()[random.nextInt(Vaccination.values().length)] : null);
        animal.setFavourite(random.nextBoolean() ? random.nextBoolean() : null);
        animal.setDescription(random.nextBoolean() ? "„Witam,\n" +
                "W schronisku jestem od 2011 roku. Trafiłem tutaj jako ok. półroczne, całkowicie dzikie szczenię złapane na ul.Orłowskiej. Pierwszą pracę ze mną podjęła  Julita i powoli robiłem postępy. Niedawno dołączyła do tej współpracy Monika i moja socjalizacja poczyniła spore postępy. Już chodzę w szelkach i wychodzę na smyczy na krótkie spacery. Tan ostatni na sesję zdjęciową był już naprawdę nie lada wyczynem. Nie sądzę, żebym tak od razu znalazł dom, ale chciałem pokazać, że taka praca u podstaw realizowana min. przez wolontariuszy potrafi przynieść niesamowite efekty, chociaż czasem jak w moim przypadku jest to rozciągnięte w czasie. 30 marca, chyba po raz pierwszy miałem okazję  przebywać w pomieszczeniach zamkniętych (sala dydaktyczna). Owszem związany był z tym spory stres, ale jeszcze pól roku temu byłoby to zupełnie nierealne. Przede mną jeszcze długa droga, ale postaram się Was informować na bieżącą o czynionych postępach i być może przyjdzie dzień, w którym pojadę do nowego domu.” Pozdrawiam, Kokos." : null);
        try {
            if (random.nextBoolean()) generateAnimalPhotoGallery(animal);
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
