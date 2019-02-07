package com.mcenteno.ecohuerto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    TextView btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnLogin = findViewById(R.id.buttonLogin);
        btnRegistro = findViewById(R.id.text_signup);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogeo = new Intent(MainActivity.this, HuertoActivity.class);
                startActivity(intentLogeo);
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegistro = new Intent(MainActivity.this, RegistroActivity.class);
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
