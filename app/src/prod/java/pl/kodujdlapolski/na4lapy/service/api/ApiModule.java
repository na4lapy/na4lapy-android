/*
 *	Copyright 2017 Stowarzyszenie Na4Łapy
 *
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *	http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */
package pl.kodujdlapolski.na4lapy.service.api;


import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.kodujdlapolski.na4lapy.BuildConfig;
import pl.kodujdlapolski.na4lapy.service.system.SystemService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .header("Cache-Control", String.format("max-age=%d, only-if-cached, max-stale=%d", 3600, 0))
                    .build();
        }
    };

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
    public OkHttpClient provideOkHttpClient(SystemService systemService) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ?
                HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        File cacheDir = new File(systemService.getCacheDir(), "HttpCache");
        Cache cache = new Cache(cacheDir, cacheSize);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .cache(cache);
        client.networkInterceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR);
        return client.addInterceptor(logging)
                .build();

    }

    @Singleton
    @Provides
    @Named("api")
    public Retrofit provideRetrofit(HttpUrl baseUrl, Gson gson, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public Api provideApi(@Named("api") Retrofit retrofit) {
        return retrofit.create(Api.class);
    }
}
