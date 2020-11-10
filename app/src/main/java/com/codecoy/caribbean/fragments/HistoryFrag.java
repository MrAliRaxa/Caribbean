package com.codecoy.caribbean.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codecoy.caribbean.R;
import com.codecoy.caribbean.databinding.FragmentHistoryBinding;


public class HistoryFrag extends Fragment {


    public HistoryFrag() {
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

        FragmentHistoryBinding historyBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_history,container,false);

        historyBinding.countryIntroHistory.setText(getArguments().getString("history"));

        return historyBinding.getRoot();
    }
}