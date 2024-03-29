package com.triana.salesianos.ecohuerto20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.triana.salesianos.ecohuerto20.model.LoginResponse;
import com.triana.salesianos.ecohuerto20.model.Registro;
import com.triana.salesianos.ecohuerto20.retrofit.generator.ServiceGenerator;
import com.triana.salesianos.ecohuerto20.retrofit.services.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {

    Button btnRegister;
    EditText etName, etLastName, etEmail, etPassword, etRepeatPassword, etPhone;
    CheckBox cbTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etName = findViewById(R.id.etName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etRepeatPassword = findViewById(R.id.etRepeatPassword);
        etPhone = findViewById(R.id.etPhone);
        cbTerms = findViewById(R.id.cbTerms);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().toString().matches("")
                        || etLastName.getText().toString().matches("")
                        || etEmail.getText().toString().matches("")
                        || etPassword.getText().toString().matches("")
                        || etRepeatPassword.getText().toString().matches("")
                        || etPhone.getText().toString().matches("")) {
                    Toast.makeText(RegistroActivity.this, "Debe introducir todos los campos.", Toast.LENGTH_SHORT).show();
                } else if(!cbTerms.isChecked()) {
                    Toast.makeText(RegistroActivity.this, "Debe leer y aceptar los términos y condiciones.", Toast.LENGTH_SHORT).show();
                } else if (!etEmail.getText().toString().matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$")) {
                    Toast.makeText(RegistroActivity.this, "Debe introducir un correo válido.", Toast.LENGTH_SHORT).show();
                } else if (!etPassword.getText().toString().equals(etRepeatPassword.getText().toString())) {
                    Toast.makeText(RegistroActivity.this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
                } else {

                    // Recoger los datos del formulario
                    String firstName = etName.getText().toString().trim();
                    String lastName = etLastName.getText().toString().trim();
                    String email = etEmail.getText().toString().trim();
                    String pass = etPassword.getText().toString().trim();
                    String phone = etPhone.getText().toString().trim();

                    Registro registro = new Registro(firstName + " " + lastName, email, pass);

                    LoginService service = ServiceGenerator.createService(LoginService.class);

                    Call<LoginResponse> loginReponseCall = service.doRegister(registro);

                    loginReponseCall.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.code() == 201) {
                                //ServiceGenerator.jwtToken = response.body().getToken();
                                UtilToken.setToken(RegistroActivity.this, response.body().getToken());
                                UtilToken.setIdUser(RegistroActivity.this, response.body().getUser().getId());

                                startActivity(new Intent(RegistroActivity.this, HuertoActivity.class));                        // Toast.makeText(RegistroActivity.this, "Usuario registrado y logeado con éxito", Toast.LENGTH_LONG).show();
                                finish();

                            } else {
                                // error
                                Toast.makeText(RegistroActivity.this, "Error en el registro. Revise los datos introducidos", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Log.e("NetworkFailure", t.getMessage());
                            Toast.makeText(RegistroActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();

                        }
                    });






                }

            }
        });
    }
}
