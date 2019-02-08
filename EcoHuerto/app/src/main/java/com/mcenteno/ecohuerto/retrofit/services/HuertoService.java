package com.mcenteno.ecohuerto.retrofit.services;

import com.mcenteno.ecohuerto.model.Huerto;
import com.mcenteno.ecohuerto.model.HuertosResponse;
import com.mcenteno.ecohuerto.model.ResponseContainer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HuertoService {

    @GET("huertos")
    Call<ResponseContainer> listHuerto();

    @GET("huertos/{id}")
    Call<Huerto> oneHuerto(@Path("id") String id);
}
