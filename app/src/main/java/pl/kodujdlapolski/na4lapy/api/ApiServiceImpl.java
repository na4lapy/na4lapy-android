package pl.kodujdlapolski.na4lapy.api;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import pl.kodujdlapolski.na4lapy.api.model.PagedAnimalListDto;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import retrofit2.Call;

public class ApiServiceImpl implements ApiService {

    private Api mApi;

    @Inject
    public ApiServiceImpl(Api api) {
        mApi = api;
    }

    @Override
    public Shelter getShelter() throws IOException {
        Call<Shelter> call = mApi.getShelter();
        return call.execute().body();
    }

    @Override
    public List<Animal> getAnimalList() throws IOException {
        Call<List<Animal>> call = mApi.getAnimalList();
        return call.execute().body();
    }

    @Override
    public List<Animal> getAnimalList(int page, int size) throws IOException {
        Call<PagedAnimalListDto> call = mApi.getAnimalList(page, size);
        PagedAnimalListDto dto = call.execute().body();
        return dto.getData();
    }

    @Override
    public Animal getAnimal(Long id) throws IOException {
        Call<Animal> call = mApi.getAnimal(id);
        return call.execute().body();
    }
}
