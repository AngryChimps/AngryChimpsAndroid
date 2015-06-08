package com.angrychimps.appname.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.R;
import com.angrychimps.appname.adapters.MainRecyclerViewAdapter;
import com.angrychimps.appname.interfaces.OnItemClickedListener;
import com.angrychimps.appname.interfaces.OnVolleyResponseListener;
import com.angrychimps.appname.models.SearchPostResponseResults;
import com.angrychimps.appname.utils.JsonRequestObjectBuilder;
import com.angrychimps.appname.utils.VolleyRequest;
import com.bluelinelabs.logansquare.LoganSquare;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/*
    The main fragment for customer mode.
 */

public class CMainFragment extends Fragment implements OnItemClickedListener, OnVolleyResponseListener {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    FragmentManager fm;
    JSONObject requestObject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        fm = getFragmentManager();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL); //columns,orientation
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemViewCacheSize(0); //Default image caching causes gaps to form and image loading failures
//        DefaultItemAnimator animator = new DefaultItemAnimator();
//        Log.i(null, "animator add duration == "+animator.getAddDuration());
//        Log.i(null, "animator change duration == "+animator.getChangeDuration());
//        Log.i(null, "animator move duration == " + animator.getMoveDuration());
//        Log.i(null, "animator remove duration == " + animator.getRemoveDuration());
//        recyclerView.setItemAnimator(animator);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        JsonRequestObjectBuilder build = new JsonRequestObjectBuilder(getActivity());
        build.setLimit(20);
        requestObject = build.getJsonObject();
        adapter = new MainRecyclerViewAdapter(this, MainActivity.searchResults);
        recyclerView.setAdapter(adapter);

        Log.i(null, "requestObject == " + requestObject.toString());
        Log.i(null, "currentRequest == " + MainActivity.currentRequest.toString());

        if (MainActivity.searchResults.size() == 0 || !requestObject.toString().equals(MainActivity.currentRequest.toString())) {
            Log.i(null, "Loading data");
            new VolleyRequest(this).makeRequest(Request.Method.POST, "search", requestObject);
        }

        //Set up the Floating Action Button
//        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
//        fab.setImageResource(R.drawable.ic_request_white_24dp);
//        fab.attachToRecyclerView(recyclerView);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Deprecated_CRequestServiceFragment fragment = new Deprecated_CRequestServiceFragment();
//                fm.beginTransaction().replace(MainActivity.container.getId(), fragment).addToBackStack(null).commit();
//                MainActivity.materialMenu.animateState(MaterialMenuDrawable.IconState.ARROW);
//                MainActivity.setToolbarTitle("Request Service");
//            }
//        });

        if (getActivity().getActionBar() != null) getActivity().getActionBar().setTitle("Browse Deals");
    }

    @Override
    public void onItemClicked(int position) {
        CAdDetailFragment fragment = new CAdDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", MainActivity.searchResults.get(position).getProvider_ad_immutable_id());
        bundle.putDouble("lat", MainActivity.searchResults.get(position).getLat());
        bundle.putDouble("lon", MainActivity.searchResults.get(position).getLon());
        bundle.putDouble("distance", MainActivity.searchResults.get(position).getDistance());
        fragment.setArguments(bundle);
        fm.beginTransaction().replace(MainActivity.container.getId(), fragment).addToBackStack(null).commit();
        //MainActivity.materialMenu.animateState(MaterialMenuDrawable.IconState.ARROW);
    }

    @Override
    public void onVolleyResponse(JSONObject object) {
        try {
            JSONArray jArray = object.getJSONObject("payload").getJSONArray("results");
            for (int i = 0; i < jArray.length(); i++) {
                MainActivity.searchResults.add(LoganSquare.parse(jArray.get(i).toString(), SearchPostResponseResults.class));
                adapter.notifyItemInserted(i);
            }
            MainActivity.currentRequest = requestObject;

        } catch (IOException | JSONException e) {
            Log.i(null, "JsonObjectRequest error");
            e.printStackTrace();
        }
    }
}

