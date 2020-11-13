package com.codecoy.caribbean.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.dataModel.Shop;
import com.codecoy.caribbean.databinding.ActivityShopViewBinding;
import com.codecoy.caribbean.databinding.ActivityShopsViewerBinding;

public class ShopView extends AppCompatActivity {

    private Shop shop;
    private ActivityShopViewBinding mDataBinding;
    private static final String TAG = "ShopView";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shop_view);
        mDataBinding= DataBindingUtil.setContentView(ShopView.this,R.layout.activity_shop_view);
        if(getIntent()!=null){
            shop=getIntent().getParcelableExtra("shop");
            Log.d(TAG, "onCreate: "+shop.getName());
            Glide.with(ShopView.this).load(shop.getLogoUrl()).placeholder(R.drawable.loading_image_spinner).into(mDataBinding.shopViewShopImage);
            Glide.with(ShopView.this).load(shop.getBannerUrl()).placeholder(R.drawable.loading_image_spinner).into(mDataBinding.shopViewShopBanner);
            mDataBinding.shopViewTitle.setText(shop.getName());
            mDataBinding.shopViewCallCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ContextCompat.checkSelfPermission(ShopView.this,Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(ShopView.this,new String[]{Manifest.permission.CALL_PHONE},1);
                    }else {
                        makeCall(shop.getContact());
                    }
                }
            });
        }
    }

    private void makeCall(String number){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+number));//change the number
        startActivity(callIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                makeCall(shop.getContact());
            }
        }
    }
}