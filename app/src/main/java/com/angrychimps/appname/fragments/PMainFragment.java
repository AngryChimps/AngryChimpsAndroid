package com.angrychimps.appname.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.angrychimps.appname.App;
import com.angrychimps.appname.R;

import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/*
    Note- nothing in here should be considered correct right now.
    TODO- copy CMainFragment over here and adapt it
 */

public class PMainFragment extends Fragment{

    @InjectView(R.id.recycler_view) RecyclerView recyclerView;
    @InjectView(R.id.fab) FloatingActionButton fab;
    @InjectView(R.id.toolbar) Toolbar toolbar;
    RecyclerView.Adapter adapter;
    FragmentManager fm;
    JSONObject requestObject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.toolbar_with_fab, container, false);
        ButterKnife.inject(this, rootView);
        App.getBus().register(this);

        toolbar.setTitle("Browse Deals");
        fab.setImageResource(R.drawable.ic_add_white_24dp);

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
}

