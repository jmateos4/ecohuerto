package com.mcenteno.ecohuerto;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mcenteno.ecohuerto.interfaces.HuertoInteractionListener;
import com.mcenteno.ecohuerto.model.Huerto;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link com.mcenteno.ecohuerto.model.Huerto} and makes a call to the
 * specified {@link com.mcenteno.ecohuerto.interfaces.HuertoInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyHuertoRecyclerViewAdapter extends RecyclerView.Adapter<MyHuertoRecyclerViewAdapter.ViewHolder> {

    private final List<Huerto> mValues;
    private final HuertoInteractionListener mListener;
    private Context ctx;

    public MyHuertoRecyclerViewAdapter(Context ctx, List<Huerto> items, HuertoInteractionListener listener) {
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Huerto mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
