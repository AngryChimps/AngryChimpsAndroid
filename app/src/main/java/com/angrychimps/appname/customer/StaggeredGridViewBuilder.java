package com.angrychimps.appname.customer;

import android.content.Context;
import android.os.Bundle;
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
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.bluelinelabs.logansquare.LoganSquare;
import com.etsy.android.grid.StaggeredGridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StaggeredGridViewBuilder implements StaggeredGridView.OnItemClickListener{

    private StaggeredGridViewAdapter adapter;
    private ArrayList<SearchPostResponseResults> results;
    private StaggeredGridView gridView;
    private Context context;
    private FragmentManager fragmentManager;

    public StaggeredGridViewBuilder(Context context, FragmentManager fragmentManager, StaggeredGridView gridView) {
        this.gridView = gridView;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    public void getResults(){
        gridView.setOnItemClickListener(this);
        gridView.setEnabled(false);
        results = new ArrayList<>();

        //Request the data using Volley for the Staggered Grid View and parse it into SearchPostResponseResults objects
        //TODO- load more data when the user reaches the bottom of the list
        //TODO- Get rid of log messages, clean code, and add search filter options
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, MainActivity.url + "search", getRequestJSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject object) {
                Log.i(null, "Response received");
                try {
                    JSONObject payload = new JSONObject(object.getJSONObject("payload").toString());
                    Log.i("object payload = ", payload.getString("results"));
                    JSONArray jArray = payload.getJSONArray("results");

                    for (int i = 0; i < jArray.length(); i++){
                        results.add(LoganSquare.parse(jArray.get(i).toString(), SearchPostResponseResults.class));
                    }
                    Log.i("results size = ", ""+results.size());
                    if(adapter == null) adapter = new StaggeredGridViewAdapter(context, results);
                    gridView.setAdapter(adapter);
                    gridView.setEnabled(true);

                } catch (IOException | JSONException e) {
                    Log.i(null, "JsonObjectRequest error");
                    e.printStackTrace();
                }
            } },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VOLLEY ERROR", "error => " + error.toString());
                    }
                }) {
            // Attach headers
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<>();
                params.put("angrychimps-api-session-token", MainActivity.sessionId);
                return params;
            }
        };
        VolleySingleton.getInstance().addToRequestQueue(request);
    }

    private JSONObject getRequestJSONObject() {

        //get the current date and the data 14 days from now
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
//        df.getTimeZone();
//        String startDate = df.format(new Date());
//        Calendar c = Calendar.getInstance();
//        try {
//            c.setTime(df.parse(startDate));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        c.add(Calendar.DATE, 14);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
//        String endDate = df.format(c.getTime());
//        Log.i("startDate = ", startDate);
//        Log.i("endDate = ", endDate);

        //SearchPostRequestPayload locationData = new SearchPostRequestPayload(latitude, longitude);
        //String jsonString = null;
        JSONObject payload = new JSONObject();
        JSONObject object = new JSONObject();
        try {
            //jsonString = LoganSquare.serialize(locationData);
            //Log.i("jsonString = ", jsonString);
            payload.put("lat", MainActivity.getLocation(context).getLatitude());
            payload.put("lon", MainActivity.getLocation(context).getLongitude());
//            payload.put("starting_at", startDate);
//            payload.put("ending_at", endDate);
//            payload.put("offset", 0);
            object.put("payload", payload);
            Log.i("object = ", payload.toString());
            Log.i("object2 = ", object.toString());
        } catch ( JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CustomerAdDetailFragment fragment = new CustomerAdDetailFragment();
        Bundle bundle = new Bundle();
        Log.i("position = ", "" + position);
        bundle.putString("id", results.get(position).getProvider_ad_immutable_id());
        bundle.putDouble("lat", results.get(position).getLat());
        bundle.putDouble("lon", results.get(position).getLon());
        bundle.putDouble("distance", results.get(position).getDistance());
        Log.i("lon = ", "" + bundle.getDouble("lon"));
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(MainActivity.container.getId(), fragment).addToBackStack(null).commit();
        MainActivity.materialMenu.animateState(MaterialMenuDrawable.IconState.ARROW);
    }
}
