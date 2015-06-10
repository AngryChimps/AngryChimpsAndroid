package com.angrychimps.appname.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.R;
import com.angrychimps.appname.adapters.MainRecyclerViewAdapter;
import com.angrychimps.appname.events.ResultChangedEvent;
import com.angrychimps.appname.events.ResultInsertedEvent;
import com.angrychimps.appname.events.ResultMovedEvent;
import com.angrychimps.appname.events.ResultRemovedEvent;
import com.angrychimps.appname.events.UpNavigationBurgerEvent;
import com.angrychimps.appname.interfaces.OnItemClickedListener;
import com.angrychimps.appname.models.SearchPostResponseResults;
import com.angrychimps.appname.utils.Otto;
import com.squareup.otto.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;

/*
    The main fragment for Customer Mode.
 */

public class CMainFragment extends Fragment implements Toolbar.OnMenuItemClickListener, OnItemClickedListener {

    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.fab) FloatingActionButton fab;
    @InjectView(R.id.recycler_view) RecyclerView recyclerView;
    SortedList<SearchPostResponseResults> searchResults;
    RecyclerView.Adapter adapter;
    FragmentManager fm;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.toolbar_with_fab, container, false);
        //Add RecyclerView to container before injecting views
        FrameLayout innerContainer = (FrameLayout) rootView.findViewById(R.id.innerContainer);
        inflater.inflate(R.layout.recycler_view, innerContainer);
        ButterKnife.inject(this, rootView);
        fm = getFragmentManager();

        toolbar.getMenu().clear();
        toolbar.setTitle("Browse Deals");
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Otto.BUS.getBus().post(new UpNavigationBurgerEvent());
            }
        });
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);

        fab.setImageResource(R.drawable.ic_request_white_24dp);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL); //columns,orientation
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemViewCacheSize(0); //Default image caching causes gaps to form and image loading failures
//        DefaultItemAnimator animator = new DefaultItemAnimator();
//        recyclerView.setItemAnimator(animator);

        return rootView;
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        searchResults = ((MainActivity) getActivity()).getSearchResults();
        adapter = new MainRecyclerViewAdapter(this, searchResults);
        recyclerView.setAdapter(adapter);

    }

    @Subscribe public void onResultChanged(ResultChangedEvent event){
        adapter.notifyItemRangeChanged(event.position, event.count);
    }

    @Subscribe public void onResultInserted(ResultInsertedEvent event){
        adapter.notifyItemRangeInserted(event.position, event.count);
    }

    @Subscribe public void onResultMoved(ResultMovedEvent event){
        adapter.notifyItemMoved(event.fromPosition, event.toPosition);
    }

    @Subscribe public void onResultRemoved(ResultRemovedEvent event){
        adapter.notifyItemRangeRemoved(event.position, event.count);
    }

    @Override public void onItemClicked(int position) {
        Fragment fragment = new CAdDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", searchResults.get(position).getProvider_ad_immutable_id());
        bundle.putDouble("lat", searchResults.get(position).getLat());
        bundle.putDouble("lon", searchResults.get(position).getLon());
        bundle.putDouble("distance", searchResults.get(position).getDistance());
        fragment.setArguments(bundle);
        ((MainActivity) getActivity()).replaceFragmentAddBackStack(fragment);
    }


    @Override public void onStart() {
        super.onStart();
        Otto.BUS.getBus().register(this);
    }

    @Override public void onStop() {
        super.onStop();
        Otto.BUS.getBus().unregister(this);
    }

    @Override public boolean onMenuItemClick(MenuItem item) {
        // Handle action bar item clicks here.
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case R.id.action_map:
                ((MainActivity) getActivity()).replaceFragmentAddBackStack(new CMapFragment());
                return true;
            case R.id.action_filter:
//                FragmentTransaction ft = fm.beginTransaction();
//                Fragment prev = fm.findFragmentByTag("dialog");
//                if (prev != null) ft.remove(prev);
//                ft.addToBackStack(null);
//
//                // Create and show the dialog.
//                CFilterFragment fragment = new CFilterFragment();
//                fragment.show(ft, "dialog");
                return true;
//            case R.id.action_favorite:
//                if (!isFavorite) {
//                    item.setIcon(R.drawable.ic_favorite_white_24dp);
//                } else item.setIcon(R.drawable.ic_favorite_outline_white_24dp);
//                isFavorite = !isFavorite;
//                return true;
        }
        return false;
    }
}

