package com.angrychimps.appname.company;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.R;

/*
    First page when the user clicks to create an ad from the company main fragment
 */

public class CompanyCreateAdFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company_create_ad, container, false);

        MainActivity.clearMenu();
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("Create an Ad");

        ListView listView = (ListView) rootView.findViewById(R.id.listViewCompanyCreateAdTimeBlock);
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.company_create_ad_header, listView, false);
        ViewGroup footer = (ViewGroup)inflater.inflate(R.layout.company_create_ad_footer, listView, false);
        listView.addHeaderView(header, null, false);
        listView.addFooterView(footer, null, false);

        final CompanyCreateAdTimeBlockListAdapter adapter = new CompanyCreateAdTimeBlockListAdapter(getActivity());
        adapter.add("new");
        listView.setAdapter(adapter);

        EditText metCompanyCreateAdTitle = (EditText) header.findViewById(R.id.metCompanyCreateAdTitle);
        EditText metCompanyCreateAdDescription = (EditText) header.findViewById(R.id.metCompanyCreateAdDescription);
        Button bCompanyCreateAdCategory = (Button) header.findViewById(R.id.bCompanyCreateAdCategory);

        FrameLayout bCompanyCreateAdTimeBlock = (FrameLayout) footer.findViewById(R.id.bCompanyCreateAdAddTimeBlock);
        EditText metCompanyCreateAdServiceName = (EditText) footer.findViewById(R.id.metCompanyCreateAdServiceName);
        EditText metCompanyCreateAdDiscountedPrice = (EditText) footer.findViewById(R.id.metCompanyCreateAdDiscountedPrice);
        EditText metCompanyCreateAdOriginalPrice = (EditText) footer.findViewById(R.id.metCompanyCreateAdOriginalPrice);
        EditText metCompanyCreateAdDuration = (EditText) footer.findViewById(R.id.metCompanyCreateAdDuration);
        EditText metCompanyCreateAdTimeNotice = (EditText) footer.findViewById(R.id.metCompanyCreateAdTimeNotice);
        Button bCompanyCreateAdNext = (Button) footer.findViewById(R.id.bCompanyCreateAdNext);

        bCompanyCreateAdTimeBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add("new");
                adapter.notifyDataSetChanged();
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
