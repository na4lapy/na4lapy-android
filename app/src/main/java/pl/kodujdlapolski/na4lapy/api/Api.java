package pl.kodujdlapolski.na4lapy.api;

import java.util.List;

import pl.kodujdlapolski.na4lapy.api.model.PagedAnimalListDto;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("shelters/1")
    Call<Shelter> getShelter();

    @GET("animals")
    Call<List<Animal>> getAnimalList();

    @GET("animals")
    Call<PagedAnimalListDto> getAnimalList(@Query("page") int page, @Query("size") int size);

    @GET("animals/{id}")
    Call<Animal> getAnimal(@Path("id") Long id);
}
