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
import com.codecoy.caribbean.databinding.FragmentBuildingsBinding;
import com.codecoy.caribbean.listeners.OnItemLoadListeners;
import com.codecoy.caribbean.repository.Repository;

import java.util.List;


public class Buildings extends Fragment {
    private Shop shop;
    public Buildings() {
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
        FragmentBuildingsBinding mDataBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_buildings, container, false);

        Repository.getShopItems(shop.getId(), DatabaseAddresses.getBuildingCollection(), new OnItemLoadListeners() {
            @Override
            public void onItemLoaded(List<Item> itemList) {
                mDataBinding.buildingsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mDataBinding.buildingsRecyclerView.setAdapter(new DealsAdaptor(getContext(),itemList));
            }

            @Override
            public void onEmpty() {
                mDataBinding.buildingsMsg.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(String e) {
                mDataBinding.buildingsMsg.setVisibility(View.VISIBLE);
                mDataBinding.buildingsMsg.setText("Error "+e);
            }
        });

        return mDataBinding.getRoot();
    }
}