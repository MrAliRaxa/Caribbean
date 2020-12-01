package com.codecoy.caribbean.adaptor.recycler_adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.data_model.Item;
import com.codecoy.caribbean.databinding.DealsPromotionsRowBinding;

import java.util.List;

public class DealsAdaptor extends RecyclerView.Adapter<DealsAdaptor.ViewHolder> {
    private Context context;
    private List<Item> items;


    public DealsAdaptor(Context context) {
        this.context = context;
    }

    public DealsAdaptor(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DealsPromotionsRowBinding promotionsRowBinding= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.deals_promotions_row,parent,false);
        return new ViewHolder(promotionsRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemDescription.setText(items.get(position).getContent());
        Glide.with(context).load(items.get(position).getImageUrl()).override(512,512).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private TextView itemDescription;
        public ViewHolder(@NonNull DealsPromotionsRowBinding itemView) {
            super(itemView.getRoot());
            itemImage=itemView.dealsImage;
            itemDescription=itemView.dealsDescription;
        }
    }
}
