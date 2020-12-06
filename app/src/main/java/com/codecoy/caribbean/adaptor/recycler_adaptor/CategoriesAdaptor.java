package com.codecoy.caribbean.adaptor.recycler_adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.activities.ShopsViewer;
import com.codecoy.caribbean.data_model.ShopCategoryModel;
import com.codecoy.caribbean.databinding.CategoriesLayoutBinding;
import com.codecoy.caribbean.listeners.OnCategoryClick;

import java.util.List;

public class CategoriesAdaptor extends RecyclerView.Adapter<CategoriesAdaptor.ViewHolder> {


    private Context context;
    private List<ShopCategoryModel> shopCategoryModels;
    private OnCategoryClick onCategoryClick;

    public CategoriesAdaptor(Context context, List<ShopCategoryModel> shopCategoryModels, OnCategoryClick onCategoryClick) {
        this.context = context;
        this.shopCategoryModels = shopCategoryModels;
        this.onCategoryClick = onCategoryClick;
    }

    @NonNull
    @Override
    public CategoriesAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoriesLayoutBinding categoriesLayoutBinding= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.categories_layout,parent,false);
        return new ViewHolder(categoriesLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdaptor.ViewHolder holder, int position) {
        holder.categoriesLayoutBinding.setCategory(shopCategoryModels.get(position));
        Glide.with(context).load(shopCategoryModels.get(position).getImageUrl()).override(256,256).into(holder.categoriesLayoutBinding.categoriesImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCategoryClick.onClick(shopCategoryModels.get(position),position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return shopCategoryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CategoriesLayoutBinding categoriesLayoutBinding;
        public ViewHolder(@NonNull CategoriesLayoutBinding itemView) {
            super(itemView.getRoot());
            this.categoriesLayoutBinding=itemView;
        }
    }
}
