package com.mcenteno.ecohuerto.retrofit.services;

import com.mcenteno.ecohuerto.model.Huerto;
import com.mcenteno.ecohuerto.model.HuertosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HuertoService {

    @GET("huertos")
    Call<HuertosResponse> listHuerto(@Query("access_token") String access_token);

    @GET("huertos/{id}")
    Call<Huerto> oneHuerto(@Path("id") String id, @Query("access_token") String access_token);
}
