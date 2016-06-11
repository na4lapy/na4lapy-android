package pl.kodujdlapolski.na4lapy.payments.api;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import pl.kodujdlapolski.na4lapy.BuildConfig;

public class PayLaneApiRequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder()
                .addHeader("Authorization", getBasicAuthHeader(BuildConfig.PAYLANE_LOGIN, BuildConfig.PAYLANE_PASSWORD))
                .build());
    }

    private String getBasicAuthHeader(String login, String pasword) {
        String userAndPassword = login + ":" + pasword;
        return "Basic " + Base64.encodeToString(userAndPassword.getBytes(), Base64.NO_WRAP);
    }
}
