package com.codecoy.caribbean.Repository;

import androidx.annotation.NonNull;

import com.codecoy.caribbean.Listeners.OnSliderLoadListeners;
import com.codecoy.caribbean.Listeners.OnUserProfileLoadListeners;
import com.codecoy.caribbean.dataModel.Country;
import com.codecoy.caribbean.DatabaseController.DatabaseAddresses;
import com.codecoy.caribbean.Listeners.OnCountriesLoadListeners;
import com.codecoy.caribbean.dataModel.TourismSlider;
import com.codecoy.caribbean.dataModel.UserProfile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    public static void getCountries(OnCountriesLoadListeners onCountriesLoadListeners){
        DatabaseAddresses.getCountriesCollection().get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Country> countries=new ArrayList<>();

                for(QueryDocumentSnapshot snapshot:queryDocumentSnapshots){
                    countries.add(snapshot.toObject(Country.class));
                }

                if(countries.size()>0){
                    onCountriesLoadListeners.onCountriesLoaded(countries);
                }else{
                    onCountriesLoadListeners.onEmpty();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                onCountriesLoadListeners.onFailure(e.getMessage());
            }
        });
    }

    public static void getMyProfile(String userId, OnUserProfileLoadListeners onUserProfileLoadListeners){
        DatabaseAddresses.getUserAccountCollection(userId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                onUserProfileLoadListeners.onUserProfileLoaded(documentSnapshot.toObject(UserProfile.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                onUserProfileLoadListeners.onFailure(e.getMessage());
            }
        });
    }
    public static void getMyTourismExplorerSlider(OnSliderLoadListeners onSliderLoadListeners){

        DatabaseAddresses.getTourismSliderCollection().get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    onSliderLoadListeners.onSliderLoaded(documentSnapshot.toObject(TourismSlider.class));
                }else{
                    onSliderLoadListeners.onNotFound();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                onSliderLoadListeners.onFailure(e.getMessage());
            }
        });

    }
}
