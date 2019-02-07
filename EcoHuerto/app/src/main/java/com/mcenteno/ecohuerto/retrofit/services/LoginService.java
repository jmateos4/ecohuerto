package com.mcenteno.ecohuerto.retrofit.services;

import com.mcenteno.plantillas.model.Registro;
import com.mcenteno.plantillas.model.ResponseContainer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/auth")
    Call<ResponseContainer> doLogin(@Header("Authorization") String authorization);

    // @POST("/auth")
    // Call<LoginResponse> doLogin();


    // @POST("/users")
    // Call<LoginResponse> doRegister(@Query("access_token") String access_token,
    //                               @Body Registro registro);

    @POST("/users")
    Call<ResponseContainer> doRegister(@Body Registro registro);




}
