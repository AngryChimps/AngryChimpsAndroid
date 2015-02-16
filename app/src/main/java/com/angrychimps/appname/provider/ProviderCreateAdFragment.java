package com.angrychimps.appname.provider;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.angrychimps.appname.R;


public class ProviderCreateAdFragment  extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company_create_ad, container, false);


        //EditText metCompanyCreateAdTitle = (EditText) rootView.findViewById(R.id.metCompanyCreateAdTitle);
        EditText metCompanyCreateAdDescription = (EditText) rootView.findViewById(R.id.metCompanyCreateAdDescription);
        EditText metCompanyCreateAdServiceName = (EditText) rootView.findViewById(R.id.metCompanyCreateAdServiceName);
        EditText metCompanyCreateAdDiscountedPrice = (EditText) rootView.findViewById(R.id.metCompanyCreateAdDiscountedPrice);
        EditText metCompanyCreateAdOriginalPrice = (EditText) rootView.findViewById(R.id.metCompanyCreateAdOriginalPrice);
        EditText metCompanyCreateAdDuration = (EditText) rootView.findViewById(R.id.metCompanyCreateAdDuration);
        EditText metCompanyCreateAdTimeNotice = (EditText) rootView.findViewById(R.id.metCompanyCreateAdTimeNotice);


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
