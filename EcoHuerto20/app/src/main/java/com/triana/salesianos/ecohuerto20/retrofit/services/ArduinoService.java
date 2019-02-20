package com.triana.salesianos.ecohuerto20.retrofit.services;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ArduinoService {
    @GET("/13/0")
    Call abrir();

    @GET("/13/1")
    Call cerrar();
}
