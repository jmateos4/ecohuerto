package com.triana.salesianos.ecohuerto20.retrofit.services;

import com.triana.salesianos.ecohuerto20.model.HuertosResponse;
import com.triana.salesianos.ecohuerto20.model.PlantacionDTO;
import com.triana.salesianos.ecohuerto20.model.PlantacionResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PlantacionService {

    @GET("plantaciones/huerto/{id_huerto}")
    Call <List<PlantacionResponse>> listPlantacion(@Path("id_huerto") String id_huerto);

    @DELETE("plantaciones/{id}")
    Call<ResponseBody> borrarPlantacion(@Path("id") String id);

    @GET("plantaciones/{id}")
    Call<PlantacionResponse> onePlantacion(@Path("id") String id);

    @PUT("plantaciones/{id}")
    Call<PlantacionResponse> riegoAut(@Path("id") String id,
                                      @Body PlantacionResponse plantacionResponse);

    @POST("plantaciones")
    Call<HuertosResponse> addPlantacion(@Body PlantacionDTO plantacion);

}
