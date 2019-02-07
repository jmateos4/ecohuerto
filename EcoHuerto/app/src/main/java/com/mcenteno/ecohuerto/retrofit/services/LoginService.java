package com.mcenteno.ecohuerto.retrofit.services;

<<<<<<< HEAD

import com.mcenteno.ecohuerto.model.Registro;
import com.mcenteno.ecohuerto.model.ResponseContainer;
=======
import com.mcenteno.ecohuerto.model.Registro;
import com.mcenteno.ecohuerto.model.RegistroResponse;
>>>>>>> 646b4effe7ec6ba2474a8000480f7363b8ff066f

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
