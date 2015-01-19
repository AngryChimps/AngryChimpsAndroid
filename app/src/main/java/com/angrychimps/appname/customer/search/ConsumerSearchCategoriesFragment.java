package com.angrychimps.appname.customer.search;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.angrychimps.appname.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConsumerSearchCategoriesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_categories, container, false);

        ArrayList<String> listDataHeader = new ArrayList<>();
        HashMap<String, List<String>> listDataChild = new HashMap<>();
        String[][] data = {{"Cleaning", "Plumbing", "Roofing"},{"Dentists", "Pediatricians"},{"Hair Salons", "Nail Salons", "Tanning"}};

        listDataHeader.add("Home Needs");
        listDataHeader.add("Health");
        listDataHeader.add("Beauty");

        listDataChild.put(listDataHeader.get(0), Arrays.asList(data[0]));
        listDataChild.put(listDataHeader.get(1), Arrays.asList(data[1]));
        listDataChild.put(listDataHeader.get(2), Arrays.asList(data[2]));

        ExpandableListAdapter listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        ExpandableListView expListView = (ExpandableListView) rootView.findViewById(R.id.searchCategoriesExpandableListView);
        expListView.setAdapter(listAdapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    // Called every time the screen orientation changes or Android kills an Activity
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

}
