package com.angrychimps.appname.company;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.angrychimps.appname.CompanyAdFlowGridArrayAdapter;
import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.R;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.etsy.android.grid.StaggeredGridView;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ScrollDirectionListener;

import java.util.ArrayList;

public class CompanyMainFragment extends Fragment implements AbsListView.OnScrollListener, AbsListView.OnItemClickListener{

    private static final String TAG = "StaggeredGridActivity";
    private static final String SAVED_DATA_KEY = "SAVED_DATA";
    private boolean mHasRequestedMore;
    private CompanyAdFlowGridArrayAdapter mAdapter;
    private ArrayList<String> mData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




        StaggeredGridView mGridView = (StaggeredGridView) getActivity().findViewById(R.id.grid_view);
        //mAdapter = new CompanyAdFlowGridArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, getCompanies());



        mGridView.setAdapter(mAdapter);
        mGridView.setOnScrollListener(this);
        mGridView.setOnItemClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_white);
        fab.attachToListView(mGridView, new ScrollDirectionListener() {
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
                fragment.setTargetFragment(getParentFragment(), 0);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(MainActivity.mContainer.getId(), fragment).addToBackStack(null).commit();
                MainActivity.sMaterialMenu.animateState(MaterialMenuDrawable.IconState.ARROW);

            }
        });



        if (getActivity().getActionBar() != null) getActivity().getActionBar().setTitle("Browse Deals");


    }

    // Called every time the screen orientation changes or Android kills an Activity to conserve resources
    // We save the last item selected in the list here and attach it to the key curChoice
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(SAVED_DATA_KEY, mData);
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
        if (!mHasRequestedMore) {
            int lastInScreen = firstVisibleItem + visibleItemCount;
            if (lastInScreen >= totalItemCount) {
                Log.d(TAG, "onScroll lastInScreen - so load more");
                mHasRequestedMore = true;
            }
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
    }
}

