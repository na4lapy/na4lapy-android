package pl.kodujdlapolski.na4lapy.repository;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Shelter;

public interface ShelterRepository {
    Shelter findOne(Long id);
    List<Shelter> findAll();
    Shelter save(Shelter shelter);
    void delete(Shelter shelter);
}
