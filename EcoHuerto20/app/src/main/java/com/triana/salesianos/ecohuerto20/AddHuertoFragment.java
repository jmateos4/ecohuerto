package com.triana.salesianos.ecohuerto20;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.triana.salesianos.ecohuerto20.interfaces.HuertoInteractionListener;
import com.triana.salesianos.ecohuerto20.model.Espacio;
import com.triana.salesianos.ecohuerto20.model.HuertoDTO;
import com.triana.salesianos.ecohuerto20.model.HuertosResponse;
import com.triana.salesianos.ecohuerto20.model.LoginResponse;
import com.triana.salesianos.ecohuerto20.model.ResponseContainer;
import com.triana.salesianos.ecohuerto20.model.UserResponse;
import com.triana.salesianos.ecohuerto20.retrofit.generator.ServiceGenerator;
import com.triana.salesianos.ecohuerto20.retrofit.generator.TipoAutenticacion;
import com.triana.salesianos.ecohuerto20.retrofit.services.HuertoService;
import com.triana.salesianos.ecohuerto20.retrofit.services.LoginService;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddHuertoFragment extends DialogFragment {
    private static final String ARG_ID_USUARIO = "id_usuario";
    private static final int READ_REQUEST_CODE = 42;
    private int idNewHuerto;
    private EditText etNombre, etDireccion, etDimensiones;
    private ImageView imgCargada;
    private Button btnUpload ;
    private Uri uriSelected;
    private UserResponse userL;
    private HuertoDTO huerto;
    private Espacio espacio;
    Context ctx;


    public AddHuertoFragment() {
        // Required empty public constructor
    }

    //Metodo Busqueda Archivos(Botón)


    public static AddHuertoFragment newInstance(String idUsuario) {
        AddHuertoFragment fragment = new AddHuertoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ID_USUARIO, idUsuario);
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
                        final HuertoService service = ServiceGenerator.createService(HuertoService.class,
                                UtilToken.getToken(ctx), TipoAutenticacion.JWT);

                        LoginService serviceUser = ServiceGenerator.createService(LoginService.class,
                                UtilToken.getToken(ctx), TipoAutenticacion.JWT);

                        Call<UserResponse> call = serviceUser.oneUser(UtilToken.getIdUser(ctx));
                        call.enqueue(new Callback<UserResponse>() {
                            @Override
                            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                if (response.isSuccessful()) {

                                    userL = response.body();
                                    userL.getId();
                                    espacio = new Espacio(null, etDimensiones.getText().toString());
                                    huerto = new HuertoDTO (etNombre.getText().toString(),etDireccion.getText().toString(), imgCargada.toString() ,espacio, userL.getId());
                                    Call <HuertosResponse> calla = service.addHuerto(huerto);
                                    calla.enqueue(new Callback<HuertosResponse>() {
                                        @Override
                                        public void onResponse(Call<HuertosResponse> calla, Response<HuertosResponse> response) {
                                            if (response.isSuccessful()) {
                                                Toast.makeText(ctx, "Response Successful", Toast.LENGTH_LONG).show();
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
                                    //recyclerView.setAdapter(new MyHuertoRecyclerViewAdapter(ctx, response.body().getRows(), mListener));
                                } else {
                                    // Toast
                                }

                            }
                            @Override
                            public void onFailure(Call<UserResponse> call, Throwable t) {
                                // Toast
                                Log.i("onFailure", "error en retrofit");
                            }
                        });








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
                if (uriSelected != null) {

                    HuertoService service = ServiceGenerator.createService(HuertoService.class);

                    try {
                        InputStream inputStream = getActivity().getContentResolver().openInputStream(uriSelected);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                        int cantBytes;
                        byte[] buffer = new byte[1024*4];

                        while ((cantBytes = bufferedInputStream.read(buffer,0,1024*4)) != -1) {
                            baos.write(buffer,0,cantBytes);
                        }


                        RequestBody requestFile =
                                RequestBody.create(
                                        MediaType.parse(getActivity().getContentResolver().getType(uriSelected)), baos.toByteArray());


                        MultipartBody.Part body =
                                MultipartBody.Part.createFormData("foto", "foto", requestFile);


                        RequestBody nombre = RequestBody.create(MultipartBody.FORM, etNombre.getText().toString().trim());
                        RequestBody direccion = RequestBody.create(MultipartBody.FORM, etDireccion.getText().toString().trim());
                        RequestBody dimensiones = RequestBody.create(MultipartBody.FORM, etDimensiones.getText().toString().trim());


                        Call<HuertosResponse> callRegisterImg= service.registerImg(body, nombre, direccion, dimensiones);

                        callRegisterImg.enqueue(new Callback<HuertosResponse>() {
                            @Override
                            public void onResponse(Call<HuertosResponse> call, Response<HuertosResponse> response) {
                                if (response.isSuccessful()) {
                                    Log.d("Uploaded", "Éxito");
                                    Log.d("Uploaded", response.body().toString());
                                } else {
                                    Log.e("Upload error", response.errorBody().toString());
                                }
                            }

                            @Override
                            public void onFailure(Call<HuertosResponse> call, Throwable t) {
                                Log.e("Upload error", t.getMessage());
                            }
                        });


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

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
                uriSelected = uri;

            }
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.ctx = context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
