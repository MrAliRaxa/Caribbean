package com.codecoy.caribbean.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.codecoy.caribbean.dataModel.Item;
import com.codecoy.caribbean.dataModel.Shop;
import com.codecoy.caribbean.dataModel.ShopCategoryModel;
import com.codecoy.caribbean.listeners.OnCategoriesLoadListeners;
import com.codecoy.caribbean.listeners.OnCategoryLoadListeners;
import com.codecoy.caribbean.listeners.OnDealsLoadListeners;
import com.codecoy.caribbean.listeners.OnShopLoadListeners;
import com.codecoy.caribbean.listeners.OnSingleShopLoadListeners;
import com.codecoy.caribbean.listeners.OnSliderLoadListeners;
import com.codecoy.caribbean.listeners.OnUserProfileLoadListeners;
import com.codecoy.caribbean.dataModel.Country;
import com.codecoy.caribbean.database_controller.DatabaseAddresses;
import com.codecoy.caribbean.listeners.OnCountriesLoadListeners;
import com.codecoy.caribbean.dataModel.SliderContent;
import com.codecoy.caribbean.dataModel.UserProfile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Repository {

    private static final String TAG = "Repository";

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
    public static void getShop(String shopId, OnSingleShopLoadListeners onSingleShopLoadListeners){

        DatabaseAddresses.getShopDocument(shopId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if(documentSnapshot.exists()){
                    onSingleShopLoadListeners.onShopLoaded(documentSnapshot.toObject(Shop.class));
                }else{
                    onSingleShopLoadListeners.onEmpty();
                    Log.d(TAG, "onSuccess: "+documentSnapshot.getReference().getPath());

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                onSingleShopLoadListeners.onFailure(e.getMessage());
            }
        });
    }
    public static void getAllShops(OnShopLoadListeners onShopLoadListeners){
        DatabaseAddresses.getShopCollection().get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Shop> shopList=new ArrayList<>();

                for(QueryDocumentSnapshot snapshot:queryDocumentSnapshots){
                    shopList.add(snapshot.toObject(Shop.class));
                }

                if(shopList.size()>0){
                    onShopLoadListeners.onShopsLoaded(shopList);
                }else{
                    onShopLoadListeners.onEmpty();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                onShopLoadListeners.onFailure(e.getMessage());
            }
        });
    }
    public static void getCategoriesShops(String categoryId,OnShopLoadListeners onShopLoadListeners){
        DatabaseAddresses.getShopCollection().get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Shop> shopList=new ArrayList<>();

                for(QueryDocumentSnapshot snapshot:queryDocumentSnapshots){
                    if(snapshot.toObject(Shop.class).getCategoryId().equals(categoryId)){
                        shopList.add(snapshot.toObject(Shop.class));
                    }
                }

                if(shopList.size()>0){
                    onShopLoadListeners.onShopsLoaded(shopList);
                }else{
                    onShopLoadListeners.onEmpty();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                onShopLoadListeners.onFailure(e.getMessage());
            }
        });
    }
    public static void getShopCategory(String categoryId, OnCategoryLoadListeners onCategoryLoadListeners){
        DatabaseAddresses.getShopCategoryCollection().document(categoryId).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            onCategoryLoadListeners.onCategoriesLoaded(documentSnapshot.toObject(ShopCategoryModel.class));
                        }else{
                            onCategoryLoadListeners.onEmpty();
                            Log.d(TAG, "onSuccess: "+documentSnapshot.getReference().getPath());
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                onCategoryLoadListeners.onFailure(e.getMessage());
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
    public static void getShopCategoriesSlider(OnSliderLoadListeners onSliderLoadListeners){

        DatabaseAddresses.getShopsCategorySliderCollection().get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    onSliderLoadListeners.onSliderLoaded(documentSnapshot.toObject(SliderContent.class));
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
    public static void getShopSlider(OnSliderLoadListeners onSliderLoadListeners){

        DatabaseAddresses.getShopsSliderDoc().get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    onSliderLoadListeners.onSliderLoaded(documentSnapshot.toObject(SliderContent.class));
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
    public static void getCategories(OnCategoriesLoadListeners onCategoriesLoadListeners){

        List<ShopCategoryModel> shopCategoryModels=new ArrayList<>();
        DatabaseAddresses.getShopCategoryCollection().get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                    shopCategoryModels.add(documentSnapshot.toObject(ShopCategoryModel.class));
                }

                if(shopCategoryModels.size()>0){
                    onCategoriesLoadListeners.onCategoriesLoaded(shopCategoryModels);
                }else{
                    onCategoriesLoadListeners.onEmpty();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                onCategoriesLoadListeners.onFailure(e.getMessage());
            }
        });
    }
    public static void getShopDealsAndPromotions(String shopId,OnDealsLoadListeners onDealsLoadListeners){

        Executor executor= Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        DatabaseAddresses.getDealsCollection().get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                List<Item> items=new ArrayList<>();

                for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                    Item item=documentSnapshot.toObject(Item.class);
                    if(item.getShopId().equals(shopId)){
                        items.add(item);
                    }
                }

                if(items.size()>0){
                    onDealsLoadListeners.onDealLoaded(items);
                }else {
                    onDealsLoadListeners.onEmpty();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                onDealsLoadListeners.onFailure(e.getMessage());
            }
        });
    }
}
