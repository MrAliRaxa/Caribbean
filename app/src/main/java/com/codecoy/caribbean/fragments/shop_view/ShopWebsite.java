package com.codecoy.caribbean.fragments.shop_view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codecoy.caribbean.R;
import com.codecoy.caribbean.data_model.Shop;
import com.codecoy.caribbean.databinding.FragmentShopWebsiteBinding;
import com.codecoy.caribbean.listeners.OnStringLoadListeners;
import com.codecoy.caribbean.repository.Repository;
import com.codecoy.caribbean.util.DialogBuilder;


public class ShopWebsite extends Fragment {

    private Shop shop;
    public ShopWebsite() {
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
        FragmentShopWebsiteBinding mDataBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_shop_website, container, false);
        ProgressDialog loading= DialogBuilder.getSimpleLoadingDialog(getContext(),"Loading","Please wait for server response . . .");
        loading.setCanceledOnTouchOutside(false);
        loading.show();

        Repository.getShopWebsite(shop.getId(), new OnStringLoadListeners() {
            @Override
            public void onStringLoaded(String s) {
                loading.dismiss();
                mDataBinding.shopWebsiteText.setPaintFlags(mDataBinding.shopWebsiteText.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
                mDataBinding.shopWebsiteText.setText(s);
                mDataBinding.shopWebsiteText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
                        startActivity(browserIntent);
                    }
                });

            }

            @Override
            public void onEmpty() {
                mDataBinding.shopWebsiteText.setText("Website not Added");
                loading.dismiss();
            }

            @Override
            public void onFailure(String e) {
                mDataBinding.shopWebsiteText.setText("Error "+e);
                loading.dismiss();
            }
        });


        return mDataBinding.getRoot();
    }
}