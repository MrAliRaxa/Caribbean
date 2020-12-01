package com.codecoy.caribbean.listeners;

import com.codecoy.caribbean.data_model.Shop;

public interface OnSingleShopLoadListeners {
    public void onShopLoaded(Shop shop);
    public void onEmpty();
    public void onFailure(String e);
}
