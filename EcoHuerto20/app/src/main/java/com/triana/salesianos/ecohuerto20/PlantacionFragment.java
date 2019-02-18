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


import com.triana.salesianos.ecohuerto20.interfaces.PlantacionInteractionListener;
import com.triana.salesianos.ecohuerto20.model.Espacio;
import com.triana.salesianos.ecohuerto20.model.HuertosResponse;
import com.triana.salesianos.ecohuerto20.model.PlantacionResponse;
import com.triana.salesianos.ecohuerto20.model.ResponseContainer;
import com.triana.salesianos.ecohuerto20.retrofit.generator.ServiceGenerator;
import com.triana.salesianos.ecohuerto20.retrofit.generator.TipoAutenticacion;
import com.triana.salesianos.ecohuerto20.retrofit.services.HuertoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.triana.salesianos.ecohuerto20.HuertoDetailFragment.ARG_ITEM_ID;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link PlantacionInteractionListener}
 * interface.
 */
public class PlantacionFragment extends Fragment {


    public static final String ARG_ITEM_ID = "item_id";
    private Context ctx;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private PlantacionInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PlantacionFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PlantacionFragment newInstance(int columnCount) {
        PlantacionFragment fragment = new PlantacionFragment();
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
        final View view = inflater.inflate(R.layout.fragment_plantacion_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            final RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            //recyclerView.setAdapter(new MyPlantacionRecyclerViewAdapter(ctx, listPlantacion, mListener));
            HuertoService service = ServiceGenerator.createService(HuertoService.class,
                    UtilToken.getToken(ctx), TipoAutenticacion.JWT);

            final String idHuerto = getArguments().getString(ARG_ITEM_ID);
            Call<List<PlantacionResponse>> call = service.listPlantacion(idHuerto);
            call.enqueue(new Callback<List<PlantacionResponse>>() {
                @Override
                public void onResponse(Call<List<PlantacionResponse>> call, Response<List<PlantacionResponse>> response) {
                    if (response.isSuccessful()) {
                        recyclerView.setAdapter(new MyPlantacionRecyclerViewAdapter(ctx, response.body(), mListener));
                    } else {
                        // Toast
                    }


                }

                @Override
                public void onFailure(Call<List<PlantacionResponse>> call, Throwable t) {
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
        this.ctx=context;
        if (context instanceof PlantacionInteractionListener) {
            mListener = (PlantacionInteractionListener) context;
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


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
}
