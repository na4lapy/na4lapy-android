package pl.kodujdlapolski.na4lapy.api;

import java.io.IOException;
import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Shelter;

public interface ApiService {

    Shelter getShelter() throws IOException;
    List<Animal> getAnimalList() throws IOException;
    List<Animal> getAnimalList(int page, int size) throws IOException;
    Animal getAnimal(Long id) throws IOException;
}
