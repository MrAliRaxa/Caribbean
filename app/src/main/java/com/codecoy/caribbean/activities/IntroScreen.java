package com.codecoy.caribbean.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codecoy.caribbean.R;
import com.codecoy.caribbean.databinding.ActivityIntroScreenBinding;

public class IntroScreen extends AppCompatActivity {

    ActivityIntroScreenBinding introScreenBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);
        introScreenBinding= DataBindingUtil.setContentView(IntroScreen.this,R.layout.activity_intro_screen);
        introScreenBinding.introScreenNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroScreen.this,CountriesSelection.class));
                finish();
            }
        });
    }
}