package com.angrychimps.appname.customer.search;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.angrychimps.appname.R;

import org.json.JSONObject;

public class CustomerSearchFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        getDialog().setTitle("Filters");
        getDialog().setCanceledOnTouchOutside(false);

        Button bSearch = (Button) rootView.findViewById(R.id.bSearch);
        Button bCancel = (Button) rootView.findViewById(R.id.bCancel);
        Button bFilterFromDate = (Button) rootView.findViewById(R.id.bFilterFromDate);
        Button bFilterFromTime = (Button) rootView.findViewById(R.id.bFilterFromTime);
        Button bFilterToDate = (Button) rootView.findViewById(R.id.bFilterToDate);
        Button bFilterToTime = (Button) rootView.findViewById(R.id.bFilterToTime);
        RadioGroup rgFilter = (RadioGroup) rootView.findViewById(R.id.rgFilter);

        JSONObject filterObject = new JSONObject();
        //TODO- add results to JSONObject and pass back to CustomerMainFragment


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
