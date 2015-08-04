package com.angrychimps.citizenvet.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.angrychimps.citizenvet.MainActivity;
import com.angrychimps.citizenvet.R;
import com.angrychimps.citizenvet.events.UpNavigationBurgerEvent;
import com.angrychimps.citizenvet.utils.Otto;

import butterknife.ButterKnife;
import butterknife.Bind;

/*
    The main fragment for Provider Mode
 */

public class PMainFragment extends Fragment implements Toolbar.OnMenuItemClickListener{

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.toolbar_with_fab, container, false);
        //Add RecyclerView to container before injecting views
        FrameLayout innerContainer = (FrameLayout) rootView.findViewById(R.id.innerContainer);
        inflater.inflate(R.layout.recycler_view, innerContainer);
        ButterKnife.bind(this, rootView);

        toolbar.setTitle("Browse Requests");
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Otto.BUS.getBus().post(new UpNavigationBurgerEvent());
            }
        });
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL); //columns,orientation
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemViewCacheSize(0); //Default image caching causes gaps to form and image loading failures

        fab.setImageResource(R.drawable.ic_add_white_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).replaceFragmentAddBackStack(new PCreateAdFragment());
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Otto.BUS.getBus().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Otto.BUS.getBus().unregister(this);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        // Handle action bar item clicks here.
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case R.id.action_map:
                ((MainActivity) getActivity()).replaceFragmentAddBackStack(new PMapFragment());
                return true;
            case R.id.action_filter:
                return true;
        }
        return false;
    }
}
