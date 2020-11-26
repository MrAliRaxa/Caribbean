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
import com.codecoy.caribbean.databinding.FragmentPricesBinding;

public class Prices extends Fragment {


    public Prices() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentPricesBinding mDataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_prices, container, false);

        mDataBinding.activitiesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDataBinding.activitiesRecyclerView.setAdapter(new DealsAdaptor(getContext()));
        return mDataBinding.getRoot();
    }
}