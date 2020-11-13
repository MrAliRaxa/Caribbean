package com.codecoy.caribbean.adaptor.recycler_adaptor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.databinding.SliderLayoutBinding;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdaptor extends SliderViewAdapter<SliderAdaptor.SliderAdapterVH> {

    private Context context;
    private  List<String> mSliderItems;

    private static final String TAG = "SliderAdaptor";
    public SliderAdaptor(Context context, List<String> mSliderItems) {
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
        Glide.with(context).load(mSliderItems.get(position)).placeholder(R.drawable.loading_image_spinner).into(viewHolder.sliderLayoutBinding.sliderLayoutImage);
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
