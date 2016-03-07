package pl.kodujdlapolski.na4lapy.api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import pl.kodujdlapolski.na4lapy.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Singleton
    @Provides
    public ApiService provideApiService(Api api) {
        return new ApiServiceImpl(api);
    }

    @Singleton
    @Provides
    public HttpUrl provideBaseUrl() {
        return HttpUrl.parse(BuildConfig.BASE_URL);
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(HttpUrl baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }
}
