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
import com.codecoy.caribbean.dataModel.ATM;
import com.codecoy.caribbean.dataModel.Shop;
import com.codecoy.caribbean.databinding.ShopRowLayoutBinding;
import com.codecoy.caribbean.listeners.OnShopClick;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class ATMAdaptor extends RecyclerView.Adapter<ATMAdaptor.ViewHolder> {


    private Context context;
    private List<ATM> atmList;
    private OnShopClick onShopClick;


    public ATMAdaptor(Context context, List<ATM> atmList, OnShopClick onShopClick) {
        this.context = context;
        this.atmList = atmList;
        this.onShopClick = onShopClick;
    }

    @NonNull
    @Override
    public ATMAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ShopRowLayoutBinding shopRowLayoutBinding= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.shop_row_layout,parent,false);
        return new ViewHolder(shopRowLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ATMAdaptor.ViewHolder holder, int position) {
        ATM atm=atmList.get(position);
        holder.shopRowLayoutBinding.shopName.setText(atm.getAtmName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShopClick.onClick(new LatLng(atm.getLat(),atm.getLng()),position);
            }
        });
        Glide.with(context).load(atm.getAtmImage()).override(256,256).into(holder.shopRowLayoutBinding.shopImage);
    }

    @Override
    public int getItemCount() {
        return atmList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ShopRowLayoutBinding shopRowLayoutBinding;
        public ViewHolder(@NonNull ShopRowLayoutBinding itemView) {
            super(itemView.getRoot());
            this.shopRowLayoutBinding=itemView;
        }
    }
}
