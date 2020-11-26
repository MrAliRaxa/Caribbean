package com.codecoy.caribbean.fragments.shop_view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.airbnb.lottie.L;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.activities.ShopsViewer;
import com.codecoy.caribbean.adaptor.recycler_adaptor.ATMAdaptor;
import com.codecoy.caribbean.dataModel.ATM;
import com.codecoy.caribbean.databinding.FragmentATMLocationsBinding;
import com.codecoy.caribbean.listeners.OnShopClick;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ATMLocations extends Fragment {

    private GoogleMap mMap;
    public ATMLocations() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentATMLocationsBinding mDataBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_a_t_m_locations, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.location_map);
        mapFragment.getMapAsync(googleMap -> {
            mMap=googleMap;
            if(mMap!=null){

                List<ATM> atms=new ArrayList<>();
                for(int i=0;i<40;i++){

                    ATM atm=new ATM();
                    atm.setId(String.valueOf(Calendar.getInstance().getTimeInMillis()));
                    atm.setAtmName("ATM "+i);
                    atm.setLat(1.23489+i);
                    atm.setLng(2.23445+i);
                    atm.setAtmImage("https://images.livemint.com/rf/Image-621x414/LiveMint/Period2/2018/05/19/Photos/Processed/atms-kFAB--621x414@LiveMint.jpg");
                    atms.add(atm);

                    mMap.addMarker(new MarkerOptions().position(new LatLng(atm.getLat(),atm.getLng())));
                }

                ATMAdaptor atmAdaptor=new ATMAdaptor(getContext(), atms, new OnShopClick() {
                    @Override
                    public void onClick(LatLng pos, int index) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos,15));
                    }
                });
                LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                mDataBinding.atmLocationRecyclerView.setLayoutManager(layoutManager);
                mDataBinding.atmLocationRecyclerView.setAdapter(atmAdaptor);

            }
        });





        return mDataBinding.getRoot();
    }


}