package com.codecoy.caribbean.data_model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class CountrySlider implements Parcelable {

    private List<String> sliderContent;
    private int sliderType;

    public CountrySlider() {

        sliderContent =new ArrayList<>();

    }

    protected CountrySlider(Parcel in) {
        sliderContent = in.createStringArrayList();
        sliderType = in.readInt();
    }

    public static final Creator<CountrySlider> CREATOR = new Creator<CountrySlider>() {
        @Override
        public CountrySlider createFromParcel(Parcel in) {
            return new CountrySlider(in);
        }

        @Override
        public CountrySlider[] newArray(int size) {
            return new CountrySlider[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(sliderContent);
        dest.writeInt(sliderType);
    }
}
