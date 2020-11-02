package com.codecoy.caribbean.dataModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Delicacies implements Parcelable {

    private List<String> sliderContent;
    private String description;
    private int sliderType;

    public Delicacies() {
        sliderContent =new ArrayList<>();
    }


    protected Delicacies(Parcel in) {
        sliderContent = in.createStringArrayList();
        description = in.readString();
        sliderType = in.readInt();
    }

    public static final Creator<Delicacies> CREATOR = new Creator<Delicacies>() {
        @Override
        public Delicacies createFromParcel(Parcel in) {
            return new Delicacies(in);
        }

        @Override
        public Delicacies[] newArray(int size) {
            return new Delicacies[size];
        }
    };

    public int getSliderType() {
        return sliderType;
    }

    public void setSliderType(int sliderType) {
        this.sliderType = sliderType;
    }


    public List<String> getSliderContent() {
        return sliderContent;
    }

    public void setSliderContent(List<String> sliderContent) {
        this.sliderContent = sliderContent;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(sliderContent);
        dest.writeString(description);
        dest.writeInt(sliderType);
    }
}