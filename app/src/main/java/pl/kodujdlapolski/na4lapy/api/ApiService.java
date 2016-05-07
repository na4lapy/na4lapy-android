package pl.kodujdlapolski.na4lapy.api;

import java.util.List;

import pl.kodujdlapolski.na4lapy.api.model.PagedAnimalListDto;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import rx.Observable;

public interface ApiService {

    Observable<Shelter> getShelter();
    Observable<List<Animal>> getAnimalList();
    Observable<PagedAnimalListDto> getAnimalList(int page, int size);
    Observable<Animal> getAnimal(Long id);
}
