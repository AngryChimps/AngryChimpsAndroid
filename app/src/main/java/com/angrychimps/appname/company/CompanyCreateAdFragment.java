package com.angrychimps.appname.company;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.R;
import com.angrychimps.appname.models.Availabilities;

import java.util.ArrayList;
import java.util.List;

/*
    First page when the user clicks to create an ad from the company main fragment
 */

public class CompanyCreateAdFragment extends Fragment {

    List<Availabilities> list;
    CompanyCreateAdTimeBlockListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company_create_ad, container, false);

        MainActivity.clearMenu();
        MainActivity.setToolbarTitle("Create an Ad");

        ListView listView = (ListView) rootView.findViewById(R.id.listViewCompanyCreateAdTimeBlock);
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.company_create_ad_header, listView, false);
        ViewGroup footer = (ViewGroup)inflater.inflate(R.layout.company_create_ad_footer, listView, false);
        listView.addHeaderView(header, null, false);
        listView.addFooterView(footer, null, false);

        list = new ArrayList<>();
        list.add(new Availabilities());
        adapter = new CompanyCreateAdTimeBlockListAdapter(getActivity(), list);
        listView.setAdapter(adapter);

        EditText metCompanyTitle = (EditText) header.findViewById(R.id.metCompanyTitle);
        EditText metCompanyDescription = (EditText) header.findViewById(R.id.metCompanyDescription);
        Button bCompanyCategory = (Button) header.findViewById(R.id.bCompanyCategory);

        Button bAddTimeBlock = (Button) footer.findViewById(R.id.bAddTimeBlock);
        EditText metServiceName = (EditText) footer.findViewById(R.id.metServiceName);
        EditText metDiscountedPrice = (EditText) footer.findViewById(R.id.metDiscountedPrice);
        EditText metOriginalPrice = (EditText) footer.findViewById(R.id.metOriginalPrice);
        EditText metAdDuration = (EditText) footer.findViewById(R.id.metAdDuration);
        EditText metAdTimeNotice = (EditText) footer.findViewById(R.id.metAdTimeNotice);
        Button bNext = (Button) footer.findViewById(R.id.bNext);

        bAddTimeBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(new Availabilities());
                adapter.notifyDataSetChanged();
            }
        });

        return rootView;
    }
}
