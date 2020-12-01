package com.codecoy.caribbean.listeners;



import com.codecoy.caribbean.data_model.ShopLocation;

import java.util.List;

public interface OnShopLocationLoadListeners {
    public void onLocationsLoaded(List<ShopLocation> locationList);
    public void onEmpty();
    public void onFailure(String e);
}
