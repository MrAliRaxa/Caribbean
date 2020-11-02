package com.codecoy.caribbean.dataModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SliderItem {


    @NonNull
    private String imageUrl;

    @NonNull
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}