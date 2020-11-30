package com.codecoy.caribbean.listeners;


import com.codecoy.caribbean.dataModel.MenuItem;

public interface OnMenuItemLoadListeners {
    public void onMenuLoaded(MenuItem itemList);
    public void onEmpty();
    public void onFailure(String e);
}
