package com.codecoy.caribbean.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.codecoy.caribbean.Adaptor.SliderAdaptor;
import com.codecoy.caribbean.DataModel.SliderItem;
import com.codecoy.caribbean.Fragments.CountryInformation;
import com.codecoy.caribbean.Fragments.Delicacies;
import com.codecoy.caribbean.Fragments.History;
import com.codecoy.caribbean.Fragments.ReligonAndCulture;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.databinding.ActivityCountryIntroBinding;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class CountryIntro extends AppCompatActivity {


    private ActivityCountryIntroBinding mDataBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding= DataBindingUtil.setContentView(CountryIntro.this,R.layout.activity_country_intro);

        SliderItem sliderItem=new SliderItem();
        sliderItem.setImageUrl("https://images.pexels.com/photos/60597/dahlia-red-blossom-bloom-60597.jpeg");
        SliderItem sliderItem2=new SliderItem();
        sliderItem2.setImageUrl("https://c4.wallpaperflare.com/wallpaper/410/867/750/vector-forest-sunset-forest-sunset-forest-wallpaper-thumb.jpg");
        SliderItem sliderItem3=new SliderItem();
        sliderItem3.setImageUrl("https://images.pexels.com/videos/3163534/free-video-3163534.jpg");
        List<SliderItem> sliderItemList=new ArrayList<>();
        sliderItemList.add(sliderItem);
        sliderItemList.add(sliderItem2);
        sliderItemList.add(sliderItem3);
        SliderAdaptor sliderAdaptor=new SliderAdaptor(this,sliderItemList);
        mDataBinding.imageSlider.setSliderAdapter(sliderAdaptor);

        mDataBinding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.DROP);
        mDataBinding.imageSlider.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);
        mDataBinding.imageSlider.startAutoCycle();

        TextView information=mDataBinding.countryIntroInformationText;
        TextView history=mDataBinding.countryIntroHistory;
        TextView delicacies=mDataBinding.countryIntroDelicacies;
        TextView religionAndCulture=mDataBinding.countryIntroReligion;
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.countryIntro_fragmentContainer,new CountryInformation())
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
                getSupportFragmentManager().beginTransaction().replace(R.id.countryIntro_fragmentContainer,new History())
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
                getSupportFragmentManager().beginTransaction().replace(R.id.countryIntro_fragmentContainer,new Delicacies())
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
                getSupportFragmentManager().beginTransaction().replace(R.id.countryIntro_fragmentContainer,new ReligonAndCulture())
                        .commit();
                information.setTextColor(Color.BLACK);
                history.setTextColor(Color.BLACK);
                delicacies.setTextColor(Color.BLACK);
                religionAndCulture.setTextColor(Color.BLUE);
            }
        });


    }
}