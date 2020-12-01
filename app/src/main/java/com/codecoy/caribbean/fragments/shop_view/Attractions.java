package com.codecoy.caribbean.fragments.shop_view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codecoy.caribbean.R;
import com.codecoy.caribbean.adaptor.recycler_adaptor.DealsAdaptor;
import com.codecoy.caribbean.data_model.Item;
import com.codecoy.caribbean.data_model.Shop;
import com.codecoy.caribbean.database_controller.DatabaseAddresses;
import com.codecoy.caribbean.databinding.FragmentAttractionsBinding;
import com.codecoy.caribbean.listeners.OnItemLoadListeners;
import com.codecoy.caribbean.repository.Repository;

import java.util.List;


public class Attractions extends Fragment {

    private Shop shop;
    public Attractions() {
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
        FragmentAttractionsBinding mDataBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_attractions, container, false);

        Repository.getShopItems(shop.getId(), DatabaseAddresses.getAttractionsCollection(), new OnItemLoadListeners() {
            @Override
            public void onItemLoaded(List<Item> itemList) {
                mDataBinding.attractionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mDataBinding.attractionRecyclerView.setAdapter(new DealsAdaptor(getContext(),itemList));
            }

            @Override
            public void onEmpty() {
                mDataBinding.attractionsMsg.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(String e) {
                mDataBinding.attractionsMsg.setVisibility(View.VISIBLE);
                mDataBinding.attractionsMsg.setText("Error "+e);
            }
        });



        return mDataBinding.getRoot();
    }
}