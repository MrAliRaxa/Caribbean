package com.codecoy.caribbean.database_controller;

import androidx.annotation.NonNull;

import com.codecoy.caribbean.dataModel.UserProfile;
import com.codecoy.caribbean.listeners.OnTaskListeners;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class DatabaseUploader {

    public static void setUserRecord(UserProfile userProfile, OnTaskListeners onTaskListeners){
        DatabaseAddresses.getUserAccountCollection(userProfile.getUid())
                .set(userProfile).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    onTaskListeners.onTaskSuccess();
                }else{
                    onTaskListeners.onTaskFail(task.getException().getMessage());
                }
            }
        });
    }
}
