package com.mcenteno.ecohuerto;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.mcenteno.ecohuerto.model.Huerto;
import com.mcenteno.ecohuerto.retrofit.generator.ServiceGenerator;
import com.mcenteno.ecohuerto.retrofit.generator.TipoAutenticacion;
import com.mcenteno.ecohuerto.retrofit.services.HuertoService;


import java.io.InputStream;
import java.net.URL;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a single Huerto detail screen.
 * This fragment is either contained in a {@link HuertoListActivity}
 * in two-pane mode (on tablets) or a {@link HuertoDetailActivity}
 * on handsets.
 */
public class HuertoDetailFragment extends Fragment {

    private HuertoService service = ServiceGenerator.createService(HuertoService.class,
            ServiceGenerator.jwtToken, TipoAutenticacion.JWT);

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Huerto mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HuertoDetailFragment() {
    }

//    public static Drawable LoadImageFromWebOperations(String url) {
//        try {
//            InputStream is = (InputStream) new URL(url).getContent();
//            Drawable d = Drawable.createFromStream(is, "src");
//            return d;
//        } catch (Exception e) {
//            return null;
//        }
//    }

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


            Call<Huerto> call = service.oneHuerto(idHuerto, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVjNWMwZjU4ZTE1YjY2MDAyMjE5YjU3MCIsImlhdCI6MTU0OTU0MjU1M30.wPQXTmzo4CCRImyoP0PhAhJI6-1FdfytGIMJvP6oM-g");
            call.enqueue(new Callback<Huerto>() {
                @Override
                public void onResponse(Call<Huerto> call, Response<Huerto> response) {
                    if (response.isSuccessful()) {
                        mItem = new Huerto(idHuerto, response.body().getNombre(), response.body().getDireccion(), response.body().getFoto(), response.body().getEspacio(), response.body().getUser(), response.body().getCreatedAt(), response.body().getUpdatedAt());
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
                        ((TextView) rootView.findViewById(R.id.user_detail)).setText(mItem.getEspacio().getDimensiones());
                    } else {
                        // Toast
                    }


                }

                @Override
                public void onFailure(Call<Huerto> call, Throwable t) {
                    // Toast
                    Log.i("onFailure", "error en retrofit");
                }
            });

        }


        return rootView;
    }


}
