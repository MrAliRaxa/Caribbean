package com.codecoy.caribbean.listeners;

import com.codecoy.caribbean.dataModel.Shop;

import java.util.List;

public interface OnSingleShopLoadListeners {
    public void onShopLoaded(Shop shop);
    public void onEmpty();
    public void onFailure(String e);
}
