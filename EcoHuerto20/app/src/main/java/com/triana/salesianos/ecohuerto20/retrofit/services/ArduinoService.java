package com.triana.salesianos.ecohuerto20.retrofit.services;

<<<<<<< HEAD
import android.support.v7.widget.CardView;

import okhttp3.ResponseBody;
=======
>>>>>>> 0b7999a9d08489f09f014df32046cfbad3376874
import retrofit2.Call;
import retrofit2.http.GET;

public interface ArduinoService {
    @GET("/13/0")
    Call<ResponseBody> abrir();

    @GET("/13/1")
    Call<ResponseBody> cerrar();
}
