package com.codecoy.caribbean.util;


import com.codecoy.caribbean.exceptions.CurrentUserNotFoundException;
import com.codecoy.caribbean.data_model.Country;
import com.codecoy.caribbean.data_model.UserProfile;

public class CurrentUser {

    private static UserProfile userProfile;
    private static Country selectedCountry;

    public static UserProfile getInstance() throws CurrentUserNotFoundException {

        if(userProfile!=null){
            return userProfile;
        }else{
            throw new CurrentUserNotFoundException();
        }
    }

    public static UserProfile getUserProfile() {
        return userProfile;
    }

    public static void setUserProfile(UserProfile userProfile) {
        CurrentUser.userProfile = userProfile;
    }

    public static Country getSelectedCountry() {
        return selectedCountry;
    }

    public static void setSelectedCountry(Country selectedCountry) {
        CurrentUser.selectedCountry = selectedCountry;
    }
}
