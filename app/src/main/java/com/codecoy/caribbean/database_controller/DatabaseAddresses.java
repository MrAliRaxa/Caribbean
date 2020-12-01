package com.codecoy.caribbean.database_controller;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DatabaseAddresses {
    public static DocumentReference getUserAccountCollection(String docId){

        return FirebaseFirestore.getInstance().collection("UserAccounts")
                .document(docId);
    }
    public static CollectionReference getShopDirectoryCollection(){
        return FirebaseFirestore.getInstance().collection("ShopDirectoryItemCollection");
    }
    public static CollectionReference getShopShowroomCollection(){
        return FirebaseFirestore.getInstance().collection("ShopShowroomItemCollection");
    }
    public static CollectionReference getActivitiesCollection(){
        return FirebaseFirestore.getInstance().collection("ActivitiesCollection");
    }
    public static CollectionReference getAttractionsCollection(){
        return FirebaseFirestore.getInstance().collection("AttractionsCollection");
    }
    public static CollectionReference getShopPricesCollection(){
        return FirebaseFirestore.getInstance().collection("ShopPricesItemCollection");
    }
    public static CollectionReference getBuildingCollection(){
        return FirebaseFirestore.getInstance().collection("BuildingCollection");
    }
    public static CollectionReference getHistoricalCollection(){
        return FirebaseFirestore.getInstance().collection("HistoricalCollection");
    }

    public static CollectionReference getShopStoreCollection(){
        return FirebaseFirestore.getInstance().collection("ShopStoreItemCollection");
    }
    public static CollectionReference getCountriesCollection(){
        return FirebaseFirestore.getInstance().collection("CountriesCollection");
    }
    public static DocumentReference getCountriesDoc(String docId){
        return FirebaseFirestore.getInstance().collection("CountriesCollection")
                .document(docId);
    }

    public static DocumentReference getShopDocument(String docId){
        return FirebaseFirestore.getInstance().collection("ShopCollection")
                .document(docId);
    }    public static CollectionReference getShopCollection(){
        return FirebaseFirestore.getInstance().collection("ShopCollection");
    }
    public static CollectionReference getShopCategoryCollection(){
        return FirebaseFirestore.getInstance().collection("ShopCategoryCollection");
    }

    public static CollectionReference getWildLifeCollection(){
        return FirebaseFirestore.getInstance().collection("WildLifeCollection");
    }
    public static CollectionReference getNorthCollection(){
        return FirebaseFirestore.getInstance().collection("NorthCollection");
    }
    public static CollectionReference getSouthCollection(){
        return FirebaseFirestore.getInstance().collection("SouthCollection");
    }
    public static CollectionReference getWestCollection(){
        return FirebaseFirestore.getInstance().collection("WestCollection");
    }
    public static CollectionReference getEastCollection(){
        return FirebaseFirestore.getInstance().collection("EastCollection");
    }

    public static CollectionReference getTobagoCollection(){
        return FirebaseFirestore.getInstance().collection("TobagoCollection");
    }

    public static DocumentReference getShopsCategorySliderCollection(){
        return FirebaseFirestore.getInstance().collection("SliderCollection")
                .document("ShopsCategorySlider");
    }
    public static CollectionReference getBankCollection(){
        return FirebaseFirestore.getInstance().collection("BankLocationCollection");
    }
    public static DocumentReference getShopsSliderDoc(){
        return FirebaseFirestore.getInstance().collection("SliderCollection")
                .document("ShopsSlider");
    }
    public static CollectionReference getShopWebsiteCollection(){
        return FirebaseFirestore.getInstance().collection("ShopWebsiteItemCollection");
    }
    public static CollectionReference getShopATMCollection(){
        return FirebaseFirestore.getInstance().collection("ShopATMCollection");
    }
    public static CollectionReference getDealsCollection(){
        return FirebaseFirestore.getInstance().collection("DealsAndPromotions");
    }
    public static CollectionReference getShopMenuCollection(){
        return FirebaseFirestore.getInstance().collection("ShopMenu");
    }
    public static CollectionReference getShopLocationCollection(){
        return FirebaseFirestore.getInstance().collection("ShopLocations");
    }

    public static CollectionReference getShopInformationCollection(){
        return FirebaseFirestore.getInstance().collection("ShopInformation");
    }

}
