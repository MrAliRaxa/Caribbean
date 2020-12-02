package com.codecoy.caribbean.fragments.shop_view;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codecoy.caribbean.R;
import com.codecoy.caribbean.adaptor.recycler_adaptor.LocationAdaptor;
import com.codecoy.caribbean.data_model.ATM;
import com.codecoy.caribbean.data_model.Shop;
import com.codecoy.caribbean.data_model.ShopLocation;
import com.codecoy.caribbean.database_controller.DatabaseAddresses;
import com.codecoy.caribbean.databinding.FragmentBankLocationsBinding;
import com.codecoy.caribbean.listeners.OnShopClick;
import com.codecoy.caribbean.listeners.OnShopLocationLoadListeners;
import com.codecoy.caribbean.repository.Repository;
import com.codecoy.caribbean.util.DialogBuilder;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class BankLocations extends Fragment {

    private Shop shop;
    private GoogleMap mMap;
    private FragmentBankLocationsBinding mDataBinding;
    public BankLocations() {
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
         mDataBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_bank_locations, container, false);
        ProgressDialog loading= DialogBuilder.getSimpleLoadingDialog(getContext(),"Loading","Please wait for server response . . .");
        loading.setCanceledOnTouchOutside(false);
        loading.show();

//        FragmentATMLocationsBinding mDataBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_a_t_m_locations, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.location_map);
      //  SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.location_map);
        mapFragment.getMapAsync(googleMap -> {
            mMap=googleMap;
            if(mMap!=null){

                Repository.getLocations(shop.getId(), DatabaseAddresses.getBankCollection(), new OnShopLocationLoadListeners() {
                    @Override
                    public void onLocationsLoaded(List<ShopLocation> locationList) {


                        for(ShopLocation shopLocation:locationList){
                            mMap.addMarker(new MarkerOptions().position(new LatLng(shopLocation.getLat(),shopLocation.getLng())));
                        }

                        LocationAdaptor locationAdaptor =new LocationAdaptor(getContext(), locationList, new OnShopClick() {
                            @Override
                            public void onClick(LatLng pos, int index) {
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos,15));

                            }
                        });
                        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                        mDataBinding.bankLocationRecyclerView.setLayoutManager(layoutManager);
                        mDataBinding.bankLocationRecyclerView.setAdapter(locationAdaptor);
                        loading.dismiss();
                    }

                    @Override
                    public void onEmpty() {
                        loading.dismiss();
                    }

                    @Override
                    public void onFailure(String e) {
                        loading.dismiss();
                    }
                });


            }
        });





        return mDataBinding.getRoot();
    }
}