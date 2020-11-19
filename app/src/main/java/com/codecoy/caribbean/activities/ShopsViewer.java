package com.codecoy.caribbean.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.adaptor.maps_adaptor.InfoWindowAdaptor;
import com.codecoy.caribbean.adaptor.recycler_adaptor.ShopAdaptor;
import com.codecoy.caribbean.adaptor.recycler_adaptor.SliderAdaptor;
import com.codecoy.caribbean.constants.SliderType;
import com.codecoy.caribbean.dataModel.Shop;
import com.codecoy.caribbean.dataModel.ShopCategoryModel;
import com.codecoy.caribbean.dataModel.SliderContent;
import com.codecoy.caribbean.databinding.ActivityShopsViewerBinding;
import com.codecoy.caribbean.databinding.MarkerSnippetLayoutBinding;
import com.codecoy.caribbean.listeners.OnCategoriesLoadListeners;
import com.codecoy.caribbean.listeners.OnShopClick;
import com.codecoy.caribbean.listeners.OnShopLoadListeners;
import com.codecoy.caribbean.listeners.OnSliderLoadListeners;
import com.codecoy.caribbean.repository.Repository;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class ShopsViewer extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private CardView _1kmView;
    private CardView _3kmView;
    private CardView _6kmView;
    private SliderView sliderView;
    private static final String TAG = "ShopsViewer";
    private ActivityShopsViewerBinding mDataBinding;
    private String categoryId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent()!=null){
            categoryId=getIntent().getStringExtra("categoryId");
        }
        mDataBinding= DataBindingUtil.setContentView(ShopsViewer.this,R.layout.activity_shops_viewer);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.explorer_tourism_map);
        mapFragment.getMapAsync(ShopsViewer.this);


        sliderView=mDataBinding.explorerTourismSlider;
        _1kmView=mDataBinding.oneKm;
        _3kmView=mDataBinding.threeKm;
        _6kmView=mDataBinding.sixKm;
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mDataBinding.explorerTourismCategories.setLayoutManager(linearLayoutManager);
        Repository.getShopSlider(new OnSliderLoadListeners() {
            @Override
            public void onSliderLoaded(SliderContent sliderContent) {
                if(sliderContent.getSliderType()== SliderType.IMAGE_SLIDER||sliderContent.getSliderType()==0){
                    sliderView.setSliderAdapter(new SliderAdaptor(getContext(), sliderContent.getSliderContent()));
                    sliderView.startAutoCycle();
                }
            }

            @Override
            public void onNotFound() {
                Toast.makeText(getContext(), "Slider Content Not Found", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String e) {
                Toast.makeText(getContext(), "Error "+e, Toast.LENGTH_SHORT).show();
            }
        });

        Repository.getCategoriesShops(categoryId,new OnShopLoadListeners() {
            @Override
            public void onShopsLoaded(List<Shop> shops) {


                ShopAdaptor shopAdaptor=new ShopAdaptor(getContext(), shops, new OnShopClick() {
                    @Override
                    public void onClick(LatLng pos, int index) {
                        moveCamera(pos,1,mMap);
                        Log.d(TAG, "onClick: ");
                    }
                });

                mDataBinding.explorerTourismCategories.setAdapter(shopAdaptor);

                if(mMap!=null){

                    for(Shop shop:shops){
                        LatLng latLng=new LatLng(shop.getLat(),shop.getLng());
                        MarkerOptions markerOptions=new MarkerOptions();
                        markerOptions.title(shop.getName());
                        markerOptions.snippet(shop.getId());
                        markerOptions.position(latLng);
                        Bitmap highQualityMarker= BitmapFactory.decodeResource(getResources(),R.drawable.marker_two_copy);
                        Bitmap lowQualityMarker=Bitmap.createScaledBitmap(highQualityMarker,160,190,false);
                        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(lowQualityMarker));

                        Glide.with(getContext())
                                .asBitmap()
                                .load(shop.getLogoUrl())
                                .override(120,120)
                                .circleCrop()
                                .placeholder(R.drawable.loading_image_spinner)
                                .into(new CustomTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        Log.d(TAG, "onResourceReady: ");
                                        mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromBitmap(resource)).anchor(.5f,1.4f));
                                        moveCamera(latLng,6,mMap);
                                    }

                                    @Override
                                    public void onLoadCleared(@Nullable Drawable placeholder) {

                                    }
                                });


                        mMap.addMarker(markerOptions);
                        mMap.getUiSettings().setMapToolbarEnabled(false);
                        mMap.setInfoWindowAdapter(new InfoWindowAdaptor(ShopsViewer.this,shop));
                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                Intent intent=new Intent(getContext(),ShopView.class);
                                intent.putExtra("id",marker.getSnippet().trim());
                                Log.d(TAG, "onInfoWindowClick: "+marker.getSnippet());
                                startActivity(intent);
                            }
                        });




                    }
                }
            }

            @Override
            public void onEmpty() {

            }

            @Override
            public void onFailure(String e) {

            }
        });
    }

    private Context getContext() {
        return ShopsViewer.this;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(false);
        _6kmView.setOnClickListener(v->{
            moveCamera(mMap.getCameraPosition().target,6,mMap);
        });
        _3kmView.setOnClickListener(v->{
            moveCamera(mMap.getCameraPosition().target,3,mMap);
        });
        _1kmView.setOnClickListener(v->{
            moveCamera(mMap.getCameraPosition().target,1,mMap);
        });
    }
    public int getZoomLevel(Circle circle) {
        int zoomLevel = 0;
        if (circle != null){
            double radius = circle.getRadius();
            double scale = radius / 500;
            zoomLevel =(int) (16 - Math.log(scale) / Math.log(2));
        }
        return zoomLevel;
    }

    private void moveCamera(LatLng pos,int kmDistance,GoogleMap googleMap){
        Circle circle = googleMap.addCircle(new CircleOptions().center(pos).radius(kmDistance*1000).strokeColor(Color.RED));
        circle.setVisible(false);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos,getZoomLevel(circle)));
    }
}