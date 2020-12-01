package com.codecoy.caribbean.fragments.shop_view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codecoy.caribbean.R;
import com.codecoy.caribbean.data_model.Shop;
import com.codecoy.caribbean.data_model.ShopInformationModel;
import com.codecoy.caribbean.databinding.FragmentInformationBinding;
import com.codecoy.caribbean.listeners.OnInformationLoadListeners;
import com.codecoy.caribbean.repository.Repository;


public class Information extends Fragment {



    private Shop shop;
    private static final String TAG = "Information";
    public Information() {
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
        FragmentInformationBinding mDataBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_information, container, false);

        Repository.getShopInformation(shop.getId(), new OnInformationLoadListeners() {
            @Override
            public void onInformationLoaded(ShopInformationModel shopInformationModel) {

                mDataBinding.informationAboutUs.setText(shopInformationModel.getAboutUs());
                mDataBinding.mondayTiming.setText(shopInformationModel.getMondayTiming());
                mDataBinding.tuesdayTiming.setText(shopInformationModel.getMondayTiming());
                mDataBinding.wednesdayTiming.setText(shopInformationModel.getMondayTiming());
                mDataBinding.thursdayTiming.setText(shopInformationModel.getMondayTiming());
                mDataBinding.fridayTiming.setText(shopInformationModel.getMondayTiming());
                mDataBinding.saturdayTiming.setText(shopInformationModel.getMondayTiming());
                mDataBinding.sundayTiming.setText(shopInformationModel.getMondayTiming());
                mDataBinding.informationEmail.setText(shopInformationModel.getEmail());
                mDataBinding.informationNumber.setText(shopInformationModel.getPhone());

                Log.d(TAG, "onInformationLoaded: ");
            }

            @Override
            public void onNotFound() {

            }

            @Override
            public void onError(String e) {

            }
        });

        return mDataBinding.getRoot();
    }
}