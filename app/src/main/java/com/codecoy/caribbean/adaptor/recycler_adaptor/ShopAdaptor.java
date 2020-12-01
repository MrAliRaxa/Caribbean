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
import com.codecoy.caribbean.data_model.Shop;
import com.codecoy.caribbean.databinding.ShopRowLayoutBinding;
import com.codecoy.caribbean.listeners.OnShopClick;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class ShopAdaptor extends RecyclerView.Adapter<ShopAdaptor.ViewHolder> {


    private Context context;
    private List<Shop> shops;
    private OnShopClick onShopClick;

    public ShopAdaptor(Context context, List<Shop> shops, OnShopClick onShopClick) {
        this.context = context;
        this.shops = shops;
        this.onShopClick = onShopClick;
    }

    @NonNull
    @Override
    public ShopAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ShopRowLayoutBinding shopRowLayoutBinding= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.shop_row_layout,parent,false);
        return new ViewHolder(shopRowLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopAdaptor.ViewHolder holder, int position) {
        Shop shop=shops.get(position);
        holder.shopRowLayoutBinding.shopName.setText(shops.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShopClick.onClick(new LatLng(shop.getLat(),shop.getLng()),position);
            }
        });
        Glide.with(context).load(shops.get(position).getLogoUrl()).override(256,256).into(holder.shopRowLayoutBinding.shopImage);
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ShopRowLayoutBinding shopRowLayoutBinding;
        public ViewHolder(@NonNull ShopRowLayoutBinding itemView) {
            super(itemView.getRoot());
            this.shopRowLayoutBinding=itemView;
        }
    }
}
