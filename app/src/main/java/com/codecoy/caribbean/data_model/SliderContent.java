package com.codecoy.caribbean.data_model;

import java.util.ArrayList;
import java.util.List;

public class SliderContent {
    private List<String> sliderContent;
    private int sliderType;
    private String id;

    public SliderContent() {
        sliderContent=new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getSliderContent() {
        return sliderContent;
    }

    public void setSliderContent(List<String> sliderContent) {
        this.sliderContent = sliderContent;
    }

    public int getSliderType() {
        return sliderType;
    }

    public void setSliderType(int sliderType) {
        this.sliderType = sliderType;
    }
}
