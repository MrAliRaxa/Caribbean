package com.codecoy.caribbean.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codecoy.caribbean.Constants.AccountType;
import com.codecoy.caribbean.DataModel.UserProfile;
import com.codecoy.caribbean.DatabaseController.DatabaseUploader;
import com.codecoy.caribbean.Listeners.OnTaskListeners;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    private static final String TAG = "SignUp";

    ActivitySignUpBinding mBinding;

    private String name,email,password,confirmPassword,phoneNumber;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_sign_up);

        mAuth=FirebaseAuth.getInstance();

        mBinding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name=mBinding.nameLayout.getEditText().getText().toString().trim();
                email=mBinding.emailLayout.getEditText().getText().toString().trim();
                password=mBinding.passwordLayout.getEditText().getText().toString().trim();
                confirmPassword=mBinding.confirmPasswordLayout.getEditText().getText().toString().trim();
                phoneNumber=mBinding.phoneNumberLayout.getEditText().getText().toString().trim();

                if(isValid()){
                    ProgressDialog pd = new ProgressDialog(SignUp.this);
                    pd.setTitle("Loading");
                    pd.setMessage("please wait . . .");
                    pd.show();
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        FirebaseUser firebaseUser=task.getResult().getUser();
                                        if(firebaseUser!=null){
                                            UserProfile userProfile=new UserProfile();
                                            userProfile.setAccountType(AccountType.USER);
                                            userProfile.setEmail(email);
                                            userProfile.setPhoneNumber(phoneNumber);
                                            userProfile.setUserName(name);
                                            userProfile.setUid(task.getResult().getUser().getUid());

                                            DatabaseUploader.setUserRecord(userProfile, new OnTaskListeners() {
                                                @Override
                                                public void onTaskSuccess() {

                                                    Toast.makeText(SignUp.this, "USer Created", Toast.LENGTH_SHORT).show();
                                                    Intent intent=new Intent(SignUp.this,EmailVerification.class);
                                                    intent.putExtra("signOut",true);
                                                    startActivity(intent);
                                                    finishAffinity();
                                                    pd.dismiss();
                                                }

                                                @Override
                                                public void onTaskFail(String e) {
                                                    pd.dismiss();
                                                    Toast.makeText(SignUp.this, "Fail to upload your data", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                        }

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        pd.dismiss();
                                    }
                                }
                            });
                }

                }

        });
    }

    private boolean isValid() {
        boolean result=true;

        if (name.equals("") || name.isEmpty())
        {
            mBinding.nameLayout.getEditText().setError("Please Enter Name");
            result=false;
        }else if (email.equals("") || email.isEmpty()) {
            mBinding.emailLayout.getEditText().setError("Please enter email");
            result = false;
        } else if (!isEmailValid(email)) {
            mBinding.emailLayout.getEditText().setError("Please enter valid email");
            result = false;
        }else if (password.equals("") || password.isEmpty())
        {
            mBinding.passwordLayout.getEditText().setError("Please enter password");
            result=false;
        }else if (confirmPassword.equals("") || confirmPassword.isEmpty())
        {
            mBinding.confirmPasswordLayout.getEditText().setError("Please enter password");
            result=false;
        }else if (phoneNumber.equals("") || phoneNumber.isEmpty())
        {
            mBinding.phoneNumberLayout.getEditText().setError("Please enter Phone Number");
            result=false;
        }else if (!password.equals(confirmPassword)||password.length()<6)
        {
            Toast.makeText(this, "Password too short", Toast.LENGTH_SHORT).show();
            result=false;
        }
        return result;

    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private FirebaseAuth getSignupAuth(){

        return null;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mAuth.signOut();
    }
}