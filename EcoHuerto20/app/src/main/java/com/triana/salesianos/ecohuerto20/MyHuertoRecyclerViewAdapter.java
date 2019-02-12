package com.triana.salesianos.ecohuerto20;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.ImageView;
=======
import android.widget.Button;
>>>>>>> 5b774750df7012218b777e35b6188b39cbc0fbdd
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.triana.salesianos.ecohuerto20.interfaces.HuertoInteractionListener;
import com.triana.salesianos.ecohuerto20.model.Huerto;
import com.triana.salesianos.ecohuerto20.model.HuertosResponse;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link HuertosResponse} and makes a call to the
 * specified {@link HuertoInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyHuertoRecyclerViewAdapter extends RecyclerView.Adapter<MyHuertoRecyclerViewAdapter.ViewHolder> {

    private final List<HuertosResponse> mValues;
    private final HuertoInteractionListener mListener;
    private Context ctx;

    public MyHuertoRecyclerViewAdapter(Context ctx, List<HuertosResponse> items, HuertoInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_huerto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mNombre.setText(mValues.get(position).getNombre());
        holder.mDireccion.setText(mValues.get(position).getDireccion());

<<<<<<< HEAD
        Glide
                .with(ctx)
                .load(mValues.get(position).getFoto())
                .into(new SimpleTarget<Drawable>(){

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        holder.mFotoHuerto.setBackground(resource);
                    }
                });

        holder.mView.setOnClickListener(new View.OnClickListener() {
=======
        holder.mBorrar.setOnClickListener(new View.OnClickListener() {
>>>>>>> 5b774750df7012218b777e35b6188b39cbc0fbdd
            @Override
            public void onClick(View v) {
                mListener.borrarHuerto(mValues.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNombre;
        public final TextView mDireccion;
<<<<<<< HEAD
        public final ImageView mFotoHuerto;
=======
        public final Button mBorrar;
>>>>>>> 5b774750df7012218b777e35b6188b39cbc0fbdd
        public HuertosResponse mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNombre = view.findViewById(R.id.nombre);
            mDireccion = view.findViewById(R.id.direccion);
<<<<<<< HEAD
            mFotoHuerto = view.findViewById(R.id.fotoHuerto);
=======
            mBorrar = view.findViewById(R.id.btnBorrar);
>>>>>>> 5b774750df7012218b777e35b6188b39cbc0fbdd
        }
    }
}
