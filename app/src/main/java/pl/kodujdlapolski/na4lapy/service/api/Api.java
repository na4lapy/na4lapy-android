package pl.kodujdlapolski.na4lapy.service.api;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Shelter;
import pl.kodujdlapolski.na4lapy.service.api.model.PagedAnimalListDto;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface Api {

    @GET("shelters/1")
    Observable<Shelter> getShelter();

    @GET("animals")
    Observable<List<Animal>> getAnimalList();

    @GET("animals")
    Observable<PagedAnimalListDto> getAnimalList(@Query("page") int page, @Query("size") int size);

    @GET("animals/{id}")
    Observable<Animal> getAnimal(@Path("id") Long id);
}
