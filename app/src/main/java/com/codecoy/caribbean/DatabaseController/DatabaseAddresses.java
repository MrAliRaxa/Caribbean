package com.codecoy.caribbean.DatabaseController;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collections;

public class DatabaseAddresses {
    public static DocumentReference getUserAccountCollection(String docId){

        return FirebaseFirestore.getInstance().collection("UserAccounts")
                .document(docId);
    }

    public static CollectionReference getCountriesCollection(){

        return FirebaseFirestore.getInstance().collection("CountriesCollection");
    }
}
