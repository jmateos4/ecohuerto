package com.triana.salesianos.ecohuerto20;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.triana.salesianos.ecohuerto20.interfaces.PlantacionInteractionListener;

/**
 * An activity representing a single Prueba detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link HuertoFragment}.
 */
public class HuertoDetailActivity extends AppCompatActivity implements PlantacionInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huerto_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrameLayout detalle = findViewById(R.id.plantaciones_detail_container);
                detalle.setVisibility(View.VISIBLE);
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transacHuertoDetailFragmenttion.
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
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, HuertoFragment.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickPlantacion(String nombre) {

    }
}
