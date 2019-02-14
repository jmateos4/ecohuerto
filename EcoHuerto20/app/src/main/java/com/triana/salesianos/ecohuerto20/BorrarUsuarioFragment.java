package com.triana.salesianos.ecohuerto20;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.triana.salesianos.ecohuerto20.model.HuertosResponse;


public class BorrarUsuarioFragment extends DialogFragment {
    private static final String ARG_ID_USUARIO = "id_usuario";

    // TODO: Rename and change types of parameters
    private int idUsuarioBorrar;
    private TextView tvNombre;
    private HuertosResponse huerto;

    public BorrarUsuarioFragment() {
        // Required empty public constructor
    }

    public static BorrarUsuarioFragment newInstance(int idUsuario) {
        BorrarUsuarioFragment fragment = new BorrarUsuarioFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID_USUARIO, idUsuario);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idUsuarioBorrar = getArguments().getInt(ARG_ID_USUARIO);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Borrar usuario");
        builder.setMessage("Borrar los datos del huerto")
                .setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        // Las siguientes 2 líneas de código cargan en el diálogo
        // la vista personalizada
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_borrar_usuario, null);

        tvNombre = v.findViewById(R.id.tvNombreHuerto);

        // Llamaría a Retrofit con el idUsuario que he recibido
        // y en el método onResponse de Retrofit tendría que poner
        // todas las líneas de código que vienen a continuación

        builder.setView(v);

        // Create the AlertDialog object and return it
        return builder.create();
    }


}
