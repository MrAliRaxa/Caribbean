package com.codecoy.caribbean.adaptor.recycler_adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.codecoy.caribbean.R;
import com.codecoy.caribbean.databinding.DealsPromotionsRowBinding;

public class DealsAdaptor extends RecyclerView.Adapter<DealsAdaptor.ViewHolder> {
    private Context context;


    public DealsAdaptor(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DealsPromotionsRowBinding promotionsRowBinding= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.deals_promotions_row,parent,false);
        return new ViewHolder(promotionsRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 50;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull DealsPromotionsRowBinding itemView) {
            super(itemView.getRoot());
        }
    }
}
