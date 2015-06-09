package com.angrychimps.appname.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.android.volley.Request;
import com.angrychimps.appname.App;
import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.R;
import com.angrychimps.appname.adapters.MainRecyclerViewAdapter;
import com.angrychimps.appname.events.UpNavigationBurgerEvent;
import com.angrychimps.appname.interfaces.OnItemClickedListener;
import com.angrychimps.appname.interfaces.OnVolleyResponseListener;
import com.angrychimps.appname.models.SearchPostResponseResults;
import com.angrychimps.appname.server.JsonRequestObjectBuilder;
import com.angrychimps.appname.server.VolleyRequest;
import com.angrychimps.appname.utils.Otto;
import com.bluelinelabs.logansquare.LoganSquare;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;

/*
    The main fragment for Customer Mode.
 */

public class CMainFragment extends Fragment implements Toolbar.OnMenuItemClickListener, OnItemClickedListener, OnVolleyResponseListener {

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
        Otto.BUS.getBus().register(this);
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        JsonRequestObjectBuilder build = new JsonRequestObjectBuilder(getActivity());
        build.setLimit(20);
        requestObject = build.getJsonObject();
        adapter = new MainRecyclerViewAdapter(this, App.searchResults);
        recyclerView.setAdapter(adapter);

        Log.i(null, "requestObject == " + requestObject.toString());
        Log.i(null, "currentRequest == " + App.currentRequest.toString());

        if (App.searchResults.size() == 0 || !requestObject.toString().equals(App.currentRequest.toString())) {
            Log.i(null, "Loading data");
            new VolleyRequest(this).makeRequest(Request.Method.POST, "search", requestObject);
        }
    }

    @Override
    public void onItemClicked(int position) {
        Fragment fragment = new CAdDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", App.searchResults.get(position).getProvider_ad_immutable_id());
        bundle.putDouble("lat", App.searchResults.get(position).getLat());
        bundle.putDouble("lon", App.searchResults.get(position).getLon());
        bundle.putDouble("distance", App.searchResults.get(position).getDistance());
        fragment.setArguments(bundle);
        ((MainActivity) getActivity()).replaceFragmentAddBackStack(fragment);
    }

    @Override
    public void onVolleyResponse(JSONObject object) {
        try {
            JSONArray jArray = object.getJSONObject("payload").getJSONArray("results");
            for (int i = 0; i < jArray.length(); i++) {
                App.searchResults.add(LoganSquare.parse(jArray.get(i).toString(), SearchPostResponseResults.class));
                adapter.notifyItemInserted(i);
            }
            App.currentRequest = requestObject;

        } catch (IOException | JSONException e) {
            Log.i(null, "JsonObjectRequest error");
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Otto.BUS.getBus().unregister(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
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

