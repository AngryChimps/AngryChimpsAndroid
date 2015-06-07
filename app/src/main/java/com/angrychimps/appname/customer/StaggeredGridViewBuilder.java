package com.angrychimps.appname.customer;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.VolleySingleton;
import com.angrychimps.appname.models.SearchPostResponseResults;
import com.bluelinelabs.logansquare.LoganSquare;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class StaggeredGridViewBuilder  {

    //private final StaggeredGridView gridView;
    private final Context context;
    private final FragmentManager fragmentManager;
    private final JSONObject requestObjectToSend;
    private StaggeredGridViewAdapter adapter;

    public StaggeredGridViewBuilder(Context context, FragmentManager fragmentManager, View gridView, JSONObject requestObjectToSend) {
        //this.gridView = gridView;
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.requestObjectToSend = requestObjectToSend;
    }

    public void getResults(){
//        gridView.setOnItemClickListener(this);
//        gridView.setEnabled(false);
        if (MainActivity.searchResults!= null && MainActivity.searchResults.size() >0 && MainActivity.currentRequest != null
                && !requestObjectToSend.toString().equals(MainActivity.currentRequest.toString())){
            Log.i(null, "Used existing data");
            if (adapter == null) adapter = new StaggeredGridViewAdapter(context, MainActivity.searchResults);
//            gridView.setAdapter(adapter);
//            gridView.setEnabled(true);
        }else {
            MainActivity.searchResults = new ArrayList<>();

            //Request the data using Volley for the Staggered Grid View and parse it into SearchPostResponseResults objects
            //TODO- load more data when the user reaches the bottom of the list
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, MainActivity.url + "search", requestObjectToSend, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject object) {
                    Log.i(null, "Response received");
                    try {
                        JSONObject payload = new JSONObject(object.getJSONObject("payload").toString());
                        Log.i("object payload = ", payload.getString("results"));
                        JSONArray jArray = payload.getJSONArray("results");

                        for (int i = 0; i < jArray.length(); i++) {
                            MainActivity.searchResults.add(LoganSquare.parse(jArray.get(i).toString(), SearchPostResponseResults.class));
                        }
                        Log.i("results size = ", "" + MainActivity.searchResults.size());
                        if (adapter == null) adapter = new StaggeredGridViewAdapter(context, MainActivity.searchResults);
//                        gridView.setAdapter(adapter);
//                        gridView.setEnabled(true);
                        MainActivity.currentRequest = requestObjectToSend;

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
    }

    //@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//        int pos = position - gridView.getHeaderViewsCount();
//        CustomerAdDetailFragment fragment = new CustomerAdDetailFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("id", MainActivity.searchResults.get(pos).getProvider_ad_immutable_id());
//        bundle.putDouble("lat", MainActivity.searchResults.get(pos).getLat());
//        bundle.putDouble("lon", MainActivity.searchResults.get(pos).getLon());
//        bundle.putDouble("distance", MainActivity.searchResults.get(pos).getDistance());
//        fragment.setArguments(bundle);
//        fragmentManager.beginTransaction().replace(MainActivity.container.getId(), fragment).addToBackStack(null).commit();
//        MainActivity.materialMenu.animateState(MaterialMenuDrawable.IconState.ARROW);
    }
}
