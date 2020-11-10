package com.codecoy.caribbean.listeners;


import com.codecoy.caribbean.dataModel.UserProfile;

public interface OnUserProfileLoadListeners {
    public void onUserProfileLoaded(UserProfile userProfile);
    public void onFailure(String e);
}
