package com.codecoy.caribbean.fragments.shop_view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codecoy.caribbean.R;
import com.codecoy.caribbean.databinding.FragmentDelicaciesBinding;


public class Directory extends Fragment {

    FragmentDelicaciesBinding mDataBinding;
    public Directory() {
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
            mDataBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_directory, container, false);

            return mDataBinding.getRoot();
    }
}