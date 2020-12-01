package com.codecoy.caribbean.listeners;

public interface OnStringLoadListeners {
    public void onStringLoaded(String s);
    public void onEmpty();
    public void onFailure(String e);
}
