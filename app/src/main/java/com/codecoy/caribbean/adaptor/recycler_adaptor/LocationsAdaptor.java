package com.codecoy.caribbean.adaptor.recycler_adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.data_model.ShopLocation;
import com.codecoy.caribbean.databinding.LocationRowBinding;

import java.util.List;

public class LocationsAdaptor extends RecyclerView.Adapter<LocationsAdaptor.ViewHolder> {
    private Context context;
    private List<ShopLocation> shopLocations;


    public LocationsAdaptor(Context context) {
        this.context = context;
    }

    public LocationsAdaptor(Context context, List<ShopLocation> shopLocations) {
        this.context = context;
        this.shopLocations = shopLocations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LocationRowBinding locationRowBinding= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.location_row,parent,false);
        return new ViewHolder(locationRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(shopLocations.get(position).getShopAddress()!=null){
            holder.mDataBinding.locationRowAddressLine.setText(shopLocations.get(position).getShopAddress());
        }
        holder.mDataBinding.locationRowGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, shopLocations.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        Glide.with(context).load(shopLocations.get(position).getImageUrl()).into(holder.mDataBinding.locationRowImage);
    }

    @Override
    public int getItemCount() {
        return shopLocations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private LocationRowBinding mDataBinding;
        public ViewHolder(@NonNull LocationRowBinding itemView) {
            super(itemView.getRoot());
            this.mDataBinding=itemView;
        }
    }
}
