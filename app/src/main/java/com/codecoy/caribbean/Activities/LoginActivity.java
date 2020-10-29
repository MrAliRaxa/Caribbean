package com.codecoy.caribbean.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codecoy.caribbean.R;
import com.codecoy.caribbean.databinding.ActivityLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private ActivityLoginBinding activityLoginBinding;
    private static final String TAG = "LoginActivity";
    public static final int emailVerificationCode=1;
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        configureGoogleClient();
        mAuth=FirebaseAuth.getInstance();
        setAuthStateListener();

        activityLoginBinding= DataBindingUtil.setContentView(LoginActivity.this,R.layout.activity_login);
        activityLoginBinding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserNow();
            }
        });

        activityLoginBinding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignUpScreen();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==emailVerificationCode){
            boolean vStatus=data.getExtras().getBoolean("vStatus");
            Toast.makeText(this, "Verified", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }


    private void startDashboard(){
        startActivity(new Intent(LoginActivity.this,IntroScreen.class));
        finish();
    }
    private void startSignUpScreen(){
        startActivity(new Intent(LoginActivity.this,SignUp.class));
    }
    private void startEmailVerificationScreen(){
        startActivityForResult(new Intent(LoginActivity.this,EmailVerification.class),emailVerificationCode);
    }
    private void configureGoogleClient() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                // for the requestIdToken, this is in the values.xml file that
                // is generated from your google-services.json
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this, Splash.class));
                        } else {
                            Log.i(TAG, "signInWithCredential:failure", task.getException());

                        }
                    }
                });
    }
    private void setAuthStateListener(){
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    if(firebaseAuth.getCurrentUser().isEmailVerified()){
                        startDashboard();
                    }else{
                        startEmailVerificationScreen();
                    }
                }

            }
        };
    }
    private void loginUserNow(){
        String email=activityLoginBinding.emailEditText.getEditText().getText().toString();
        String password=activityLoginBinding.passwordEditText.getEditText().getText().toString();
        if(email.isEmpty()){
            Toast.makeText(LoginActivity.this, "invalid email", Toast.LENGTH_SHORT).show();
            return;
        }else if(password.isEmpty()||password.length()<6){
            Toast.makeText(LoginActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
            return;
        }
        ProgressDialog pd = new ProgressDialog(LoginActivity.this);
        pd.setTitle("Loading");
        pd.setMessage("please wait . . .");
        pd.show();
        mAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser firebaseUser=authResult.getUser();
                if(firebaseUser.isEmailVerified()){
                    startDashboard();
                    pd.dismiss();

                }else{
                    startEmailVerificationScreen();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                if(e instanceof FirebaseAuthInvalidCredentialsException){
                    Toast.makeText(LoginActivity.this, "Wrong Email Password", Toast.LENGTH_SHORT).show();
                }else if(e instanceof FirebaseAuthInvalidUserException){
                    Toast.makeText(LoginActivity.this, "Invalid User", Toast.LENGTH_SHORT).show();
                }else if(e instanceof FirebaseAuthWeakPasswordException){
                    Toast.makeText(LoginActivity.this, "Password too weak", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}