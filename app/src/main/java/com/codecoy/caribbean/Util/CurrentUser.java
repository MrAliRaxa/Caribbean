package com.codecoy.caribbean.Util;


import com.codecoy.caribbean.Exceptions.CurrentUserNotFoundException;
import com.codecoy.caribbean.dataModel.Country;
import com.codecoy.caribbean.dataModel.UserProfile;

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
