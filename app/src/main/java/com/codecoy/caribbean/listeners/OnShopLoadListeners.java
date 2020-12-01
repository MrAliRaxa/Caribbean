package com.codecoy.caribbean.listeners;

import com.codecoy.caribbean.data_model.Shop;

import java.util.List;

public interface OnShopLoadListeners {
    public void onShopsLoaded(List<Shop> shops);
    public void onEmpty();
    public void onFailure(String e);
}
