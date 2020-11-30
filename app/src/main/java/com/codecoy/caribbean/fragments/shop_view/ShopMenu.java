package com.codecoy.caribbean.fragments.shop_view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.dataModel.MenuItem;
import com.codecoy.caribbean.dataModel.Shop;
import com.codecoy.caribbean.databinding.FragmentShopMenuBinding;
import com.codecoy.caribbean.listeners.OnMenuItemLoadListeners;
import com.codecoy.caribbean.listeners.OnStringLoadListeners;
import com.codecoy.caribbean.repository.Repository;

public class ShopMenu extends Fragment {


    private Shop shop;
    public ShopMenu() {
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
        FragmentShopMenuBinding mDataBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_shop_menu, container, false);

        Repository.getShopMenu(shop.getId(), new OnMenuItemLoadListeners() {
            @Override
            public void onMenuLoaded(MenuItem itemList) {
                Glide.with(getContext()).load(itemList.getImageUri()).into(mDataBinding.shopMenuMenuImage);

            }

            @Override
            public void onEmpty() {
                mDataBinding.shopMenuMsg.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFailure(String e) {
                mDataBinding.shopMenuMsg.setVisibility(View.VISIBLE);
                mDataBinding.shopMenuMsg.setText("Error "+e);
            }
        });
        return mDataBinding.getRoot();
    }
}