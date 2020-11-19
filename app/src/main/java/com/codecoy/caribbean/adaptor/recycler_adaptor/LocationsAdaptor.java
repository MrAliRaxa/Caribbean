package com.codecoy.caribbean.adaptor.recycler_adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.codecoy.caribbean.R;
import com.codecoy.caribbean.databinding.LocationRowBinding;

public class LocationsAdaptor extends RecyclerView.Adapter<LocationsAdaptor.ViewHolder> {
    private Context context;


    public LocationsAdaptor(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LocationRowBinding locationRowBinding= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.location_row,parent,false);
        return new ViewHolder(locationRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 50;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull LocationRowBinding itemView) {
            super(itemView.getRoot());
        }
    }
}
