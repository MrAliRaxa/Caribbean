package com.codecoy.caribbean.adaptor.maps_adaptor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.codecoy.caribbean.R;
import com.codecoy.caribbean.dataModel.Shop;
import com.codecoy.caribbean.databinding.MarkerSnippetLayoutBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class InfoWindowAdaptor implements GoogleMap.InfoWindowAdapter {

    private Context context;
    private Shop shop;
    private static final String TAG = "InfoWindowAdaptor";

    public InfoWindowAdaptor(Context context, Shop shop) {
        this.context = context;
        this.shop = shop;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        MarkerSnippetLayoutBinding snippetLayoutBinding= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.marker_snippet_layout,null,false);
        snippetLayoutBinding.snippetTitle.setText(marker.getTitle());
        snippetLayoutBinding.snippetDes.setText(marker.getSnippet());
        return snippetLayoutBinding.getRoot();
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

}
