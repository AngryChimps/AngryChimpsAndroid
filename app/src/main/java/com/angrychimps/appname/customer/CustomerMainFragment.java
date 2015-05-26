package com.angrychimps.appname.customer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.R;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.etsy.android.grid.StaggeredGridView;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ScrollDirectionListener;

/*
    The main fragment for customer mode.
 */

public class CustomerMainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        JsonRequestObjectBuilder object = new JsonRequestObjectBuilder(getActivity());
        StaggeredGridView gridView = (StaggeredGridView) getActivity().findViewById(R.id.gridViewMain);
        StaggeredGridViewBuilder builder = new StaggeredGridViewBuilder(getActivity(), getFragmentManager(), gridView, object.getJsonObject());
        builder.getResults();

        //Set up the Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_request_white_24dp);
        fab.attachToListView(gridView, new ScrollDirectionListener() {
            @Override
            public void onScrollDown() {
                Log.d("ListViewFragment", "onScrollDown()");
            }

            @Override
            public void onScrollUp() {
                Log.d("ListViewFragment", "onScrollUp()");
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerCreateAdFragment fragment = new CustomerCreateAdFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(MainActivity.container.getId(), fragment).addToBackStack(null).commit();
                MainActivity.materialMenu.animateState(MaterialMenuDrawable.IconState.ARROW);
                MainActivity.setToolbarTitle("Request Service");
            }
        });

        if (getActivity().getActionBar() != null) getActivity().getActionBar().setTitle("Browse Deals");
    }
}

