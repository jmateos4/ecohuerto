package com.mcenteno.plantillas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class PluviometroActivity extends AppCompatActivity {

    GifImageView gif1, gif2;
    TextView recogido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pluviometro);

        gif1= findViewById(R.id.gif1);
        gif2= findViewById(R.id.gif2);
        recogido= findViewById(R.id.recogido);
        String numero = recogido.getText().toString();
        int recogidoParseado = Integer.parseInt(numero);

        if (recogidoParseado==0){
            gif1.setVisibility(View.INVISIBLE);
            gif2.setVisibility(View.VISIBLE);
        } if(recogidoParseado>0){
            gif1.setVisibility(View.VISIBLE);
            gif2.setVisibility(View.INVISIBLE);
        }

    }

}
