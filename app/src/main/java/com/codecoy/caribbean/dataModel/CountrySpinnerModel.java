package com.codecoy.caribbean.dataModel;

import android.graphics.drawable.Drawable;

public class CountrySpinnerModel {
    private String countryName;
    private Drawable drawable;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
