package com.mcenteno.ecohuerto.retrofit.services;

<<<<<<< HEAD

import com.mcenteno.ecohuerto.model.Registro;
import com.mcenteno.ecohuerto.model.ResponseContainer;
=======
>>>>>>> 3000616b5e46614155f0174f02e1c85e6074037b
import com.mcenteno.ecohuerto.model.Registro;
import com.mcenteno.ecohuerto.model.RegistroResponse;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/auth")
    Call<RegistroResponse> doLogin(@Header("Authorization") String authorization);

    // @POST("/auth")
    // Call<LoginResponse> doLogin();


    // @POST("/users")
    // Call<LoginResponse> doRegister(@Query("access_token") String access_token,
    //                               @Body Registro registro);

    @POST("/users")
    Call<RegistroResponse> doRegister(@Body Registro registro);




}
