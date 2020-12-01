package com.codecoy.caribbean.listeners;

import com.codecoy.caribbean.data_model.Item;

import java.util.List;

public interface OnDealsLoadListeners {
    public void onDealLoaded(List<Item> item);
    public void onEmpty();
    public void onFailure(String e);
}
