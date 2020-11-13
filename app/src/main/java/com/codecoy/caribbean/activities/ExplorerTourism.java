package com.codecoy.caribbean.activities;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.codecoy.caribbean.adaptor.recycler_adaptor.CategoriesAdaptor;
import com.codecoy.caribbean.adaptor.recycler_adaptor.SliderAdaptor;
import com.codecoy.caribbean.constants.SliderType;
import com.codecoy.caribbean.dataModel.Shop;
import com.codecoy.caribbean.dataModel.ShopCategoryModel;
import com.codecoy.caribbean.databinding.ActivityExplorerTourismBinding;
import com.codecoy.caribbean.databinding.MarkerSnippetLayoutBinding;
import com.codecoy.caribbean.listeners.OnCategoriesLoadListeners;
import com.codecoy.caribbean.listeners.OnShopLoadListeners;
import com.codecoy.caribbean.listeners.OnSliderLoadListeners;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.repository.Repository;
import com.codecoy.caribbean.dataModel.SliderContent;

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
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class ExplorerTourism extends AppCompatActivity implements OnMapReadyCallback {

    private SliderView sliderView;
    private ActivityExplorerTourismBinding mDataBinding;
    private static final String TAG = "ExplorerTourism";
    private GoogleMap mMap;
    private CardView _1kmView;
    private CardView _3kmView;
    private CardView _6kmView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding= DataBindingUtil.setContentView(ExplorerTourism.this,R.layout.activity_explorer_tourism);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.explorer_tourism_map);
        mapFragment.getMapAsync(this);


        sliderView=mDataBinding.explorerTourismSlider;
        _1kmView=mDataBinding.oneKm;
        _3kmView=mDataBinding.threeKm;
        _6kmView=mDataBinding.sixKm;




        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mDataBinding.explorerTourismCategories.setLayoutManager(linearLayoutManager);




        Repository.getShopCategoriesSlider(new OnSliderLoadListeners() {
            @Override
            public void onSliderLoaded(SliderContent sliderContent) {
                Log.d(TAG, "onSliderLoaded: "+sliderContent.getSliderContent().toString());
                if(sliderContent.getSliderType()== SliderType.IMAGE_SLIDER||sliderContent.getSliderType()==0){
                    sliderView.setSliderAdapter(new SliderAdaptor(getContext(), sliderContent.getSliderContent()));
                    sliderView.setAutoCycle(true);
                    sliderView.startAutoCycle();
                    sliderView.setIndicatorAnimation(IndicatorAnimationType.SCALE_DOWN);
                }
            }

            @Override
            public void onNotFound() {
                Toast.makeText(ExplorerTourism.this, "Slider Content Not Found", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String e) {
                Toast.makeText(ExplorerTourism.this, "Error "+e, Toast.LENGTH_SHORT).show();
            }
        });

        Repository.getCategories(new OnCategoriesLoadListeners() {
            @Override
            public void onCategoriesLoaded(List<ShopCategoryModel> categoryModels) {
                CategoriesAdaptor categoriesAdaptor=new CategoriesAdaptor(getContext(),categoryModels);
                mDataBinding.explorerTourismCategories.setAdapter(categoriesAdaptor);

            }

            @Override
            public void onEmpty() {
                Toast.makeText(ExplorerTourism.this, "Empty", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String e) {
                Toast.makeText(ExplorerTourism.this, "Error "+e, Toast.LENGTH_SHORT).show();

            }
        });

        Repository.getAllShops(new OnShopLoadListeners() {
            @Override
            public void onShopsLoaded(List<Shop> shops) {

                mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                    @Override
                    public View getInfoWindow(Marker marker) {
                        MarkerSnippetLayoutBinding snippetLayoutBinding=DataBindingUtil.inflate(getLayoutInflater(),R.layout.marker_snippet_layout,null,false);
                        snippetLayoutBinding.snippetTitle.setText(marker.getTitle());
                        snippetLayoutBinding.snippetDes.setText(marker.getSnippet());
                        return snippetLayoutBinding.getRoot();
                    }

                    @Override
                    public View getInfoContents(Marker marker) {

                        return null;
                    }
                });
                if(mMap!=null){

                    for(Shop shop:shops){
                        LatLng latLng=new LatLng(shop.getLat(),shop.getLng());
                        MarkerOptions markerOptions=new MarkerOptions();
                        markerOptions.title(shop.getName());
                        markerOptions.snippet("Description will be there is shop add it");
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
                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                Intent intent=new Intent(getContext(),ShopView.class);
                                intent.putExtra("shop",shop);
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

    private Context getContext(){
        return ExplorerTourism.this;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
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