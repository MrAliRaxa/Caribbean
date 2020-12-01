package com.codecoy.caribbean.fragments.shop_view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codecoy.caribbean.R;
import com.codecoy.caribbean.adaptor.recycler_adaptor.LocationsAdaptor;
import com.codecoy.caribbean.data_model.Shop;
import com.codecoy.caribbean.data_model.ShopLocation;
import com.codecoy.caribbean.database_controller.DatabaseAddresses;
import com.codecoy.caribbean.databinding.FragmentLocationBinding;
import com.codecoy.caribbean.listeners.OnShopLocationLoadListeners;
import com.codecoy.caribbean.repository.Repository;

import java.util.List;


public class Location extends Fragment {






    private Shop shop;
    public Location() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            shop=getArguments().getParcelable("shop");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       FragmentLocationBinding mDataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_location, container, false);

        Repository.getLocations(shop.getId(), DatabaseAddresses.getShopLocationCollection(), new OnShopLocationLoadListeners() {
            @Override
            public void onLocationsLoaded(List<ShopLocation> locationList) {
                RecyclerView recyclerView=mDataBinding.locationRecyclerView;
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(new LocationsAdaptor(getContext(),locationList));
            }

            @Override
            public void onEmpty() {
                mDataBinding.locationMsg.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(String e) {
                mDataBinding.locationMsg.setVisibility(View.VISIBLE);
                mDataBinding.locationMsg.setText("Error "+e);
            }
        });



       return mDataBinding.getRoot();
    }
}