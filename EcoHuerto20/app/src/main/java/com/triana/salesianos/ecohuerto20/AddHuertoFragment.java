package com.triana.salesianos.ecohuerto20;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class AddHuertoFragment extends DialogFragment {
    private static final String ARG_ID_USUARIO = "id_usuario";
    private static final int READ_REQUEST_CODE = 42;
    private int idNewHuerto;
    private EditText etNombre, etDireccion, etDimensiones;
    private ImageView imgCargada;
    private Button btnUpload ;


    public AddHuertoFragment() {
        // Required empty public constructor
    }

    //Metodo Busqueda Archivos(Botón)
    public void performFileSearch(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    //ActivityResult(Botón)
    public void onActivityResult( int requestCode, int resultCode,
                                  Intent resultData) {

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                Log.i("Filechooser URI", "Uri: " + uri.toString());
                Glide
                        .with(this)
                        .load(uri)
                        .into(imgCargada);
            }
        }
    }

    public static AddHuertoFragment newInstance(int idUsuario) {
        AddHuertoFragment fragment = new AddHuertoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID_USUARIO, idUsuario);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idNewHuerto = getArguments().getInt(ARG_ID_USUARIO);
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Añadir Huerto");
        builder.setMessage("")
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Metodo Guardar imagen btn_upload on click listener
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });



        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_add_huerto, null);

        etNombre = v.findViewById(R.id.editNombreH);
        etDireccion = v.findViewById(R.id.editDireccionH);
        etDimensiones = v.findViewById(R.id.editDimensionesH);
        imgCargada = v.findViewById(R.id.imagenPrev);
        btnUpload = v.findViewById(R.id.buttonUploadImg);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performFileSearch();
            }
        });

        // Llamaría a Retrofit con el idUsuario que he recibido
        // y en el método onResponse de Retrofit tendría que poner
        // todas las líneas de código que vienen a continuación

        builder.setView(v);
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
