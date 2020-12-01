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
import com.codecoy.caribbean.databinding.FragmentStoresBinding;
import com.codecoy.caribbean.listeners.OnItemLoadListeners;
import com.codecoy.caribbean.repository.Repository;

import java.util.List;

public class Stores extends Fragment {


    private Shop shop;

    public Stores() {
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
        FragmentStoresBinding mDataBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_stores, container, false);


        Repository.getShopStoreItem(shop.getId(), new OnItemLoadListeners() {
            @Override
            public void onItemLoaded(List<Item> itemList) {
                mDataBinding.storesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mDataBinding.storesRecyclerView.setAdapter(new DealsAdaptor(getContext(),itemList));
            }

            @Override
            public void onEmpty() {
                mDataBinding.storesMsg.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(String e) {
                mDataBinding.storesMsg.setVisibility(View.VISIBLE);
                mDataBinding.storesMsg.setText("Error "+e);

            }
        });

        return mDataBinding.getRoot();
    }
}