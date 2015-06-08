package com.angrychimps.appname.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.angrychimps.appname.R;

/*
    Customer Create Ad page, currently not implemented
 */

public class Deprecated_CRequestServiceFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_customer_create_ad, container, false);

        EditText etCustomerCreateAdTitle = (EditText) rootView.findViewById(R.id.etCustomerCreateAdTitle);
        EditText etCustomerCreateAdDescription = (EditText) rootView.findViewById(R.id.etCustomerCreateAdDescription);
        Button bCustomerCreateAdManagePhotos = (Button) rootView.findViewById(R.id.bCustomerCreateAdManagePhotos);
        final RadioGroup rgCustomerCreateAd = (RadioGroup) rootView.findViewById(R.id.rgCustomerCreateAd);
        final LinearLayout lCreateAdAddressVisibility = (LinearLayout) rootView.findViewById(R.id.lCreateAdAddressVisibility);
        EditText etCustomerAddress = (EditText) rootView.findViewById(R.id.etCustomerAddress);
        EditText etCustomerAddress2 = (EditText) rootView.findViewById(R.id.etCustomerAddress2);
        EditText etCustomerZipcode = (EditText) rootView.findViewById(R.id.etCustomerZipcode);
        Button bCustomerCreateAdStartDate = (Button) rootView.findViewById(R.id.bCustomerCreateAdStartDate);
        Button bCustomerCreateAdStartTime = (Button) rootView.findViewById(R.id.bCustomerCreateAdStartTime);
        Button bCustomerCreateAdEndDate = (Button) rootView.findViewById(R.id.bCustomerCreateAdEndDate);
        Button bCustomerCreateAdEndTime = (Button) rootView.findViewById(R.id.bCustomerCreateAdEndTime);
        Button bCustomerCreateAdCategory = (Button) rootView.findViewById(R.id.bCustomerCreateAdCategory);
        Button bCustomerCreateAdPost = (Button) rootView.findViewById(R.id.bCustomerCreateAdPost);
        Button bCustomerCreateAdCancel = (Button) rootView.findViewById(R.id.bCustomerCreateAdCancel);

        rgCustomerCreateAd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbChecked = (RadioButton) rgCustomerCreateAd.findViewById(checkedId);
                int checkedIndex = rgCustomerCreateAd.indexOfChild(rbChecked);

                if (checkedIndex == 0) {
                    lCreateAdAddressVisibility.setVisibility(View.GONE);
                }else lCreateAdAddressVisibility.setVisibility(View.VISIBLE);
            }
        });


        return rootView;
    }
}
