package com.codecoy.caribbean.listeners;

import com.codecoy.caribbean.dataModel.ShopCategoryModel;

import java.util.List;

public interface OnCategoryLoadListeners {
    public void onCategoriesLoaded(ShopCategoryModel shopCategoryModel);
    public void onEmpty();
    public void onFailure(String e);
}
