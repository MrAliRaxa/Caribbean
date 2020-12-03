package com.codecoy.caribbean.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codecoy.caribbean.R;
import com.codecoy.caribbean.constants.ShopBoundry;
import com.codecoy.caribbean.databinding.ActivityTouristTypeSelectionBinding;

public class TouristTypeSelection extends AppCompatActivity {

    private ActivityTouristTypeSelectionBinding mDataBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_type_selection);
        mDataBinding= DataBindingUtil.setContentView(TouristTypeSelection.this,R.layout.activity_tourist_type_selection);


        mDataBinding.touristSelectionLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TouristTypeSelection.this,ExplorerTourism.class);
                intent.putExtra("visitor", ShopBoundry.LOCAL);
                startActivity(intent);

            }
        });


        mDataBinding.touristSelectionTourist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TouristTypeSelection.this,TourismTips.class);
                intent.putExtra("visitor", ShopBoundry.TOURIST);
                startActivity(intent);

            }
        });
    }
}