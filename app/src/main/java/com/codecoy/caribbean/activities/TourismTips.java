package com.codecoy.caribbean.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codecoy.caribbean.R;
import com.codecoy.caribbean.constants.ShopBoundry;
import com.codecoy.caribbean.databinding.ActivityTourismTipsBinding;

public class TourismTips extends AppCompatActivity {

    private ActivityTourismTipsBinding tipsBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tipsBinding= DataBindingUtil.setContentView(TourismTips.this,R.layout.activity_tourism_tips);
        tipsBinding.touristTipsNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TourismTips.this,ExplorerTourism.class);
                intent.putExtra("visitor", getIntent().getIntExtra("visitor",0));
                startActivity(intent);
            }
        });
    }
}