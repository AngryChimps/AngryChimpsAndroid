package com.angrychimps.appname.customer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.R;
import com.angrychimps.appname.VolleySingleton;
import com.angrychimps.appname.adapters.MainRecyclerViewAdapter;
import com.angrychimps.appname.interfaces.OnItemClickedListener;
import com.angrychimps.appname.models.SearchPostResponseResults;
import com.angrychimps.appname.utils.JsonRequestObjectBuilder;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.bluelinelabs.logansquare.LoganSquare;
import com.melnykov.fab.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
    The main fragment for customer mode.
 */

public class CustomerMainFragment extends Fragment implements OnItemClickedListener{

    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        fragmentManager = getFragmentManager();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL); //columns,orientation
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemViewCacheSize(0);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        JsonRequestObjectBuilder build = new JsonRequestObjectBuilder(getActivity());
        build.setLimit(20);
        final JSONObject requestObject = build.getJsonObject();
//        StaggeredGridView gridView = (StaggeredGridView) getActivity().findViewById(R.id.gridViewMain);
//        StaggeredGridViewBuilder builder = new StaggeredGridViewBuilder(getActivity(), getFragmentManager(), gridView, requestObject);
//        builder.getResults();
        recyclerViewAdapter = new MainRecyclerViewAdapter(this, MainActivity.searchResults);
        recyclerView.setAdapter(recyclerViewAdapter);

        Log.i(null, "requestObject == " + requestObject.toString());
        Log.i(null, "currentRequest == "+MainActivity.currentRequest.toString());

        if (MainActivity.searchResults.size() == 0 || !requestObject.toString().equals(MainActivity.currentRequest.toString())){
            Log.i(null, "Loading data");

            //Request the data using Volley for the Staggered Grid View and parse it into SearchPostResponseResults objects
            //TODO- load more data when the user reaches the bottom of the list
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, MainActivity.url + "search", requestObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject object) {
                    Log.i(null, "Response received");
                    try {
                        JSONObject payload = new JSONObject(object.getJSONObject("payload").toString());
                        Log.i("object payload = ", payload.getString("results"));
                        JSONArray jArray = payload.getJSONArray("results");

                        for (int i = 0; i < jArray.length(); i++) {
                            MainActivity.searchResults.add(LoganSquare.parse(jArray.get(i).toString(), SearchPostResponseResults.class));
                            recyclerViewAdapter.notifyItemInserted(i);
                        }
                        Log.i("results size = ", "" + MainActivity.searchResults.size());
                        MainActivity.currentRequest = requestObject;

                    } catch (IOException | JSONException e) {
                        Log.i(null, "JsonObjectRequest error");
                        e.printStackTrace();
                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("VOLLEY ERROR", "error => " + error.toString());
                        }
                    }) {
                // Attach headers
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("angrychimps-api-session-token", MainActivity.sessionId);
                    return params;
                }
            };
            VolleySingleton.getInstance().addToRequestQueue(request);
        }


        //Set up the Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_request_white_24dp);
        fab.attachToRecyclerView(recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerCreateAdFragment fragment = new CustomerCreateAdFragment();
                fragmentManager.beginTransaction().replace(MainActivity.container.getId(), fragment).addToBackStack(null).commit();
                MainActivity.materialMenu.animateState(MaterialMenuDrawable.IconState.ARROW);
                MainActivity.setToolbarTitle("Request Service");
            }
        });

        if (getActivity().getActionBar() != null) getActivity().getActionBar().setTitle("Browse Deals");
    }

    @Override
    public void onItemClicked(int position) {
        CustomerAdDetailFragment fragment = new CustomerAdDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", MainActivity.searchResults.get(position).getProvider_ad_immutable_id());
        bundle.putDouble("lat", MainActivity.searchResults.get(position).getLat());
        bundle.putDouble("lon", MainActivity.searchResults.get(position).getLon());
        bundle.putDouble("distance", MainActivity.searchResults.get(position).getDistance());
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(MainActivity.container.getId(), fragment).addToBackStack(null).commit();
        MainActivity.materialMenu.animateState(MaterialMenuDrawable.IconState.ARROW);
    }
}

