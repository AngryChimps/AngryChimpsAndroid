package com.angrychimps.appname.consumer.search;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.angrychimps.appname.R;

public class ConsumerSearchSortingFragment extends Fragment {

    Button bSearchSortRelevance, bSearchSortLocation, bSearchSortPriceLowHigh, bSearchSortPriceHighLow, bSearchSortDiscount;
    static int sortMode= 0; //0 = relevance, 1 = location, 2 = price low high, 3 = price high low, 4 = discount

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_sorting, container, false);
        bSearchSortRelevance = (Button) rootView.findViewById(R.id.bSearchSortRelevance);
        bSearchSortLocation = (Button) rootView.findViewById(R.id.bSearchSortLocation);
        bSearchSortPriceLowHigh = (Button) rootView.findViewById(R.id.bSearchSortPriceLowHigh);
        bSearchSortPriceHighLow = (Button) rootView.findViewById(R.id.bSearchSortPriceHighLow);
        bSearchSortDiscount = (Button) rootView.findViewById(R.id.bSearchSortDiscount);
        clearButtonFocus();
        switch(sortMode){
            case 1: bSearchSortLocation.requestFocus();
                break;
            case 2: bSearchSortPriceLowHigh.requestFocus();
                break;
            case 3: bSearchSortPriceHighLow.requestFocus();
                break;
            case 4: bSearchSortRelevance.requestFocus();
                break;
            default: bSearchSortRelevance.requestFocus();
        }

        bSearchSortRelevance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearButtonFocus();
                bSearchSortRelevance.requestFocus();
                sortMode = 0;
            }
        });

        bSearchSortLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearButtonFocus();
                bSearchSortLocation.requestFocus();
                sortMode = 1;
            }
        });

        bSearchSortPriceLowHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearButtonFocus();
                bSearchSortPriceLowHigh.requestFocus();
                sortMode = 2;
            }
        });

        bSearchSortPriceHighLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearButtonFocus();
                bSearchSortPriceHighLow.requestFocus();
                sortMode = 3;
            }
        });

        bSearchSortDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearButtonFocus();
                bSearchSortDiscount.requestFocus();
                sortMode = 4;
            }
        });
        return rootView;
    }

    private void clearButtonFocus() {
        bSearchSortRelevance.clearFocus();
        bSearchSortLocation.clearFocus();
        bSearchSortPriceLowHigh.clearFocus();
        bSearchSortPriceHighLow.clearFocus();
        bSearchSortDiscount.clearFocus();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    // Called every time the screen orientation changes or Android kills an Activity
    // We save the last item selected in the list here and attach it to the key curChoice
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}

