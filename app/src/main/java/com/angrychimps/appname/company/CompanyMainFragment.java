package com.angrychimps.appname.company;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.R;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.etsy.android.grid.StaggeredGridView;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ScrollDirectionListener;

/*
    Note- nothing in here should be considered correct right now.
    TODO- copy CustomerMainFragment over here and adapt it
 */

public class CompanyMainFragment extends Fragment implements AbsListView.OnScrollListener, AbsListView.OnItemClickListener{

    private static final String TAG = "StaggeredGridActivity";
    private boolean hasRequestedMore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        StaggeredGridView gridView = (StaggeredGridView) getActivity().findViewById(R.id.gridViewMain);
        //mAdapter = new CompanyAdFlowGridArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, getCompanies());

        //gridView.setAdapter(adapter);
        gridView.setOnScrollListener(this);
        gridView.setOnItemClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_white_24dp);
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

                CompanyCreateAdFragment fragment = new CompanyCreateAdFragment();
                //fragment.setTargetFragment(getParentFragment(), 0);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(MainActivity.container.getId(), fragment).addToBackStack(null).commit();
                MainActivity.materialMenu.animateState(MaterialMenuDrawable.IconState.ARROW);

            }
        });



        if (getActivity().getActionBar() != null) getActivity().getActionBar().setTitle("Browse Deals");


    }

    @Override
    public void onScrollStateChanged(final AbsListView view, final int scrollState) {
        Log.d(TAG, "onScrollStateChanged:" + scrollState);
    }
    @Override
    public void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {
        Log.d(TAG, "onScroll firstVisibleItem:" + firstVisibleItem +
                " visibleItemCount:" + visibleItemCount +
                " totalItemCount:" + totalItemCount);
        // our handling
        if (!hasRequestedMore) {
            int lastInScreen = firstVisibleItem + visibleItemCount;
            if (lastInScreen >= totalItemCount) {
                Log.d(TAG, "onScroll lastInScreen - so load more");
                hasRequestedMore = true;
            }
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
    }
}

