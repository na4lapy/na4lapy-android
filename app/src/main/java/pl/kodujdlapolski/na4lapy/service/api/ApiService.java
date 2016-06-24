package pl.kodujdlapolski.na4lapy.service.api;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.service.api.model.PagedAnimalListDto;
import rx.Observable;

public interface ApiService {

    Observable<Shelter> getShelter();
    Observable<List<Animal>> getAnimalList();
    Observable<PagedAnimalListDto> getAnimalList(int page, int size);
    Observable<Animal> getAnimal(Long id);
}
