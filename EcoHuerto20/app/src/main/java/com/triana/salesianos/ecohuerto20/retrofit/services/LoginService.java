package com.triana.salesianos.ecohuerto20.retrofit.services;

import com.triana.salesianos.ecohuerto20.model.LoginResponse;
import com.triana.salesianos.ecohuerto20.model.Registro;
import com.triana.salesianos.ecohuerto20.model.RegistroResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/auth")
    Call<LoginResponse> doLogin(@Header("Authorization") String authorization);

    // @POST("/auth")
    // Call<LoginResponse> doLogin();


    // @POST("/users")
    // Call<LoginResponse> doRegister(@Query("access_token") String access_token,
    //                               @Body Registro registro);

    @POST("/users")
    Call<RegistroResponse> doRegister(@Body Registro registro);




}
