package com.codecoy.caribbean.adaptor.recycler_adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.data_model.ShopLocation;
import com.codecoy.caribbean.databinding.ShopRowLayoutBinding;
import com.codecoy.caribbean.listeners.OnShopClick;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class LocationAdaptor extends RecyclerView.Adapter<LocationAdaptor.ViewHolder> {


    private Context context;
    private List<ShopLocation> shopLocations;
    private OnShopClick onShopClick;


    public LocationAdaptor(Context context, List<ShopLocation> shopLocations, OnShopClick onShopClick) {
        this.context = context;
        this.shopLocations = shopLocations;
        this.onShopClick = onShopClick;
    }

    @NonNull
    @Override
    public LocationAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ShopRowLayoutBinding shopRowLayoutBinding= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.shop_row_layout,parent,false);
        return new ViewHolder(shopRowLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdaptor.ViewHolder holder, int position) {
        ShopLocation shopLocation=shopLocations.get(position);
        holder.shopRowLayoutBinding.shopName.setText(shopLocation.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShopClick.onClick(new LatLng(shopLocation.getLat(),shopLocation.getLng()),position);
            }
        });
        Glide.with(context).load(shopLocation.getImageUrl()).override(256,256).into(holder.shopRowLayoutBinding.shopImage);
    }

    @Override
    public int getItemCount() {
        return shopLocations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ShopRowLayoutBinding shopRowLayoutBinding;
        public ViewHolder(@NonNull ShopRowLayoutBinding itemView) {
            super(itemView.getRoot());
            this.shopRowLayoutBinding=itemView;
        }
    }
}
