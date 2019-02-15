package com.triana.salesianos.ecohuerto20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.triana.salesianos.ecohuerto20.model.LoginResponse;
import com.triana.salesianos.ecohuerto20.retrofit.generator.ServiceGenerator;
import com.triana.salesianos.ecohuerto20.retrofit.services.LoginService;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    TextView btnRegistro;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.buttonLogin);
        btnRegistro = findViewById(R.id.text_signup);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username_txt = email.getText().toString();
                String password_txt = password.getText().toString();

                String credentials = Credentials.basic(username_txt, password_txt);

                LoginService service = ServiceGenerator.createService(LoginService.class);
                Call<LoginResponse> call = service.doLogin(credentials);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.code() != 201) {
                            // error
                            Log.e("RequestError", response.message());
                            Toast.makeText(LoginActivity.this, "Error de petición", Toast.LENGTH_SHORT).show();
                        } else {
                            // exito
                            // Toast.makeText(MainActivity.this, response.body().getToken(), Toast.LENGTH_LONG).show();
                            /*
                                Pasos:
                                    1) Almacenar el token donde corresponda.
                                    2) Lanzar el siguiente Activity.
                             */
                            // ServiceGenerator.jwtToken = response.body().getToken();
                            /*SharedPreferences sharedPreferences =
                                    getSharedPreferences(getString(R.string.sharedpreferences_filename),
                                            Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(getString(R.string.jwt_key), response.body().getToken());
                            editor.commit();*/
                            UtilToken.setToken(LoginActivity.this, response.body().getToken());
                            UtilToken.setIdUser(LoginActivity.this, response.body().getUser().getId());

                            //startActivity(new Intent(LoginActivity.this, HuertoActivity.class));
                            startActivity(new Intent(LoginActivity.this, HuertoActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e("NetworkFailure", t.getMessage());
                        Toast.makeText(LoginActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegistro = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intentRegistro);
            }
        });





        /*

        btnLogin = findViewById(R.id.buttonLogin);
        btnSignUp = findViewById(R.id.text_signup);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignUp = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intentSignUp);
            }
        });

        /*btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogeo = new Intent(MainActivity.this, InicioActivity.class);
                startActivity(intentLogeo);
            }
        });

        */
    }
}
