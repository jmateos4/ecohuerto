package com.triana.salesianos.ecohuerto20;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.triana.salesianos.ecohuerto20.model.HuertosResponse;
import com.triana.salesianos.ecohuerto20.model.ResponseContainer;
import com.triana.salesianos.ecohuerto20.retrofit.generator.ServiceGenerator;
import com.triana.salesianos.ecohuerto20.retrofit.generator.TipoAutenticacion;
import com.triana.salesianos.ecohuerto20.retrofit.services.HuertoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An activity representing a list of Pruebas. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PruebaDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class PruebaListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.prueba_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.prueba_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    /**
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, mTwoPane));
    }*/

    private void setupRecyclerView(@NonNull final RecyclerView recyclerView) {

        HuertoService service = ServiceGenerator.createService(HuertoService.class,
                UtilToken.getToken(this), TipoAutenticacion.JWT);

        Call<ResponseContainer<HuertosResponse>> call = service.listHuerto();
        call.enqueue(new Callback<ResponseContainer<HuertosResponse>>() {
            @Override
            public void onResponse(Call<ResponseContainer<HuertosResponse>> call, Response<ResponseContainer<HuertosResponse>> response) {
                if (response.isSuccessful()) {
                    //adapter = new SimpleItemRecyclerViewAdapter(HuertoListActivity.this, response.body().getRows(), mTwoPane);
                    //recyclerView.setAdapter(adapter);
                    recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(PruebaListActivity.this, response.body().getRows(), mTwoPane));
                } else {
                    Toast.makeText(PruebaListActivity.this, "Fallo de consulta", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseContainer<HuertosResponse>> call, Throwable t) {
                Toast.makeText(PruebaListActivity.this, "Fallo de conexión", Toast.LENGTH_LONG).show();
                Log.i("onFailure", "error en retrofit");
            }
        });
    }





    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final PruebaListActivity mParentActivity;
        private final List<HuertosResponse> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HuertosResponse item = (HuertosResponse) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(PruebaDetailFragment.ARG_ITEM_ID, item.getId());
                    PruebaDetailFragment fragment = new PruebaDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.prueba_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, PruebaDetailFragment.class);
                    intent.putExtra(PruebaDetailFragment.ARG_ITEM_ID, item.getId());

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(PruebaListActivity parent,
                                      List<HuertosResponse> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.prueba_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mNombreHuerto.setText(mValues.get(position).getNombre());
            holder.mDireccion.setText(mValues.get(position).getDireccion());

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mNombreHuerto;
            final TextView mDireccion;

            ViewHolder(View view) {
                super(view);
                mNombreHuerto = (TextView) view.findViewById(R.id.nombre);
                mDireccion = (TextView) view.findViewById(R.id.direccion);
            }
        }
    }


    /**





    public class HuertoListActivity extends AppCompatActivity {
        HuertoService service = ServiceGenerator.createService(HuertoService.class,
                ServiceGenerator.jwtToken, TipoAutenticacion.JWT);
        private SimpleItemRecyclerViewAdapter adapter;


        private boolean mTwoPane;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_huerto_list);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            toolbar.setTitle(getTitle());

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            if (findViewById(R.id.huerto_detail_container) != null) {
                // The detail container view will be present only in the
                // large-screen layouts (res/values-w900dp).
                // If this view is present, then the
                // activity should be in two-pane mode.
                mTwoPane = true;
            }

            View recyclerView = findViewById(R.id.huerto_list);
            assert recyclerView != null;
            setupRecyclerView((RecyclerView) recyclerView);
        }

        private void setupRecyclerView(@NonNull final RecyclerView recyclerView) {
            Call<ResponseContainer> call = service.listHuerto();
            call.enqueue(new Callback<ResponseContainer>() {
                @Override
                public void onResponse(Call<ResponseContainer> call, Response<ResponseContainer> response) {
                    if (response.isSuccessful()) {
                        //adapter = new SimpleItemRecyclerViewAdapter(HuertoListActivity.this, response.body().getRows(), mTwoPane);
                        //recyclerView.setAdapter(adapter);
                    } else {
                        // Toast
                    }
                }

                @Override
                public void onFailure(Call<ResponseContainer> call, Throwable t) {
                    // Toast
                    Log.i("onFailure", "error en retrofit");
                }
            });
        }

        public static class SimpleItemRecyclerViewAdapter
                extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

            private final HuertoListActivity mParentActivity;
            private final HuertosResponse mValues;
            private final boolean mTwoPane;
            private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Huerto item = (Huerto) view.getTag();
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(HuertoDetailFragment.ARG_ITEM_ID, item.getId());
                        HuertoDetailFragment fragment = new HuertoDetailFragment();
                        fragment.setArguments(arguments);
                        mParentActivity.getSupportFragmentManager().beginTransaction()
                                .replace(R.id.huerto_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = view.getContext();
                        Intent intent = new Intent(context, HuertoDetailActivity.class);
                        intent.putExtra(HuertoDetailFragment.ARG_ITEM_ID, item.getId());

                        context.startActivity(intent);
                    }
                }
            };

            SimpleItemRecyclerViewAdapter(HuertoListActivity parent,
                                          HuertosResponse items,
                                          boolean twoPane) {
                mValues = items;
                mParentActivity = parent;
                mTwoPane = twoPane;
            }

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.huerto_list_content, parent, false);
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(final ViewHolder holder, int position) {
                holder.mNombreHuerto.setText(mValues.getRows().get(position).getNombre());
                holder.mDireccion.setText(mValues.getRows().get(position).getDireccion());

                holder.itemView.setTag(mValues.getRows().get(position));
                holder.itemView.setOnClickListener(mOnClickListener);
            }

            @Override
            public int getItemCount() {
                return mValues.getCount();
            }

            class ViewHolder extends RecyclerView.ViewHolder {
                final TextView mNombreHuerto;
                final TextView mDireccion;

                ViewHolder(View view) {
                    super(view);
                    mNombreHuerto = (TextView) view.findViewById(R.id.nombre);
                    mDireccion = (TextView) view.findViewById(R.id.direccion);
                }
            }
        }



     */
}
