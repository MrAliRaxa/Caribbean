package com.codecoy.caribbean.fragments.shop_view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.data_model.MenuItem;
import com.codecoy.caribbean.data_model.Shop;
import com.codecoy.caribbean.databinding.FragmentDelicaciesBinding;
import com.codecoy.caribbean.databinding.FragmentDirectoryBinding;
import com.codecoy.caribbean.listeners.OnMenuItemLoadListeners;
import com.codecoy.caribbean.repository.Repository;


public class Directory extends Fragment {

    FragmentDirectoryBinding mDataBinding;
    private Shop shop;
    public Directory() {
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
            mDataBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_directory, container, false);
            Repository.getShopDirectory(shop.getId(), new OnMenuItemLoadListeners() {
                @Override
                public void onMenuLoaded(MenuItem itemList) {
                    Glide.with(getContext()).load(itemList.getImageUri()).into(mDataBinding.directoryImage);
                }

                @Override
                public void onEmpty() {
                    mDataBinding.directoryMsg.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(String e) {
                    mDataBinding.directoryMsg.setVisibility(View.GONE);
                    mDataBinding.directoryMsg.setText("Error "+e);

                }
            });
            return mDataBinding.getRoot();
    }
}