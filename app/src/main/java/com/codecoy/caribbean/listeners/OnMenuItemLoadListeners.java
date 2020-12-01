package com.codecoy.caribbean.listeners;


import com.codecoy.caribbean.data_model.MenuItem;

public interface OnMenuItemLoadListeners {
    public void onMenuLoaded(MenuItem itemList);
    public void onEmpty();
    public void onFailure(String e);
}
