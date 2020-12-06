package com.codecoy.caribbean.listeners;

import com.codecoy.caribbean.data_model.ShopCategoryModel;
import com.google.android.gms.maps.model.LatLng;

public interface OnCategoryClick {
    public void onClick(ShopCategoryModel shopCategoryModel, int index);
}
