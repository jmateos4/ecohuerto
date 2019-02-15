package com.triana.salesianos.ecohuerto20.retrofit.services;

import com.triana.salesianos.ecohuerto20.model.HuertoDTO;
import com.triana.salesianos.ecohuerto20.model.HuertosResponse;
import com.triana.salesianos.ecohuerto20.model.LoginResponse;
import com.triana.salesianos.ecohuerto20.model.PlantacionResponse;
import com.triana.salesianos.ecohuerto20.model.ResponseContainer;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @POST("huertos")
    Call<HuertosResponse> addHuerto(@Body HuertoDTO huerto);

    @GET("plantaciones/{id}")
    Call<PlantacionResponse> onePlantacion(@Path("id") String id);


    @Multipart
    @POST("/huertos")
    Call<HuertosResponse> registerImg(@Part MultipartBody.Part avatar,
                                      @Part("nombre") RequestBody nombre,
                                      @Part("direcion") RequestBody direccion,
                                      @Part("foto")RequestBody foto,
                                      @Part("espacio") RequestBody espacio,
                                      @Part("user") RequestBody user);


}