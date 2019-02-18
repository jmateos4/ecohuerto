package com.triana.salesianos.ecohuerto20;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.triana.salesianos.ecohuerto20.interfaces.HuertoInteractionListener;
import com.triana.salesianos.ecohuerto20.retrofit.generator.ServiceGenerator;
import com.triana.salesianos.ecohuerto20.retrofit.generator.TipoAutenticacion;
import com.triana.salesianos.ecohuerto20.retrofit.services.HuertoService;
public class HuertoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HuertoInteractionListener {

    private HuertoService service;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huerto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        service = ServiceGenerator.createService(HuertoService.class,
                UtilToken.getToken(this), TipoAutenticacion.JWT);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment, new HuertoFragment())
                .commit();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogAddHuerto();
            }
        });


    }


    private void mostrarDialogAddHuerto() {
        DialogFragment dialog = AddHuertoFragment.newInstance(UtilToken.getIdUser(HuertoActivity.this));
        dialog.show(getSupportFragmentManager(), "AddHuertoFragment");
    }

    private void mostrarDialogEditUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to log out?")
                .setTitle("Log out");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                UtilToken.setIdUser(HuertoActivity.this, null);
                UtilToken.setToken(HuertoActivity.this, null);
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.huerto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment f = null;

        switch (item.getItemId()) {
            case R.id.nav_huertos:
                f = new HuertoFragment();
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mostrarDialogAddHuerto();
                    }
                });
                fab.show();
                break;
            case R.id.nav_pluviometro:

                fab.hide();
                break;
            case R.id.nav_centroMeteorologico:
                f = new CentroMeteorologicoFragment();
                fab.hide();
                break;
            case R.id.nav_profile:
                f = new UserFragment();
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mostrarDialogAddHuerto();
                    }
                });
                fab.show();
                break;
            case R.id.logOut:

                break;
        }

        if(f != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, f)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you want to log out?")
                    .setTitle("Log out");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    UtilToken.setIdUser(HuertoActivity.this, null);
                    UtilToken.setToken(HuertoActivity.this, null);
                    finish();
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

    @Override
    public void borrarHuerto(String id) {

    }
}
