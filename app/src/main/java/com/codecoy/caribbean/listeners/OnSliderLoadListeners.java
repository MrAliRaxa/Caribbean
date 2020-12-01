package com.codecoy.caribbean.listeners;

import com.codecoy.caribbean.data_model.SliderContent;

public interface OnSliderLoadListeners {
    public void onSliderLoaded(SliderContent sliderContent);
    public void onNotFound();
    public void onFailure(String e);
}
