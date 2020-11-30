package com.codecoy.caribbean.listeners;



import com.codecoy.caribbean.dataModel.ShopLocation;

import java.util.List;

public interface OnShopLocationLoadListeners {
    public void onLocationsLoaded(List<ShopLocation> locationList);
    public void onEmpty();
    public void onFailure(String e);
}
