package com.angrychimps.citizenvet.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.angrychimps.citizenvet.R;
import com.angrychimps.citizenvet.adapters.CCreateAdTimeBlockListAdapter;
import com.angrychimps.citizenvet.models_old.Availabilities;

import java.util.ArrayList;
import java.util.List;

/*
    First page when the user clicks to create an ad from the company main fragment
 */

public class PCreateAdFragment extends Fragment {

    private List<Availabilities> list;
    private CCreateAdTimeBlockListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_p_create_ad, container, false);

//        MainActivity.clearMenu();
//        MainActivity.setToolbarTitle("Create an Ad");

        ListView listView = (ListView) rootView.findViewById(R.id.listViewCompanyCreateAdTimeBlock);
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.header_p_create_ad, listView, false);
        ViewGroup footer = (ViewGroup)inflater.inflate(R.layout.footer_p_create_ad, listView, false);
        listView.addHeaderView(header, null, false);
        listView.addFooterView(footer, null, false);

        list = new ArrayList<>();
        list.add(new Availabilities());
        adapter = new CCreateAdTimeBlockListAdapter(getActivity(), list);
        listView.setAdapter(adapter);

//        EditText metCompanyTitle = (EditText) header.findViewById(R.id.metCompanyTitle);
//        EditText metCompanyDescription = (EditText) header.findViewById(R.id.metCompanyDescription);
//        Button bCompanyCategory = (Button) header.findViewById(R.id.bCompanyCategory);
//
//        Button bAddTimeBlock = (Button) footer.findViewById(R.id.bAddTimeBlock);
//        EditText metServiceName = (EditText) footer.findViewById(R.id.metServiceName);
//        EditText metDiscountedPrice = (EditText) footer.findViewById(R.id.metDiscountedPrice);
//        EditText metOriginalPrice = (EditText) footer.findViewById(R.id.metOriginalPrice);
//        EditText metAdDuration = (EditText) footer.findViewById(R.id.metAdDuration);
//        EditText metAdTimeNotice = (EditText) footer.findViewById(R.id.metAdTimeNotice);
//        Button bNext = (Button) footer.findViewById(R.id.bNext);

//        bAddTimeBlock.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                list.add(new Availabilities());
//                adapter.notifyDataSetChanged();
//            }
//        });

        return rootView;
    }
}
