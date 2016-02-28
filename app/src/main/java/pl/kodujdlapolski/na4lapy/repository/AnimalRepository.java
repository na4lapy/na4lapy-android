package pl.kodujdlapolski.na4lapy.repository;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Shelter;

public interface AnimalRepository {
    Animal findOne(Long id);
    List<Animal> findAll();
    List<Animal> findAllByShelter(Shelter shelter);
    Animal save(Animal animal);
    void delete(Animal animal);
}
