package com.angrychimps.citizenvet.fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.angrychimps.citizenvet.R;
import com.angrychimps.citizenvet.server.JsonRequestObject;

import org.json.JSONObject;

public class CFilterFragment extends DialogFragment implements View.OnClickListener{

    private final JsonRequestObject builder = new JsonRequestObject();
    //TODO- add results to JSONObject and pass back to CMainFragment or implement interface through activity

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_filter, container, false);
        getDialog().setTitle("Filters");
        getDialog().setCanceledOnTouchOutside(false);

        Button bSearch = (Button) rootView.findViewById(R.id.bSearch);
        Button bFilterFromDate = (Button) rootView.findViewById(R.id.bFilterFromDate);
        Button bFilterFromTime = (Button) rootView.findViewById(R.id.bFilterFromTime);
        Button bFilterToDate = (Button) rootView.findViewById(R.id.bFilterToDate);
        Button bFilterToTime = (Button) rootView.findViewById(R.id.bFilterToTime);
        RadioGroup rgFilter = (RadioGroup) rootView.findViewById(R.id.rgFilter);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bSearch:
                JSONObject object = builder.getJsonObject();
                Bundle bundle = new Bundle();
                bundle.putString("JsonObject", object.toString());
                //TODO finish this
                break;
            case R.id.bFilterFromDate:
                break;
            case R.id.bFilterFromTime:
                break;
            case R.id.bFilterToDate:
                break;
            case R.id.bFilterToTime:
                break;
            case R.id.rgFilter:
                break;
        }
    }
}