package pl.kodujdlapolski.na4lapy.payments;

import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import pl.kodujdlapolski.na4lapy.payments.api.PayLaneApi;
import pl.kodujdlapolski.na4lapy.payments.api.PayLaneApiRequestInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class PaymentsModule {

    @Provides
    @Singleton
    public PaymentsService providePaymentService(PayLaneApi payLaneApi) {
        return new PaymentsServiceImpl(payLaneApi);
    }

    @Singleton
    @Provides
    public PayLaneApi providePayLaneApi(@Named("paylane") Retrofit retrofit) {
        return retrofit.create(PayLaneApi.class);
    }

    @Singleton
    @Provides
    @Named("paylane")
    public Retrofit provideRetrofit(Gson gson) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new PayLaneApiRequestInterceptor()).build();
        return new Retrofit.Builder()
                .baseUrl(HttpUrl.parse("https://direct.paylane.com/rest/"))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
