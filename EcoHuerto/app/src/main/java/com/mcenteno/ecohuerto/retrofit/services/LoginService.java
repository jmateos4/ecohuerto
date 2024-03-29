package com.mcenteno.ecohuerto.retrofit.services;

import com.mcenteno.ecohuerto.model.LoginResponse;
import com.mcenteno.ecohuerto.model.Registro;
import com.mcenteno.ecohuerto.model.RegistroResponse;


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
