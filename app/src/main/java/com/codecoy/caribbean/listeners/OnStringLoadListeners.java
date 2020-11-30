package com.codecoy.caribbean.listeners;

public interface OnStringLoadListeners {
    public void onStringLoaded(String s);
    public void onNotFound();
    public void onFailure(String e);
}
