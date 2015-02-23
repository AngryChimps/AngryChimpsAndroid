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

import com.angrychimps.appname.AdFlowGridArrayAdapter;
import com.angrychimps.appname.CompanyListing;
import com.angrychimps.appname.JSONParser;
import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.R;
import com.angrychimps.appname.fab.FloatingActionButton;
import com.angrychimps.appname.fab.ScrollDirectionListener;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;

public class CompanyMainFragment extends Fragment implements AbsListView.OnScrollListener, AbsListView.OnItemClickListener{

    private static final String TAG = "StaggeredGridActivity";
    private static final String SAVED_DATA_KEY = "SAVED_DATA";
    private boolean mHasRequestedMore;
    private AdFlowGridArrayAdapter mAdapter;
    private ArrayList<String> mData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //Get company listings- possibly move this into the adapter?
        JSONParser parser = new JSONParser();
        parser.getSearch();

        StaggeredGridView mGridView = (StaggeredGridView) getActivity().findViewById(R.id.grid_view);
        mAdapter = new AdFlowGridArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, getCompanies());

        // do we have saved data?
        if (savedInstanceState != null) mData = savedInstanceState.getStringArrayList(SAVED_DATA_KEY);
        if (mData == null) mData = generateData();
        for (String data : mData) mAdapter.add(data);

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
                MainActivity.materialMenu.animateState(MaterialMenuDrawable.IconState.ARROW);

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



    private ArrayList<CompanyListing> getCompanies() {
        ArrayList<CompanyListing> list = new ArrayList<>();
//
//        list.add(new CompanyListing(null, "We cut your hair", "Hair Company \n" +
//                "123 Main St \nSan Francisco, CA 94110", "Tomorrow 9:30-12:00pm"));

        return list;
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
                onLoadMoreItems();
            }
        }
    }
    private void onLoadMoreItems() {
        final ArrayList<String> sampleData = generateData();
        for (String data : sampleData) {
            mAdapter.add(data);
        }
        // stash all the data in our backing store
        mData.addAll(sampleData);
        // notify the adapter that we can update now
        mAdapter.notifyDataSetChanged();
        mHasRequestedMore = false;
    }
    private ArrayList<String> generateData() {
        ArrayList<String> listData = new ArrayList<>();
        listData.add("http://devvy.angrychimps.com/media/ci/d0f335bf6996890e45b4548740af4100.jpg");
        listData.add("http://devvy.angrychimps.com/media/ci/e5c840d404f9417e8d19891958125ff0.jpg");
        listData.add("http://i62.tinypic.com/2iitkhx.jpg");
        listData.add("http://i61.tinypic.com/w0omeb.jpg");
        listData.add("http://devvy.angrychimps.com/media/ci/d0f335bf6996890e45b4548740af4100.jpg");
        listData.add("http://i60.tinypic.com/w9iu1d.jpg");
        listData.add("http://i62.tinypic.com/2iitkhx.jpg");
        listData.add("http://devvy.angrychimps.com/media/ci/d0f335bf6996890e45b4548740af4100.jpg");
        listData.add("http://devvy.angrychimps.com/media/ci/e5c840d404f9417e8d19891958125ff0.jpg");
        listData.add("http://i60.tinypic.com/iw6kh2.jpg");
        listData.add("http://i57.tinypic.com/ru08c8.jpg");
        listData.add("http://devvy.angrychimps.com/media/ci/d0f335bf6996890e45b4548740af4100.jpg");
        listData.add("http://i60.tinypic.com/k12r10.jpg");
        listData.add("http://devvy.angrychimps.com/media/ci/e5c840d404f9417e8d19891958125ff0.jpg");
        listData.add("http://i58.tinypic.com/2e3daug.jpg");
        listData.add("http://i59.tinypic.com/2igznfr.jpg");
        listData.add("http://devvy.angrychimps.com/media/ci/e5c840d404f9417e8d19891958125ff0.jpg");
        listData.add("http://i62.tinypic.com/2iitkhx.jpg");
        return listData;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
    }
}

