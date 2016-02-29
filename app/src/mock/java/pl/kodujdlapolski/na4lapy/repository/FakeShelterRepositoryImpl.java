package pl.kodujdlapolski.na4lapy.repository;

import com.google.common.collect.Lists;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Shelter;

public class FakeShelterRepositoryImpl implements ShelterRepository {

    private Shelter shelter;

    public FakeShelterRepositoryImpl() {
        shelter = new Shelter();
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
    public Shelter findOne(Long id) {
        return shelter;
    }

    @Override
    public List<Shelter> findAll() {
        return Lists.newArrayList(shelter);
    }

    @Override
    public Shelter save(Shelter shelter) {
        shelter.setId(999L);
        return shelter;
    }

    @Override
    public void delete(Shelter shelter) {
    }
}
