package com.triana.salesianos.ecohuerto20.retrofit.services;

import com.triana.salesianos.ecohuerto20.model.Espacio;
import com.triana.salesianos.ecohuerto20.model.HuertosResponse;
import com.triana.salesianos.ecohuerto20.model.PlantacionResponse;
import com.triana.salesianos.ecohuerto20.model.ResponseContainer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HuertoService {

    @GET("huertos")
    Call<ResponseContainer<HuertosResponse>> listHuerto();

    @GET("huertos/{id}")
    Call<HuertosResponse> oneHuerto(@Path("id") String id);

    @DELETE("huertos/{id}")
    Call borrarHuerto(@Path("id") String id);

    @GET("plantaciones/huerto/{id_huerto}")
    Call <List<PlantacionResponse>> listPlantacion(@Path("id_huerto") String id_huerto);
    
}