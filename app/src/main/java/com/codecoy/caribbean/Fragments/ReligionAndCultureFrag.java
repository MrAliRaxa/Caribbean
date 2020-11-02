package com.codecoy.caribbean.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codecoy.caribbean.Adaptor.SliderAdaptor;
import com.codecoy.caribbean.Constants.SliderType;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.dataModel.ReligionAndCulture;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;


public class ReligionAndCultureFrag extends Fragment {

    private ReligionAndCulture religionAndCulture;
    public ReligionAndCultureFrag() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            religionAndCulture=getArguments().getParcelable("religionAndCulture");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_religon_and_culture, container, false);

        SliderView sliderView = root.findViewById(R.id.delicacies_slider);
        if (religionAndCulture.getSliderContent() != null) {

            if (religionAndCulture.getSliderType() == SliderType.IMAGE_SLIDER) {
                SliderAdaptor sliderAdaptor = new SliderAdaptor(getContext(), religionAndCulture.getSliderContent());
                sliderView.setSliderAdapter(sliderAdaptor);
                sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP);
                sliderView.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);
                sliderView.startAutoCycle();
            } else {


            }

        }
        TextView delicaciesContent=root.findViewById(R.id.delicacies_text);
        delicaciesContent.setText(religionAndCulture.getDescription());

        return root;
    }
}