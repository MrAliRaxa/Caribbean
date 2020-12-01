package com.codecoy.caribbean.listeners;

import com.codecoy.caribbean.data_model.ShopInformationModel;

public interface OnInformationLoadListeners {
    public void onInformationLoaded(ShopInformationModel shopInformationModel);
    public void onNotFound();
    public void onError(String e);
}
