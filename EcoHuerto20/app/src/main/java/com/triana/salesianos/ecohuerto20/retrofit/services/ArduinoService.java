package com.triana.salesianos.ecohuerto20.retrofit.services;

import android.support.v7.widget.CardView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ArduinoService {
    @GET("/13/0")
    Call<ResponseBody> abrir();

    @GET("/13/1")
    Call<ResponseBody> cerrar();
}
