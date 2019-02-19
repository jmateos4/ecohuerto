package com.triana.salesianos.ecohuerto20;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.Toast;

import com.triana.salesianos.ecohuerto20.interfaces.HuertoInteractionListener;
import com.triana.salesianos.ecohuerto20.interfaces.PlantacionInteractionListener;
import com.triana.salesianos.ecohuerto20.model.PlantacionResponse;
import com.triana.salesianos.ecohuerto20.retrofit.generator.ServiceArduinoGenerator;
import com.triana.salesianos.ecohuerto20.retrofit.generator.ServiceGenerator;
import com.triana.salesianos.ecohuerto20.retrofit.generator.TipoAutenticacion;
import com.triana.salesianos.ecohuerto20.retrofit.services.ArduinoService;
import com.triana.salesianos.ecohuerto20.retrofit.services.HuertoService;
import com.triana.salesianos.ecohuerto20.retrofit.services.PlantacionService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An activity representing a single Prueba detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link HuertoFragment}.
 */
public class HuertoDetailActivity extends AppCompatActivity implements PlantacionInteractionListener, HuertoInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huerto_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fabAdd);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogAddPlantacion(getIntent().getStringExtra(HuertoDetailFragment.ARG_ITEM_ID));
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrarHuerto(getIntent().getStringExtra(HuertoDetailFragment.ARG_ITEM_ID));
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {

            Bundle arguments = new Bundle();
            arguments.putString(HuertoDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(HuertoDetailFragment.ARG_ITEM_ID));
            HuertoDetailFragment fragment = new HuertoDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.prueba_detail_container, fragment)
                    .commit();

            Bundle arguments1 = new Bundle();
            arguments1.putString(PlantacionFragment.ARG_ITEM_ID, getIntent().getStringExtra(PlantacionFragment.ARG_ITEM_ID));
            PlantacionFragment fragment1 = new PlantacionFragment();
            fragment1.setArguments(arguments1);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.plantaciones_detail_container, fragment1)
                    .commit();

        }


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, HuertoFragment.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void borrarHuerto(final String idHuerto) {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(HuertoDetailActivity.this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);



        builder.setPositiveButton(R.string.borrar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                HuertoService service = ServiceGenerator.createService(HuertoService.class,
                        UtilToken.getToken(HuertoDetailActivity.this), TipoAutenticacion.JWT);
                Call call = service.borrarHuerto(idHuerto);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(HuertoDetailActivity.this, "Borrado satisfactoriamente", Toast.LENGTH_LONG);
                            startActivity(new Intent(HuertoDetailActivity.this, HuertoActivity.class));
                            finish();
                        } else {
                            Toast.makeText(HuertoDetailActivity.this, "No se ha borrado", Toast.LENGTH_LONG);
                        }

                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        // Toast
                        Log.i("onFailure", "error en retrofit");
                    }
                });
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });


        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        dialog.show();
    }


    @Override
    public void borrarPlantacion(final String idPlantacion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(HuertoDetailActivity.this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);



        builder.setPositiveButton(R.string.borrar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                PlantacionService service = ServiceGenerator.createService(PlantacionService.class,
                        UtilToken.getToken(HuertoDetailActivity.this), TipoAutenticacion.JWT);
                Call call = service.borrarPlantacion(idPlantacion);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        if (response.isSuccessful()) {
                            startActivity(new Intent(HuertoDetailActivity.this, HuertoDetailActivity.class));
                            Toast.makeText(HuertoDetailActivity.this, "Borrado satisfactoriamente", Toast.LENGTH_LONG);
                        } else {
                            Toast.makeText(HuertoDetailActivity.this, "No se ha borrado", Toast.LENGTH_LONG);
                        }

                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        // Toast
                        Log.i("onFailure", "error en retrofit");
                    }
                });
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });


        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        dialog.show();

    }

    public void mostrarDialogAddPlantacion(String idHuerto){
        DialogFragment dialog = AddPlantacionFragment.newInstance(idHuerto);
        dialog.show(getSupportFragmentManager(), "AddPlantacionFragment");
    }

    @Override
    public void setRiegoAutOnOff(final String idPlantacion, final String nombre, final String tipo, final String huerto, final Boolean riegoAut) {
        AlertDialog.Builder builder = new AlertDialog.Builder(HuertoDetailActivity.this);

        if (riegoAut == false) {
            // 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage(R.string.dialog_message_riegoOn)
                    .setTitle(R.string.dialog_title_riegoOn);


            builder.setPositiveButton(R.string.activar, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    PlantacionService service = ServiceGenerator.createService(PlantacionService.class,
                            UtilToken.getToken(HuertoDetailActivity.this), TipoAutenticacion.JWT);
                    Call<PlantacionResponse> call = service.riegoAut(idPlantacion, new PlantacionResponse(nombre, tipo, huerto, true));
                    call.enqueue(new Callback<PlantacionResponse>() {
                        @Override
                        public void onResponse(Call<PlantacionResponse> call, Response<PlantacionResponse> response) {
                            if (response.isSuccessful()) {
                                new PlantacionResponse();
                                ArduinoService service = ServiceArduinoGenerator.createService(ArduinoService.class);
                                Call call1 = service.abrir();
                                call1.enqueue(new Callback() {
                                    @Override
                                    public void onResponse(Call call1, Response response) {
                                        if (response.isSuccessful()) {
                                            Toast.makeText(HuertoDetailActivity.this, "Abierto satisfactoriamente", Toast.LENGTH_LONG);
                                        } else {
                                            Toast.makeText(HuertoDetailActivity.this, "No se ha abierto", Toast.LENGTH_LONG);
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call call1, Throwable t) {
                                        // Toast
                                        Log.i("onFailure", "error en retrofit");
                                    }
                                });
                                startActivity(new Intent(HuertoDetailActivity.this, HuertoActivity.class));
                                Toast.makeText(HuertoDetailActivity.this, "Activado satisfactoriamente", Toast.LENGTH_LONG);
                            } else {
                                Toast.makeText(HuertoDetailActivity.this, "No se ha activado", Toast.LENGTH_LONG);
                            }

                        }

                        @Override
                        public void onFailure(Call<PlantacionResponse> call, Throwable t) {
                            // Toast
                            Log.i("onFailure", "error en retrofit");
                        }
                    });
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });


            // 3. Get the AlertDialog from create()
            AlertDialog dialog = builder.create();

            dialog.show();
        }

        if (riegoAut==true){
            // 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage(R.string.dialog_message_riegoOff)
                    .setTitle(R.string.dialog_title_riegoOff);


            builder.setPositiveButton(R.string.desactivar, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    PlantacionService service = ServiceGenerator.createService(PlantacionService.class,
                            UtilToken.getToken(HuertoDetailActivity.this), TipoAutenticacion.JWT);
                    Call<PlantacionResponse> call = service.riegoAut(idPlantacion, new PlantacionResponse(nombre, tipo, huerto, false));
                    call.enqueue(new Callback<PlantacionResponse>() {
                        @Override
                        public void onResponse(Call<PlantacionResponse> call, Response<PlantacionResponse> response) {
                            if (response.isSuccessful()) {
                                new PlantacionResponse();
                                ArduinoService service = ServiceArduinoGenerator.createService(ArduinoService.class);
                                Call call1 = service.cerrar();
                                call1.enqueue(new Callback() {
                                    @Override
                                    public void onResponse(Call call1, Response response) {
                                        if (response.isSuccessful()) {
                                            Toast.makeText(HuertoDetailActivity.this, "Cerrado satisfactoriamente", Toast.LENGTH_LONG);
                                        } else {
                                            Toast.makeText(HuertoDetailActivity.this, "No se ha cerrado", Toast.LENGTH_LONG);
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call call1, Throwable t) {
                                        // Toast
                                        Log.i("onFailure", "error en retrofit");
                                    }
                                });
                                startActivity(new Intent(HuertoDetailActivity.this, HuertoActivity.class));
                                Toast.makeText(HuertoDetailActivity.this, "Activado satisfactoriamente", Toast.LENGTH_LONG);
                            } else {
                                Toast.makeText(HuertoDetailActivity.this, "No se ha activado", Toast.LENGTH_LONG);
                            }

                        }

                        @Override
                        public void onFailure(Call<PlantacionResponse> call, Throwable t) {
                            // Toast
                            Log.i("onFailure", "error en retrofit");
                        }
                    });
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });


            // 3. Get the AlertDialog from create()
            AlertDialog dialog = builder.create();

            dialog.show();
        }
    }
}
