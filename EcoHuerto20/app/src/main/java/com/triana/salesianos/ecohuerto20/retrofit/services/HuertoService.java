package com.triana.salesianos.ecohuerto20.retrofit.services;

import com.triana.salesianos.ecohuerto20.model.Huerto;
import com.triana.salesianos.ecohuerto20.model.HuertosResponse;
import com.triana.salesianos.ecohuerto20.model.ResponseContainer;

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
}
