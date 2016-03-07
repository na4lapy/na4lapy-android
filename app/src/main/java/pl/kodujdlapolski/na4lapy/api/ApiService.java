package pl.kodujdlapolski.na4lapy.api;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Shelter;

public interface ApiService {
    List<Animal> getAllAnimals();
    List<Shelter> getAllShelters();
}
