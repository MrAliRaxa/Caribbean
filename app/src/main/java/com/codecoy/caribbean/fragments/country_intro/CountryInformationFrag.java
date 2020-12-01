package com.codecoy.caribbean.fragments.country_intro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.data_model.Country;
import com.codecoy.caribbean.data_model.CountryInformation;

public class CountryInformationFrag extends Fragment {

    CountryInformation information;
    Country country;

    public CountryInformationFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            information=getArguments().getParcelable("information");
            country=getArguments().getParcelable("country");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_country_information, container, false);

        TextView name=root.findViewById(R.id.countryInfo_name);
        ImageView flag=root.findViewById(R.id.countryInfo_flag);
        ImageView armFlag=root.findViewById(R.id.countryInfo_armFlag);
        TextView motto=root.findViewById(R.id.countryInfo_motto);
        TextView language=root.findViewById(R.id.countryInfo_language);
        TextView population=root.findViewById(R.id.countryInfo_population);
        TextView capital=root.findViewById(R.id.countryInfo_capital);
        TextView temp=root.findViewById(R.id.countryInfo_temp);
        TextView currency=root.findViewById(R.id.countryInfo_currency);
        TextView other=root.findViewById(R.id.countryInfo_other);
        name.setText(information.getName());
        motto.setText(information.getMotto());
        language.setText(information.getLanguage());
        population.setText(String.valueOf(information.getPopulation()));
        capital.setText(information.getCapital());
        temp.setText(String.valueOf(information.getTemperature()));
        currency.setText(information.getCurrencyName());
        other.setText(information.getExtraInformation());

        Glide.with(getContext()).load(country.getFlagImageUrl()).override(256,256).into(flag);
        Glide.with(getContext()).load(country.getArmFlagUrl()).override(256,256).into(armFlag);
        return root;
    }
}