package com.codecoy.caribbean.fragments.country_intro;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.codecoy.caribbean.adaptor.recycler_adaptor.SliderAdaptor;
import com.codecoy.caribbean.constants.SliderType;
import com.codecoy.caribbean.R;
import com.codecoy.caribbean.dataModel.ReligionAndCulture;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;


public class ReligionAndCultureFrag extends Fragment {

    private ReligionAndCulture religionAndCulture;
    private static final String TAG = "ReligionAndCultureFrag";
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

        SliderView sliderView = root.findViewById(R.id.religion_slider);
        if (religionAndCulture.getSliderContent() != null) {

            if (religionAndCulture.getSliderType() == SliderType.IMAGE_SLIDER) {
                root.findViewById(R.id.frameLayout1).setVisibility(View.INVISIBLE);
                SliderAdaptor sliderAdaptor = new SliderAdaptor(getContext(), religionAndCulture.getSliderContent());
                sliderView.setSliderAdapter(sliderAdaptor);
                sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP);
                sliderView.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);
                sliderView.startAutoCycle();
            } else {
                VideoView videoView=root.findViewById(R.id.religion_video);
                Log.d(TAG, "onCreate: "+religionAndCulture.getSliderContent().get(0));
                videoView.setVideoPath(religionAndCulture.getSliderContent().get(0));
                videoView.requestFocus();
                videoView.start();
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        root.findViewById(R.id.placeholder).setVisibility(View.GONE);
                        root.findViewById(R.id.religion_slider).setVisibility(View.GONE);
                        mp.setLooping(true);
                    }
                });

            }

        }
        TextView delicaciesContent=root.findViewById(R.id.religion_text);
        delicaciesContent.setText(religionAndCulture.getDescription());

        return root;
    }
}