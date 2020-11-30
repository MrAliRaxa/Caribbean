package com.codecoy.caribbean.listeners;

import com.codecoy.caribbean.dataModel.ShopInformationModel;

public interface OnInformationLoadListeners {
    public void onInformationLoaded(ShopInformationModel shopInformationModel);
    public void onNotFound();
    public void onError(String e);
}
