package com.codecoy.caribbean.fragments.shop_view;

import android.app.ProgressDialog;
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
import com.codecoy.caribbean.util.DialogBuilder;


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
        ProgressDialog loading= DialogBuilder.getSimpleLoadingDialog(getContext(),"Loading","Please wait for server response . . .");
        loading.setCanceledOnTouchOutside(false);
        loading.show();

        Repository.getShopInformation(shop.getId(), new OnInformationLoadListeners() {
            @Override
            public void onInformationLoaded(ShopInformationModel shopInformationModel) {
                loading.dismiss();
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
                loading.dismiss();
                mDataBinding.informationInfo.setVisibility(View.VISIBLE);
                mDataBinding.informationMsg.setVisibility(View.GONE);
                Log.d(TAG, "onInformationLoaded: ");
            }

            @Override
            public void onNotFound() {
                loading.dismiss();
                mDataBinding.informationInfo.setVisibility(View.GONE);
                mDataBinding.informationMsg.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(String e) {
                loading.dismiss();
                mDataBinding.informationInfo.setVisibility(View.GONE);
                mDataBinding.informationMsg.setVisibility(View.VISIBLE);

            }
        });

        return mDataBinding.getRoot();
    }
}