package com.codecoy.caribbean.Adaptor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.codecoy.caribbean.DataModel.SliderItem;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.databinding.SliderLayoutBinding;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdaptor extends SliderViewAdapter<SliderAdaptor.SliderAdapterVH> {

    private Context context;
    private  List<SliderItem> mSliderItems;

    private static final String TAG = "SliderAdaptor";
    public SliderAdaptor(Context context, List<SliderItem> mSliderItems) {
        this.context = context;
        this.mSliderItems = mSliderItems;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        SliderLayoutBinding sliderLayoutBinding= DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.slider_layout
        ,parent,false);
        return new SliderAdapterVH(sliderLayoutBinding);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: "+mSliderItems.get(position).getImageUrl());
        Glide.with(context).load(mSliderItems.get(position).getImageUrl()).into(viewHolder.sliderLayoutBinding.sliderLayoutImage);
    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder{

        private SliderLayoutBinding sliderLayoutBinding;
        public SliderAdapterVH(SliderLayoutBinding sliderLayoutBinding) {
            super(sliderLayoutBinding.getRoot());
            this.sliderLayoutBinding=sliderLayoutBinding;
        }
    }
}
