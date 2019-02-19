package com.triana.salesianos.ecohuerto20.retrofit.generator;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceArduinoGenerator {

    private static final String BASE_URL = "https://172.27.0.30/arduino/digital/";

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = null;

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}