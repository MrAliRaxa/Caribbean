package com.codecoy.caribbean.Listeners;


import com.codecoy.caribbean.dataModel.UserProfile;

public interface OnUserProfileLoadListeners {
    public void onUserProfileLoaded(UserProfile userProfile);
    public void onFailure(String e);
}
