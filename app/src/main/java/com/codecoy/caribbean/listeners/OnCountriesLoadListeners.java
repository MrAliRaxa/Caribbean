package com.codecoy.caribbean.listeners;

import com.codecoy.caribbean.dataModel.Country;

import java.util.List;

public interface OnCountriesLoadListeners {

    public void onCountriesLoaded(List<Country> countries);
    public void onEmpty();
    public void onFailure(String e);
}
