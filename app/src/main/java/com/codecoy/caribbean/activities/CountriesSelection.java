package com.codecoy.caribbean.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Spinner;

import com.codecoy.caribbean.adaptor.CounterySpinnerAdaptor;
import com.codecoy.caribbean.util.CurrentUser;
import com.codecoy.caribbean.data_model.Country;
import com.codecoy.caribbean.data_model.CountryInformation;
import com.codecoy.caribbean.data_model.CountrySlider;
import com.codecoy.caribbean.data_model.Delicacies;
import com.codecoy.caribbean.listeners.OnCountriesLoadListeners;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.repository.Repository;
import com.codecoy.caribbean.databinding.ActivityCountriesSelectionBinding;


import java.util.List;

public class CountriesSelection extends AppCompatActivity {

    private Spinner countriesSpinner;
    private List<Country> countryList;
    private ActivityCountriesSelectionBinding selectionBinding;
    private static final String TAG = "CountriesSelection";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        selectionBinding= DataBindingUtil.setContentView(CountriesSelection.this,R.layout.activity_countries_selection);

        countriesSpinner=selectionBinding.countrySpinner;
        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait . . .");
        progressDialog.show();

        Repository.getCountries(new OnCountriesLoadListeners() {
            @Override
            public void onCountriesLoaded(List<Country> countries) {
                countryList=countries;
                CounterySpinnerAdaptor counterySpinnerAdaptor=new CounterySpinnerAdaptor(CountriesSelection.this, countries);
                selectionBinding.countrySpinner.setAdapter(counterySpinnerAdaptor);
                progressDialog.dismiss();
            }

            @Override
            public void onEmpty() {
                Log.d(TAG, "onEmpty: ");
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(String e) {
                Log.d(TAG, "onFailure: ");
                progressDialog.dismiss();
            }
        });

        selectionBinding.countriesSelectionNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delicacies delicacies=countryList.get(countriesSpinner.getSelectedItemPosition()).getDelicacies();
                CountryInformation information=countryList.get(countriesSpinner.getSelectedItemPosition()).getInformation();
                CountrySlider slider=countryList.get(countriesSpinner.getSelectedItemPosition()).getCountrySlider();

                Intent intent=new Intent(CountriesSelection.this,CountryIntro.class);
                intent.putExtra("country",countryList.get(countriesSpinner.getSelectedItemPosition()));
                intent.putExtra("delicacies",delicacies);
                intent.putExtra("information",information);
                intent.putExtra("slider",slider);

                Log.d(TAG, "onClick: "+countryList.get(countriesSpinner.getSelectedItemPosition()).getInformation().getName());
                CurrentUser.setSelectedCountry(countryList.get(countriesSpinner.getSelectedItemPosition()));
                startActivity(intent);

            }
        });

    }
}