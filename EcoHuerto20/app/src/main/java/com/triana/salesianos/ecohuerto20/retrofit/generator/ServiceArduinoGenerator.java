package com.triana.salesianos.ecohuerto20.retrofit.generator;


import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceArduinoGenerator {

    private static final String BASE_URL = "https://172.27.0.30/arduino/digital/";

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = null;

    private static OkHttpClient.Builder httpClientBuilder =
            new OkHttpClient.Builder();


    public static <S> S createService(Class<S> serviceClass) {

        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalUrl = original.url();

                HttpUrl url = originalUrl.newBuilder()
                        .build();

                Request request = original.newBuilder()
                        .url(url)
                        .build();


                return chain.proceed(request);
            }
        });
        builder.client(httpClientBuilder.build());
        retrofit=builder.build();
        return retrofit.create(serviceClass);
    }
}