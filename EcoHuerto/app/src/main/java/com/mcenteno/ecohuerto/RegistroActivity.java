package com.mcenteno.ecohuerto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

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
                if (!cbTerms.isChecked())
                    Toast.makeText(RegistroActivity.this, "Debe leer y aceptar los términos y condiciones.", Toast.LENGTH_SHORT).show();
                else if (etName.getText().toString().matches("")
                        || etLastName.getText().toString().matches("")
                        || etEmail.getText().toString().matches("")
                        || etPassword.getText().toString().matches("")
                        || etRepeatPassword.getText().toString().matches("")
                        || etPhone.getText().toString().matches("")) {
                    Toast.makeText(RegistroActivity.this, "Debe introducir todos los campos.", Toast.LENGTH_SHORT).show();
                } else if (etEmail.getText().toString().matches("")) {
                    
                }

            }
        });
    }
}
