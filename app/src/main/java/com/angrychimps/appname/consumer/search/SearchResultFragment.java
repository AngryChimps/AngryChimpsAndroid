package com.angrychimps.appname.consumer.search;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.angrychimps.appname.R;

import java.util.ArrayList;


public class SearchResultFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_result, container, false);

        ArrayAdapter<SearchResultItem> adapter = new SearchResultArrayAdapter(getActivity(), getCompanies());

        setListAdapter(adapter);
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

    // When a list item is clicked we want to change the hero info
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

    }

    private ArrayList<SearchResultItem> getCompanies() {
        ArrayList<SearchResultItem> list = new ArrayList<SearchResultItem>();
        list.add(new SearchResultItem(null, "We cut your hairs", "Hair company \nSan Francisco, CA 94110", "Tomorrow 9:30-12:00pm"));
        list.add(new SearchResultItem(null, "We're the best!", "Haircut Express \nSan Francisco, CA 94112", "Tomorrow 9:30-12:00pm"));
        return list;
    }
}
