package pl.kodujdlapolski.na4lapy.api;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

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
    public List<Animal> getAllAnimals() {
        //TODO
        return null;
    }

    @Override
    public List<Shelter> getAllShelters() {
        Call<List<Shelter>> sheltersCall = mApi.listShelters();
        List<Shelter> shelters = null;
        try {
            shelters = sheltersCall.execute().body();
        } catch (IOException e) {
            Log.w(getClass().getSimpleName(), e);
        }
        return shelters;
    }
}
