package com.angrychimps.appname.consumer.search;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.angrychimps.appname.R;

public class ConsumerSearchLocationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_location, container, false);
        final TextView tvRadius = (TextView) rootView.findViewById(R.id.tvSearchLocationRadius);
        Button bMyLocation = (Button) rootView.findViewById(R.id.bSearchLocationMyLocation);
        Button bCityState = (Button) rootView.findViewById(R.id.bSearchLocationCityState);
        Button bZipcode = (Button) rootView.findViewById(R.id.bSearchLocationZipcode);
        SeekBar seekBarRadius = (SeekBar) rootView.findViewById(R.id.seekBarSearchLocationRadius);
        EditText etLocationCurrent = (EditText) rootView.findViewById(R.id.etSearchLocationCurrent);
        bMyLocation.requestFocus();



        bMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bCityState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bZipcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        seekBarRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvRadius.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    // Called every time the screen orientation changes or Android kills an Activity
    // to conserve resources
    // We save the last item selected in the list here and attach it to the key curChoice
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

}
