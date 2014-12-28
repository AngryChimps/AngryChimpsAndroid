package com.angrychimps.appname.provider;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.angrychimps.appname.R;

public class ProviderMainFragment extends ListFragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            //ArrayAdapter<CompanyListing> adapter = new AdFlowArrayAdapter(getActivity(), getCompanies());

            //setListAdapter(adapter);
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
    //private ArrayList<CompanyListing> getCompanies() {
//        ArrayList<CompanyListing> list = new ArrayList<CompanyListing>();
//        list.add(new CompanyListing(null, "We cut your hair", "Hair Company \n" +
//                "123 Main St \nSan Francisco, CA 94110", "Tomorrow 9:30-12:00pm (+2 more)"));
//        list.add(new CompanyListing(null, "We're the best!", "Haircut Express \n" +
//                "123 Harrison St \nSan Francisco, CA 94112", "Tomorrow 9:30-12:00pm"));
//        list.add(new CompanyListing(null, "Best deals in town", "Cheap Hairdos \n" +
//                "123 Folsom St \nSan Francisco, CA 94114", "Tomorrow 9:30-12:00pm"));
//        list.add(new CompanyListing(null, "Half off hair!", "Barber Man \n" +
//                "123 12th St \nSan Francisco, CA 94112", "Tomorrow 9:30-12:00pm"));
        //return list;
    //}

}
