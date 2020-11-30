package com.codecoy.caribbean.listeners;

import com.codecoy.caribbean.dataModel.Item;

import java.util.List;

public interface OnItemLoadListeners {
    public void onItemLoaded(List<Item> itemList);
    public void onEmpty();
    public void onFailure(String e);
}
