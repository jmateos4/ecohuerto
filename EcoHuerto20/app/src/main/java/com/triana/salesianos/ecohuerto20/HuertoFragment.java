package com.triana.salesianos.ecohuerto20;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.triana.salesianos.ecohuerto20.interfaces.HuertoInteractionListener;
import com.triana.salesianos.ecohuerto20.model.HuertosResponse;
import com.triana.salesianos.ecohuerto20.model.ResponseContainer;
import com.triana.salesianos.ecohuerto20.retrofit.generator.ServiceGenerator;
import com.triana.salesianos.ecohuerto20.retrofit.generator.TipoAutenticacion;
import com.triana.salesianos.ecohuerto20.retrofit.services.HuertoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link com.triana.salesianos.ecohuerto20.interfaces.HuertoInteractionListener}
 * interface.
 */
public class HuertoFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private HuertoInteractionListener mListener;

    private Context ctx;

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
             *
             */

            HuertoService service = ServiceGenerator.createService(HuertoService.class,
                    UtilToken.getToken(ctx), TipoAutenticacion.JWT);

            Call<ResponseContainer<HuertosResponse>> call = service.listHuerto();
            call.enqueue(new Callback<ResponseContainer<HuertosResponse>>() {
                @Override
                public void onResponse(Call<ResponseContainer<HuertosResponse>> call, Response<ResponseContainer<HuertosResponse>> response) {
                    if (response.isSuccessful()) {
                        recyclerView.setAdapter(new MyHuertoRecyclerViewAdapter(ctx, response.body().getRows(), mListener));
                    } else {
                        // Toast
                    }


                }

                @Override
                public void onFailure(Call<ResponseContainer<HuertosResponse>> call, Throwable t) {
                    // Toast
                    Log.i("onFailure", "error en retrofit");
                }
            });

            /**
             *
             */
            //recyclerView.setAdapter(new MyHuertoRecyclerViewAdapter(ctx, DummyContent.ITEMS, mListener));
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
                    + " must implement HuertoInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
