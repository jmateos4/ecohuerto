package com.triana.salesianos.ecohuerto20;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.triana.salesianos.ecohuerto20.model.HuertosResponse;
import com.triana.salesianos.ecohuerto20.model.PlantacionDTO;
import com.triana.salesianos.ecohuerto20.model.PlantacionResponse;
import com.triana.salesianos.ecohuerto20.retrofit.generator.ServiceGenerator;
import com.triana.salesianos.ecohuerto20.retrofit.generator.TipoAutenticacion;
import com.triana.salesianos.ecohuerto20.retrofit.services.PlantacionService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPlantacionFragment extends DialogFragment {

    public static final String ARG_ITEM_ID = "idHuerto";
    private PlantacionDTO plantacion;

    private String idHuertoEditar;
    private EditText etNombre, etTipo;
    Context ctx;


    public AddPlantacionFragment() {
        // Required empty public constructor
    }

    public static AddPlantacionFragment newInstance(String idHuerto) {
        AddPlantacionFragment fragment = new AddPlantacionFragment();
        Bundle args = new Bundle();
        //args.putInt(ARG_ITEM_ID, idHuerto);
        args.putString(ARG_ITEM_ID, idHuerto);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idHuertoEditar = getArguments().getString(ARG_ITEM_ID);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_add_plantacion, null);

        etNombre = v.findViewById(R.id.editTextNombre);
        etTipo = v.findViewById(R.id.editTextTipo);
        //plantacion = new PlantacionDTO(ARG_ITEM_ID, etNombre.getText().toString(), etTipo.getText().toString());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Crear Plantación");
        builder.setMessage("Rellene los datos del usuario")
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    final PlantacionService service = ServiceGenerator.createService(PlantacionService.class,
                            UtilToken.getToken(ctx), TipoAutenticacion.JWT);

                    public void onClick(DialogInterface dialog, int id) {

                        PlantacionDTO planta = new PlantacionDTO(idHuertoEditar, etNombre.getText().toString(), etTipo.getText().toString());

                        Call<HuertosResponse> calla = service.addPlantacion(planta);
                        calla.enqueue(new Callback<HuertosResponse>() {
                            @Override
                            public void onResponse(Call<HuertosResponse> calla, Response<HuertosResponse> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(ctx, "Añadido Correctamente", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(ctx, "Response False", Toast.LENGTH_LONG).show();
                                }


                            }

                            @Override
                            public void onFailure(Call<HuertosResponse> calla, Throwable t) {
                                // Toast
                                Log.i("onFailure", "error en retrofit");
                            }
                        });
                    }

                });
        builder.setView(v);

        return builder.create();
    }


    @Override
    public void onAttach(Context context) {
        ctx = context;
        super.onAttach(context);
        this.ctx = context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
