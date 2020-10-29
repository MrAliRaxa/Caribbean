package com.codecoy.caribbean.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.codecoy.caribbean.R;
import com.codecoy.caribbean.databinding.ActivityEmailVerificationBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailVerification extends AppCompatActivity {



    private FirebaseUser firebaseUser;
    private ActivityEmailVerificationBinding verificationBinding;
    private TextView emailStatus;
    private TextView msgText;
    private Boolean signOutMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        verificationBinding= DataBindingUtil.setContentView(EmailVerification.this,R.layout.activity_email_verification);
        emailStatus=verificationBinding.emailVerificationEmailStatus;
        msgText=verificationBinding.emailVerificationMsg;

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser!=null){
            sendVerificationEmail(firebaseUser);
            if(getIntent()!=null){
                signOutMsg=getIntent().getBooleanExtra("signOut",false);
            }
        }



    }

    private void sendVerificationEmail(FirebaseUser user){

        user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(EmailVerification.this, "Email Sent", Toast.LENGTH_SHORT).show();
                emailStatus.setText("Email Sent");
                msgText.setVisibility(View.VISIBLE);
                setVerificationCallBack(user);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EmailVerification.this, "Error :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                emailStatus.setText("Fail to send mail");
            }
        });
    }
    private void setVerificationCallBack(FirebaseUser user){

        Thread verificationThread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (!user.isEmailVerified()){
                    firebaseUser.reload().addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EmailVerification.this, "Fail to get Verification Status try again", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            FirebaseUser vUser=FirebaseAuth.getInstance().getCurrentUser();
                            if(vUser.isEmailVerified()){

                                new  AlertDialog.Builder(EmailVerification.this)
                                        .setTitle("Verified")
                                        .setMessage("Your email is verified now thank you !")
                                        .setPositiveButton("Back to login", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                Intent intent=new Intent(EmailVerification.this,LoginActivity.class);
                                                if(signOutMsg){
                                                    FirebaseAuth.getInstance().signOut();
                                                }
                                                startActivity(intent);
                                                finish();

                                            }
                                        }).show();


                            }
                        }
                    });
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        verificationThread.start();
    }
}