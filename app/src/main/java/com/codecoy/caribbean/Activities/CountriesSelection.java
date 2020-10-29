package com.codecoy.caribbean.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.codecoy.caribbean.Adaptor.CounterySpinnerAdaptor;
import com.codecoy.caribbean.DataModel.CountrySpinnerModel;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.databinding.ActivityCountriesSelectionBinding;


import java.util.ArrayList;
import java.util.List;

public class CountriesSelection extends AppCompatActivity {

    private Spinner countriesSpinner;
    private ActivityCountriesSelectionBinding selectionBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_selection);
        selectionBinding= DataBindingUtil.setContentView(CountriesSelection.this,R.layout.activity_countries_selection);

        List<CountrySpinnerModel> countrySpinnerModelList =new ArrayList<>();
        CountrySpinnerModel trinidad=new CountrySpinnerModel();
        CountrySpinnerModel stLucia=new CountrySpinnerModel();
        CountrySpinnerModel jamaica=new CountrySpinnerModel();
        CountrySpinnerModel barbados=new CountrySpinnerModel();
        CountrySpinnerModel grenada=new CountrySpinnerModel();
        trinidad.setCountryName("Trinidad & Tobago ");
        trinidad.setDrawable(ContextCompat.getDrawable(this,R.drawable.trinidad));
        countrySpinnerModelList.add(trinidad);
        stLucia.setCountryName("St Lucia");
        stLucia.setDrawable(ContextCompat.getDrawable(this,R.drawable.trinidad));
        countrySpinnerModelList.add(stLucia);
//
        jamaica.setCountryName("Jamaica");
        barbados.setCountryName("Barbados");
        grenada.setCountryName("Grenada");
        jamaica.setDrawable(ContextCompat.getDrawable(this,R.drawable.trinidad));
        barbados.setDrawable(ContextCompat.getDrawable(this,R.drawable.trinidad));
        grenada.setDrawable(ContextCompat.getDrawable(this,R.drawable.trinidad));
        countrySpinnerModelList.add(jamaica);
        countrySpinnerModelList.add(barbados);
        countrySpinnerModelList.add(grenada);


        CounterySpinnerAdaptor counterySpinnerAdaptor=new CounterySpinnerAdaptor(this, countrySpinnerModelList);
        selectionBinding.countrySpinner.setAdapter(counterySpinnerAdaptor);
        selectionBinding.countriesSelectionNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CountriesSelection.this,TouristTypeSelection.class));
                finish();
            }
        });
    }
}