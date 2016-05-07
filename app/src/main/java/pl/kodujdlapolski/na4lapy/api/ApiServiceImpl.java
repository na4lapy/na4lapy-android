package pl.kodujdlapolski.na4lapy.api;

import java.util.List;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.api.model.PagedAnimalListDto;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import rx.Observable;

public class ApiServiceImpl implements ApiService {

    private Api mApi;

    @Inject
    public ApiServiceImpl(Api api) {
        mApi = api;
    }

    @Override
    public Observable<Shelter> getShelter() {
        return mApi.getShelter();
    }

    @Override
    public Observable<List<Animal>> getAnimalList() {
        return mApi.getAnimalList();
    }

    @Override
    public Observable<PagedAnimalListDto> getAnimalList(int page, int size) {
        return mApi.getAnimalList(page, size);
    }

    @Override
    public Observable<Animal> getAnimal(Long id) {
        return mApi.getAnimal(id);
    }
}
