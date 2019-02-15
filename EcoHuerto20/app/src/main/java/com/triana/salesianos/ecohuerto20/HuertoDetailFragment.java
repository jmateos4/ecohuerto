package com.triana.salesianos.ecohuerto20;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.triana.salesianos.ecohuerto20.model.HuertosResponse;
import com.triana.salesianos.ecohuerto20.retrofit.generator.ServiceGenerator;
import com.triana.salesianos.ecohuerto20.retrofit.generator.TipoAutenticacion;
import com.triana.salesianos.ecohuerto20.retrofit.services.HuertoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;

/**
 * A fragment representing a single Prueba detail screen.
 * This fragment is either contained in a {@link HuertoFragment}
 * in two-pane mode (on tablets) or a {@link HuertoDetailActivity}
 * on handsets.
 */
public class HuertoDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private HuertosResponse mItem;

    private HuertoService service = ServiceGenerator.createService(HuertoService.class,
            ServiceGenerator.jwtToken, TipoAutenticacion.JWT);


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HuertoDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.huerto_detail, container, false);

        // Show the dummy content as text in a TextView.

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.


            final String idHuerto = getArguments().getString(ARG_ITEM_ID);

            Activity activity = this.getActivity();
            final CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);


            Call<HuertosResponse> call = service.oneHuerto(idHuerto);
            call.enqueue(new Callback<HuertosResponse>() {
                @Override
                public void onResponse(Call<HuertosResponse> call, Response<HuertosResponse> response) {
                    if (response.isSuccessful()) {
                        mItem = new HuertosResponse(idHuerto, response.body().getNombre(), response.body().getDireccion(), response.body().getFoto(), response.body().getEspacio(), response.body().getUser(), response.body().getCreatedAt(), response.body().getUpdatedAt());
                        appBarLayout.setTitle(mItem.getNombre());
                        //appBarLayout.setBackground(LoadImageFromWebOperations(mItem.getFoto()));
                        Glide
                                .with(getContext())
                                .load(mItem.getFoto())
                                .into(new SimpleTarget<Drawable>(){

                                    @Override
                                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                        appBarLayout.setBackground(resource);
                                    }
                                });
                        ((TextView) rootView.findViewById(R.id.localizacion_detail)).setText(mItem.getDireccion());
                        ((TextView) rootView.findViewById(R.id.dimension_detail)).setText(mItem.getEspacio().getDimensiones());
                    } else {
                        // Toast
                    }


                }

                @Override
                public void onFailure(Call<HuertosResponse> call, Throwable t) {
                    // Toast
                    Log.i("onFailure", "error en retrofit");
                }
            });


        }


        return rootView;
    }
}
