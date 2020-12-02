package com.codecoy.caribbean.fragments.shop_view;

import android.app.ProgressDialog;
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
import com.codecoy.caribbean.databinding.FragmentHistoricalSitesBinding;
import com.codecoy.caribbean.databinding.FragmentHistoryBinding;
import com.codecoy.caribbean.listeners.OnItemLoadListeners;
import com.codecoy.caribbean.repository.Repository;
import com.codecoy.caribbean.util.DialogBuilder;

import java.util.List;


public class HistoricalSites extends Fragment {


    private Shop shop;

    public HistoricalSites() {
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
        FragmentHistoricalSitesBinding mDataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_historical_sites, container, false);
        ProgressDialog loading= DialogBuilder.getSimpleLoadingDialog(getContext(),"Loading","Please wait for server response . . .");
        loading.setCanceledOnTouchOutside(false);
        loading.show();

        Repository.getShopItems(shop.getId(), DatabaseAddresses.getHistoricalCollection(), new OnItemLoadListeners() {
            @Override
            public void onItemLoaded(List<Item> itemList) {
                mDataBinding.historicalSitesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mDataBinding.historicalSitesRecyclerView.setAdapter(new DealsAdaptor(getContext(),itemList));
                loading.dismiss();

            }

            @Override
            public void onEmpty() {
                mDataBinding.historicalSitesMsg.setVisibility(View.VISIBLE);
                loading.dismiss();
            }

            @Override
            public void onFailure(String e) {
                mDataBinding.historicalSitesMsg.setVisibility(View.VISIBLE);
                mDataBinding.historicalSitesMsg.setText("Error "+e);
                loading.dismiss();
            }
        });


        return mDataBinding.getRoot();
    }
}