package com.codecoy.caribbean.Fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codecoy.caribbean.Adaptor.SliderAdaptor;
import com.codecoy.caribbean.Constants.SliderType;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.dataModel.Delicacies;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class DelicaciesFragm extends Fragment {


    private Delicacies delicacies;

    public DelicaciesFragm() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            delicacies = getArguments().getParcelable("delicacies");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delicacies, container, false);

        SliderView sliderView = view.findViewById(R.id.delicacies_slider);
        if (delicacies.getSliderContent() != null) {

            if (delicacies.getSliderType() == SliderType.IMAGE_SLIDER) {
                SliderAdaptor sliderAdaptor = new SliderAdaptor(getContext(), delicacies.getSliderContent());
                sliderView.setSliderAdapter(sliderAdaptor);
                sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP);
                sliderView.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);
                sliderView.startAutoCycle();
            } else {


            }

        }
        TextView delicaciesContent=view.findViewById(R.id.delicacies_text);
        delicaciesContent.setText(delicacies.getDescription());
        return view;
    }
}
