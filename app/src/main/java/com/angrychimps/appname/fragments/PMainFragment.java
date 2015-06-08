package com.angrychimps.appname.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.angrychimps.appname.App;
import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.R;
import com.angrychimps.appname.events.BackPressedEvent;
import com.angrychimps.appname.events.UpNavigationBurgerEvent;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.otto.Subscribe;

import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/*
    The main fragment for Provider Mode
 */

public class PMainFragment extends Fragment implements Toolbar.OnMenuItemClickListener{

    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.fab) FloatingActionButton fab;
    @InjectView(R.id.recycler_view) RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    JSONObject requestObject;
    FragmentManager fm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.toolbar_with_fab, container, false);
        //Add RecyclerView to container before injecting views
        FrameLayout innerContainer = (FrameLayout) rootView.findViewById(R.id.innerContainer);
        inflater.inflate(R.layout.recycler_view, innerContainer);
        ButterKnife.inject(this, rootView);
        App.getBus().register(this);
        fm = getFragmentManager();

        setToolbar();

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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        App.getBus().unregister(this);
    }

    private void setToolbar() {
        toolbar.getMenu().clear();
        toolbar.setTitle("Browse Deals");
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getBus().post(new UpNavigationBurgerEvent());
            }
        });
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        // Handle action bar item clicks here.
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case R.id.action_map:
                SupportMapFragment mapFragment = SupportMapFragment.newInstance();
                fm.beginTransaction().replace(R.id.innerContainer, mapFragment).addToBackStack(null).commit();
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(final GoogleMap map) {
                        toolbar.getMenu().clear();
                        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
                        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setToolbar();
                                fm.popBackStack();
                            }
                        });
                        toolbar.setTitle("Map");
                        toolbar.inflateMenu(R.menu.menu_map);

                        LatLng currentPosition = new LatLng(App.getLatitude(), App.getLongitude());
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 13));
                        map.addMarker(new MarkerOptions().position(currentPosition));

                        for (int i = 0; i < App.searchResults.size(); i++) {
                            map.addMarker(new MarkerOptions().position(new LatLng(App.searchResults.get(i).getLat(),
                                    App.searchResults.get(i).getLon())).icon(BitmapDescriptorFactory.defaultMarker(207)));
                        }
                    }
                });
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
        }
        return false;
    }

    @Subscribe
    public void onBackPressed(BackPressedEvent event){
        setToolbar();
    }
}

