package com.codecoy.caribbean.Activities;


import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;

import com.codecoy.caribbean.Adaptor.SliderAdaptor;
import com.codecoy.caribbean.Constants.SliderType;
import com.codecoy.caribbean.Listeners.OnSliderLoadListeners;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.Repository.Repository;
import com.codecoy.caribbean.dataModel.TourismSlider;
import com.codecoy.caribbean.databinding.ActivityExplorerTourismBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jarvanmo.exoplayerview.media.SimpleMediaSource;
import com.jarvanmo.exoplayerview.ui.ExoVideoView;
import com.potyvideo.library.AndExoPlayerView;
import com.potyvideo.library.globalInterfaces.ExoPlayerCallBack;
import com.smarteist.autoimageslider.SliderView;

public class ExplorerTourism extends AppCompatActivity implements OnMapReadyCallback {

    private SliderView sliderView;
    private ExoVideoView videoView;
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
        videoView=mDataBinding.videoView;
        _1kmView=mDataBinding.oneKm;
        _3kmView=mDataBinding.threeKm;
        _6kmView=mDataBinding.sixKm;






        Repository.getMyTourismExplorerSlider(new OnSliderLoadListeners() {
            @Override
            public void onSliderLoaded(TourismSlider tourismSlider) {
                if(tourismSlider.getSliderType()== SliderType.VIDEO){
                    sliderView.setVisibility(View.GONE);
                    SimpleMediaSource mediaSource = new SimpleMediaSource(tourismSlider.getSliderContent().get(0));//uri also supported
                    videoView.play(mediaSource);

                }else{
                    videoView.setVisibility(View.GONE);
                    sliderView.setSliderAdapter(new SliderAdaptor(getContext(),tourismSlider.getSliderContent()));

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
        videoView.releasePlayer();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        moveCamera(1,mMap);

        _1kmView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveCamera(1,mMap);
            }
        });
        _3kmView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveCamera(3,mMap);
            }
        });
        _6kmView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveCamera(6,mMap);
            }
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

    private void moveCamera(int kmDistance,GoogleMap googleMap){
        LatLng sydney = new LatLng(-34, 151);

        Circle circle = googleMap.addCircle(new CircleOptions().center(sydney).radius(kmDistance*1000).strokeColor(Color.RED));
        circle.setVisible(false);

        googleMap.addMarker(new MarkerOptions().position(sydney).title("Location"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,getZoomLevel(circle)));
    }
}