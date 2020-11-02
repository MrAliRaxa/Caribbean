package com.codecoy.caribbean.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.codecoy.caribbean.dataModel.Country;
import com.codecoy.caribbean.R;


import java.util.List;

public class CounterySpinnerAdaptor extends BaseAdapter {
    private Context context;
    private List<Country> countries;

    public CounterySpinnerAdaptor(Context context, List<Country> countries) {
        this.context = context;
        this.countries = countries;
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

         View view =LayoutInflater.from(context).inflate(R.layout.country_row,null);
         ImageView flag=view.findViewById(R.id.countryImage);
         TextView name=view.findViewById(R.id.countryTitle);
        Country Country =countries.get(position);
        Glide.with(context).load(Country.getFlagImageUrl()).override(256,256).into(flag);
         name.setText(Country.getInformation().getName());
         return view;
    }
}
