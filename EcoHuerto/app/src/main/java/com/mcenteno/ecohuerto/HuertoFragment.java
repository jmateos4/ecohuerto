package com.mcenteno.ecohuerto;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.mcenteno.ecohuerto.interfaces.HuertoInteractionListener;
import com.mcenteno.ecohuerto.model.Huerto;
import com.mcenteno.ecohuerto.model.ResponseContainer;
import com.mcenteno.ecohuerto.retrofit.generator.ServiceGenerator;
import com.mcenteno.ecohuerto.retrofit.generator.TipoAutenticacion;
import com.mcenteno.ecohuerto.retrofit.services.HuertoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link HuertoInteractionListener}
 * interface.
 */
public class HuertoFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private HuertoInteractionListener mListener;
    private Context ctx;

    private HuertoService service = ServiceGenerator.createService(HuertoService.class,
            UtilToken.getToken(ctx), TipoAutenticacion.JWT);

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HuertoFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static HuertoFragment newInstance(int columnCount) {
        HuertoFragment fragment = new HuertoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_huerto_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            final RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            /**
             * llamada a la api para listar huerto
             */


            /*
            @GET("huertos")
    Call<HuertosResponse> listHuerto(@Query("access_token") String access_token);
             */

            Call<ResponseContainer> call = service.listHuerto();
            call.enqueue(new Callback<ResponseContainer>() {
                @Override
                public void onResponse(Call<ResponseContainer> call, Response<ResponseContainer> response) {
                    if (response.isSuccessful()) {
                        recyclerView.setAdapter(new MyHuertoRecyclerViewAdapter(ctx, response.body().getRows(), mListener));
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
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.ctx = context;
        if (context instanceof HuertoInteractionListener) {
            mListener = (HuertoInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
