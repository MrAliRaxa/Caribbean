package com.codecoy.caribbean.listeners;


import com.codecoy.caribbean.data_model.UserProfile;

public interface OnUserProfileLoadListeners {
    public void onUserProfileLoaded(UserProfile userProfile);
    public void onFailure(String e);
}
