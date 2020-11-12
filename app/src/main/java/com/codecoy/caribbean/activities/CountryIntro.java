package com.codecoy.caribbean.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.codecoy.caribbean.adaptor.recycler_adaptor.SliderAdaptor;
import com.codecoy.caribbean.constants.SliderType;
import com.codecoy.caribbean.fragments.ReligionAndCultureFrag;
import com.codecoy.caribbean.dataModel.Country;
import com.codecoy.caribbean.dataModel.CountryInformation;
import com.codecoy.caribbean.dataModel.CountrySlider;
import com.codecoy.caribbean.dataModel.Delicacies;
import com.codecoy.caribbean.fragments.CountryInformationFrag;
import com.codecoy.caribbean.fragments.DelicaciesFragm;
import com.codecoy.caribbean.fragments.HistoryFrag;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.databinding.ActivityCountryIntroBinding;
import com.jarvanmo.exoplayerview.media.SimpleMediaSource;
import com.jarvanmo.exoplayerview.ui.ExoVideoView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

public class CountryIntro extends AppCompatActivity {


    private ActivityCountryIntroBinding mDataBinding;
    private static final String TAG = "CountryIntro";
    private Country country;
    private ExoVideoView exoVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mDataBinding= DataBindingUtil.setContentView(CountryIntro.this,R.layout.activity_country_intro);
        if(getIntent().getExtras()!=null){
            if(getIntent().getExtras().getParcelable("country")!=null){
                country=getIntent().getExtras().getParcelable("country");
              //  intent.putExtra("country",countryList.get(countriesSpinner.getSelectedItemPosition()));
                Delicacies delicacies= getIntent().getParcelableExtra("delicacies");
                CountryInformation information=getIntent().getParcelableExtra("information");
                CountrySlider slider=getIntent().getParcelableExtra("slider");
                country.setDelicacies(delicacies);
                country.setInformation(information);
                country.setCountrySlider(slider);


            }else{
                Log.d(TAG, "onCreate: null country");

            }

        }else{
            Log.d(TAG, "onCreate: country null now");
        }


        Log.d(TAG, "onCreate: "+country.getInformation().getName());


        mDataBinding.countryIntroExplorer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CountryIntro.this,TouristTypeSelection.class));
                finish();
            }
        });
        if(country.getCountrySlider().getSliderContent()!=null){

            if(country.getCountrySlider().getSliderType()== SliderType.IMAGE_SLIDER){
                mDataBinding.frameLayout1.setVisibility(View.INVISIBLE);
                SliderAdaptor sliderAdaptor=new SliderAdaptor(this,country.getCountrySlider().getSliderContent());
                mDataBinding.countryIntroImageSlider.setSliderAdapter(sliderAdaptor);
                mDataBinding.countryIntroImageSlider.setIndicatorAnimation(IndicatorAnimationType.DROP);
                mDataBinding.countryIntroImageSlider.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);
                mDataBinding.countryIntroImageSlider.startAutoCycle();
            }else{
                Log.d(TAG, "onCreate: "+country.getCountrySlider().getSliderContent().get(0));
                mDataBinding.placeholder.setVisibility(View.GONE);
                mDataBinding.countryIntroImageSlider.setVisibility(View.GONE);
                exoVideoView=mDataBinding.videoView;
                SimpleMediaSource mediaSource = new SimpleMediaSource(country.getCountrySlider().getSliderContent().get(0));//uri also supported
                exoVideoView.play(mediaSource);

            }

        }


        TextView information=mDataBinding.countryIntroInformationText;
        TextView history=mDataBinding.countryIntroHistory;
        TextView delicacies=mDataBinding.countryIntroDelicacies;
        TextView religionAndCulture=mDataBinding.countryIntroReligion;
        Bundle bundle=new Bundle();
        bundle.putParcelable("information",country.getInformation());
        bundle.putParcelable("country",country);
        CountryInformationFrag informationFrag=new CountryInformationFrag();

        informationFrag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.countryIntro_fragmentContainer,informationFrag)
                .commit();
        information.setTextColor(Color.BLUE);
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putParcelable("information",country.getInformation());
                bundle.putParcelable("country",country);
                CountryInformationFrag informationFrag=new CountryInformationFrag();

                informationFrag.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.countryIntro_fragmentContainer,informationFrag)
                        .commit();
                information.setTextColor(Color.BLUE);
                history.setTextColor(Color.BLACK);
                delicacies.setTextColor(Color.BLACK);
                religionAndCulture.setTextColor(Color.BLACK);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("history",country.getHistory());
                HistoryFrag historyFrag=new HistoryFrag();
                historyFrag.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.countryIntro_fragmentContainer,historyFrag)
                        .commit();

                information.setTextColor(Color.BLACK);
                history.setTextColor(Color.BLUE);
                delicacies.setTextColor(Color.BLACK);
                religionAndCulture.setTextColor(Color.BLACK);
            }
        });

        delicacies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putParcelable("delicacies",country.getDelicacies());
                DelicaciesFragm delicaciesFragm=new DelicaciesFragm();
                delicaciesFragm.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.countryIntro_fragmentContainer,delicaciesFragm)
                        .commit();

                information.setTextColor(Color.BLACK);
                history.setTextColor(Color.BLACK);
                delicacies.setTextColor(Color.BLUE);
                religionAndCulture.setTextColor(Color.BLACK);
            }
        });

        religionAndCulture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putParcelable("religionAndCulture",country.getReligionAndCulture());
                ReligionAndCultureFrag religionAndCultureFrag=new ReligionAndCultureFrag();
                religionAndCultureFrag.setArguments(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.countryIntro_fragmentContainer,religionAndCultureFrag)
                        .commit();

                information.setTextColor(Color.BLACK);
                history.setTextColor(Color.BLACK);
                delicacies.setTextColor(Color.BLACK);
                religionAndCulture.setTextColor(Color.BLUE);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        exoVideoView.releasePlayer();
    }
}