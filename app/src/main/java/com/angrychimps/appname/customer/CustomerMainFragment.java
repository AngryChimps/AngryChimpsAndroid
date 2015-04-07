package com.angrychimps.appname.customer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.R;
import com.angrychimps.appname.VolleySingleton;
import com.angrychimps.appname.models.SearchPostResponseResults;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.bluelinelabs.logansquare.LoganSquare;
import com.etsy.android.grid.StaggeredGridView;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ScrollDirectionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
    The main fragment for customer mode.
 */

public class CustomerMainFragment extends Fragment implements StaggeredGridView.OnItemClickListener{

    private CustomerMainGridArrayAdapter adapter;
    private ArrayList<SearchPostResponseResults> results;
    private StaggeredGridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gridView = (StaggeredGridView) getActivity().findViewById(R.id.gridViewMain);
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
                    if(adapter == null) adapter = new CustomerMainGridArrayAdapter(getActivity(), results);
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
                }
        ) {
            // Attach headers
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<>();
                params.put("angrychimps-api-session-token", MainActivity.sessionId);
                return params;
            }
        };
        VolleySingleton.getInstance().addToRequestQueue(request);


        //Set up the Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_request_white);
        fab.attachToListView(gridView, new ScrollDirectionListener() {
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
                CustomerCreateAdFragment fragment = new CustomerCreateAdFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(MainActivity.container.getId(), fragment).addToBackStack(null).commit();
                MainActivity.materialMenu.animateState(MaterialMenuDrawable.IconState.ARROW);
                ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle("Request Service");
            }
        });

        if (getActivity().getActionBar() != null) getActivity().getActionBar().setTitle("Browse Deals");
    }

    //JSONObject used in the Volley request- at a minimum, this passes location data
    //TODO- Add search filter options
    private JSONObject getRequestJSONObject() {
        //get current location
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        Log.i("latitude: ", "" + latitude);
        Log.i("longitude: ", "" + longitude);

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
            payload.put("lat", latitude);
            payload.put("lon", longitude);
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
        bundle.putString("id", results.get(position).getProvider_ad_immutable_id());
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(MainActivity.container.getId(), fragment).addToBackStack(null).commit();
        MainActivity.materialMenu.animateState(MaterialMenuDrawable.IconState.ARROW);
    }
}

