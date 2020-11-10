package com.codecoy.caribbean.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.codecoy.caribbean.adaptor.recycler_adaptor.SliderAdaptor;
import com.codecoy.caribbean.constants.SliderType;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.dataModel.Delicacies;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class DelicaciesFragm extends Fragment {


    private Delicacies delicacies;
    private static final String TAG = "DelicaciesFragm";
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
                view.findViewById(R.id.frameLayout1).setVisibility(View.INVISIBLE);
                SliderAdaptor sliderAdaptor = new SliderAdaptor(getContext(), delicacies.getSliderContent());
                sliderView.setSliderAdapter(sliderAdaptor);
                sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP);
                sliderView.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);
                sliderView.startAutoCycle();
            } else {

                VideoView videoView=view.findViewById(R.id.delicacies_video);
                videoView.setVideoPath(delicacies.getSliderContent().get(0));
                videoView.requestFocus();
                videoView.start();
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        view.findViewById(R.id.placeholder).setVisibility(View.GONE);
                        view.findViewById(R.id.delicacies_slider).setVisibility(View.GONE);
                        mp.setLooping(true);
                    }
                });
            }

        }
        TextView delicaciesContent=view.findViewById(R.id.delicacies_text);
        delicaciesContent.setText(delicacies.getDescription());
        return view;
    }
}
