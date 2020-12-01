package com.codecoy.caribbean.listeners;

import com.codecoy.caribbean.data_model.ShopCategoryModel;

public interface OnCategoryLoadListeners {
    public void onCategoriesLoaded(ShopCategoryModel shopCategoryModel);
    public void onEmpty();
    public void onFailure(String e);
}
