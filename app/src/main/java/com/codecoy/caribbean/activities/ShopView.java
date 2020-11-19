package com.codecoy.caribbean.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.constants.ShopType;
import com.codecoy.caribbean.dataModel.Shop;
import com.codecoy.caribbean.dataModel.ShopCategoryModel;
import com.codecoy.caribbean.databinding.ActivityShopViewBinding;
import com.codecoy.caribbean.fragments.shop_view.ATMLocations;
import com.codecoy.caribbean.fragments.shop_view.Activities;
import com.codecoy.caribbean.fragments.shop_view.Attractions;
import com.codecoy.caribbean.fragments.shop_view.BankLocations;
import com.codecoy.caribbean.fragments.shop_view.Buildings;
import com.codecoy.caribbean.fragments.shop_view.DealsAndPromotions;
import com.codecoy.caribbean.fragments.shop_view.Directory;
import com.codecoy.caribbean.fragments.shop_view.East;
import com.codecoy.caribbean.fragments.shop_view.HistoricalSites;
import com.codecoy.caribbean.fragments.shop_view.Information;
import com.codecoy.caribbean.fragments.shop_view.Location;
import com.codecoy.caribbean.fragments.shop_view.North;
import com.codecoy.caribbean.fragments.shop_view.Prices;
import com.codecoy.caribbean.fragments.shop_view.ShopMenu;
import com.codecoy.caribbean.fragments.shop_view.ShopWebsite;
import com.codecoy.caribbean.fragments.shop_view.Showroom;
import com.codecoy.caribbean.fragments.shop_view.South;
import com.codecoy.caribbean.fragments.shop_view.Stores;
import com.codecoy.caribbean.fragments.shop_view.Tobago;
import com.codecoy.caribbean.fragments.shop_view.West;
import com.codecoy.caribbean.fragments.shop_view.WildLife;
import com.codecoy.caribbean.listeners.OnCategoryLoadListeners;
import com.codecoy.caribbean.listeners.OnSingleShopLoadListeners;
import com.codecoy.caribbean.repository.Repository;
import com.codecoy.caribbean.util.DialogBuilder;

public class ShopView extends AppCompatActivity {

    private String shopId;
    private Shop shopGlobal;
    private ActivityShopViewBinding mDataBinding;
    private static final String TAG = "ShopView";
    private CardView dealsAndPromostions;
    private CardView locations;
    private CardView shopMenu;
    private CardView information;
    private CardView showRoom;
    private CardView prices;
    private CardView directory;
    private CardView stores;
    private CardView website;
    private CardView atmLocations;
    private CardView bankLocations;
    private CardView activities;
    private CardView attractions;
    private CardView buildings;
    private CardView historicalSites;
    private CardView wildlife;
    private CardView north;
    private CardView south;
    private CardView east;
    private CardView west;
    private CardView tobago;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shop_view);
        mDataBinding= DataBindingUtil.setContentView(ShopView.this,R.layout.activity_shop_view);
        dealsAndPromostions=mDataBinding.shopViewDealsAndPromotions;
        locations=mDataBinding.shopViewLocations;
        shopMenu=mDataBinding.shopViewMenu;
        information=mDataBinding.shopViewInformation;
        wildlife=mDataBinding.shopViewWildLife;
        showRoom=mDataBinding.shopViewShowroom;
        prices=mDataBinding.shopViewPrices;
        directory=mDataBinding.shopViewDirectory;
        stores=mDataBinding.shopViewStores;
        website=mDataBinding.shopViewWebsites;
        atmLocations=mDataBinding.shopViewAtmLocation;
        bankLocations=mDataBinding.shopViewBankLocation;
        activities=mDataBinding.shopViewActivites;
        attractions=mDataBinding.shopViewAttractions;
        buildings=mDataBinding.shopViewBuilding;
        historicalSites=mDataBinding.shopViewHistoricalSite;
        north=mDataBinding.shopViewNorth;
        south=mDataBinding.shopViewSouth;
        east=mDataBinding.shopViewEast;
        west=mDataBinding.shopViewWest;
        tobago=mDataBinding.shopViewTobago;

        if(getIntent()!=null){

            shopId=getIntent().getStringExtra("id");
            Log.d(TAG, "onCreate: ");
            ProgressDialog progressDialog= DialogBuilder.getSimpleLoadingDialog(ShopView.this,"Loading","Waiting for server response");
            progressDialog.show();
            Repository.getShop(shopId, new OnSingleShopLoadListeners() {
                @Override
                public void onShopLoaded(Shop shop) {
                    progressDialog.dismiss();
                    shopGlobal=shop;
                    Glide.with(ShopView.this).load(shop.getLogoUrl()).placeholder(R.drawable.loading_dialoge).into(mDataBinding.shopViewShopImage);
                    Glide.with(ShopView.this).load(shop.getBannerUrl()).placeholder(R.drawable.loading_dialoge).into(mDataBinding.shopViewShopBanner);
                    mDataBinding.shopViewTitle.setText(shop.getName());
                    Log.d(TAG, "onShopLoaded:"+shop.getCategoryId());
                    Repository.getShopCategory(shop.getCategoryId(), new OnCategoryLoadListeners() {
                        @Override
                        public void onCategoriesLoaded(ShopCategoryModel shopCategoryModel) {
                            validateShopView(shopCategoryModel.getViewType());
                            setLayoutBtns();
                        }

                        @Override
                        public void onEmpty() {
                            Toast.makeText(ShopView.this, "Empty", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(String e) {

                        }
                    });
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

                @Override
                public void onEmpty() {
                    Toast.makeText(ShopView.this, "Shop Not Exist", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(String e) {
                    Toast.makeText(ShopView.this, "Error "+e, Toast.LENGTH_SHORT).show();
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
                makeCall(shopGlobal.getContact());
            }
        }



    }
    private void validateShopView(int layoutType){

        if(layoutType==ShopType.DMLI){
            dealsAndPromostions.setVisibility(View.VISIBLE);
            shopMenu.setVisibility(View.VISIBLE);
            locations.setVisibility(View.VISIBLE);
            information.setVisibility(View.VISIBLE);
            replaceFragment(new DealsAndPromotions());
        }else if(layoutType==ShopType.AABHW){
            activities.setVisibility(View.VISIBLE);
            attractions.setVisibility(View.VISIBLE);
            historicalSites.setVisibility(View.VISIBLE);
            buildings.setVisibility(View.VISIBLE);
            wildlife.setVisibility(View.VISIBLE);
        }else if(layoutType==ShopType.DDSI){
            dealsAndPromostions.setVisibility(View.VISIBLE);
            directory.setVisibility(View.VISIBLE);
            stores.setVisibility(View.VISIBLE);
            information.setVisibility(View.VISIBLE);
        }else if(layoutType==ShopType.DS){
            directory.setVisibility(View.VISIBLE);
            stores.setVisibility(View.VISIBLE);
        }else if(layoutType==ShopType.DSLI){
            dealsAndPromostions.setVisibility(View.VISIBLE);
            showRoom.setVisibility(View.VISIBLE);
            locations.setVisibility(View.VISIBLE);
            information.setVisibility(View.VISIBLE);
        }else if(layoutType==ShopType.NSEWT){
            north.setVisibility(View.VISIBLE);
            south.setVisibility(View.VISIBLE);
            east.setVisibility(View.VISIBLE);
            west.setVisibility(View.VISIBLE);
            tobago.setVisibility(View.VISIBLE);
        }else if(layoutType==ShopType.PSL){
            prices.setVisibility(View.VISIBLE);
            showRoom.setVisibility(View.VISIBLE);
            locations.setVisibility(View.VISIBLE);
            information.setVisibility(View.VISIBLE);
        }else if(layoutType==ShopType.SL){
            showRoom.setVisibility(View.VISIBLE);
            locations.setVisibility(View.VISIBLE);
        }else if(layoutType==ShopType.WABI){
            website.setVisibility(View.VISIBLE);
            atmLocations.setVisibility(View.VISIBLE);
            bankLocations.setVisibility(View.VISIBLE);
            information.setVisibility(View.VISIBLE);
        }
    }

    private void setLayoutBtns(){
        dealsAndPromostions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new DealsAndPromotions());
            }
        });

        locations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Location());
            }
        });
        shopMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ShopMenu());
            }
        });
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Information());
            }
        });
        wildlife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new WildLife());
            }
        });
        dealsAndPromostions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new DealsAndPromotions());
            }
        });
        showRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Showroom());
            }
        });
        prices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Prices());
            }
        });
        directory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Directory());
            }
        });
        stores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Stores());
            }
        });
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ShopWebsite());
            }
        });
        atmLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ATMLocations());
            }
        });
        bankLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new BankLocations());
            }
        });

        atmLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ATMLocations());
            }
        });
        activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Activities());
            }
        });
        attractions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Attractions());
            }
        });

        buildings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Buildings());
            }
        });
        historicalSites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new HistoricalSites());
            }
        });

        north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new North());
            }
        });

        south.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new South());
            }
        });
        east.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new East());
            }
        });
        west.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new West());
            }
        });

        tobago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Tobago());
            }
        });
    }
    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.shopView_fragmentContainer,fragment)
                .commit();
    }
}