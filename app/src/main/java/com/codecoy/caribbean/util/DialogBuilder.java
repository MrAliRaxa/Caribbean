package com.codecoy.caribbean.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class DialogBuilder {
    public static ProgressDialog getSimpleLoadingDialog(Context context, String t, String msg){
        ProgressDialog progressDialog=new ProgressDialog(context);
        progressDialog.setTitle(t);
        progressDialog.setMessage(msg);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        return progressDialog;
    }
    public static AlertDialog getSimpleMsg(Context context, String t, String msg,DialogInterface.OnClickListener onClickListener){
        return new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle(t)
                .setMessage(msg)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onClickListener.onClick(dialog,which);
                    }
                })
                .create();
    }
}
