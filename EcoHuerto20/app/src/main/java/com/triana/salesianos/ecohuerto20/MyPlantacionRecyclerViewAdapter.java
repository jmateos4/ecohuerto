package com.triana.salesianos.ecohuerto20;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.triana.salesianos.ecohuerto20.interfaces.PlantacionInteractionListener;
import com.triana.salesianos.ecohuerto20.model.PlantacionResponse;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlantacionResponse} and makes a call to the
 * specified {@link PlantacionInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPlantacionRecyclerViewAdapter extends RecyclerView.Adapter<MyPlantacionRecyclerViewAdapter.ViewHolder> {

    private final List<PlantacionResponse> mValues;
    private final PlantacionInteractionListener mListener;
    private Context ctx;

    public MyPlantacionRecyclerViewAdapter(Context ctx, List<PlantacionResponse> items, PlantacionInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.ctx=ctx;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_plantacion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNombre.setText(mValues.get(position).getNombre());
        holder.mTipo.setText(mValues.get(position).getTipo());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNombre;
        public final TextView mTipo;
        public PlantacionResponse mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNombre = (TextView) view.findViewById(R.id.nombre);
            mTipo = (TextView) view.findViewById(R.id.tipo);
        }

    }
}
