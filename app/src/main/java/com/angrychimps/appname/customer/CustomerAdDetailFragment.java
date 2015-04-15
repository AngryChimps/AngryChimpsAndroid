package com.angrychimps.appname.customer;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.R;
import com.angrychimps.appname.ViewPagerAdapter;
import com.angrychimps.appname.VolleySingleton;
import com.angrychimps.appname.models.ProviderAdImmutableGetResponsePayload;
import com.bluelinelabs.logansquare.LoganSquare;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.relex.circleindicator.CircleIndicator;


public class CustomerAdDetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_ad_detail, container, false);

        MainActivity.clearMenu();

        final ListView listView = (ListView) rootView.findViewById(R.id.listViewServices);
        final ViewGroup header = (ViewGroup)inflater.inflate(R.layout.ad_detail_header, listView, false);
        final ViewGroup footer = (ViewGroup)inflater.inflate(R.layout.ad_detail_footer, listView, false);
        listView.addHeaderView(header, null, false);
        listView.addFooterView(footer, null, false);

        final ViewPager pager = (ViewPager) rootView.findViewById(R.id.viewPagerAdDetailImage);
        final TextView tvCompanyTagLine = (TextView) rootView.findViewById(R.id.tvCompanyTagLine);
        final TextView tvCompanyDetails = (TextView) rootView.findViewById(R.id.tvCompanyDetails);
        //final View gradient = rootView.findViewById(R.id.gradient);
        //final MapView map = (MapView) rootView.findViewById(R.id.mapAdDetail);





        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, MainActivity.url + "providerAdImmutable/" +
                this.getArguments().getString("id") , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject object) {
                Log.i(null, "Response received");

                try {
                    JSONObject payload = new JSONObject(object.getJSONObject("payload").toString());

                    Log.i("ad detail payload = ", payload.toString());
                    Log.i("inner payload = ", payload.getJSONObject("payload").toString());
                    ProviderAdImmutableGetResponsePayload result = LoganSquare.parse(payload.getJSONObject("payload").toString(), ProviderAdImmutableGetResponsePayload.class);

                    ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(result.getCompany().getName());
                    CustomerAdDetailAdapter adapter = new CustomerAdDetailAdapter(getActivity(), result.getServices());
                    listView.setAdapter(adapter);

                    pager.setAdapter(new ViewPagerAdapter(getActivity(), result.getPhotos()));

                    CircleIndicator indicator = (CircleIndicator) rootView.findViewById(R.id.circleIndicatorAdDetail);
                    indicator.setViewPager(pager);
                    if(result.getPhotos().size() > 1) indicator.setVisibility(View.VISIBLE);

                    tvCompanyTagLine.setText(result.getTitle());
                    tvCompanyDetails.setText(result.getDescription());

                    //Make text fade out if too long. Click to make visible
                    if(tvCompanyDetails.length() > 280){

                        //Shader makes the text fade out toward the bottom
                        final Shader textShader=new LinearGradient(0, tvCompanyDetails.getLineHeight()*4, 0, 0, new int[]{Color.TRANSPARENT,Color.BLACK},
                                new float[]{0, 1}, Shader.TileMode.CLAMP);
                        tvCompanyDetails.getPaint().setShader(textShader);

                        tvCompanyDetails.setMaxLines(4);
                        tvCompanyDetails.setOnClickListener(new View.OnClickListener() {
                            ObjectAnimator animation;
                            boolean isExpanded = false;
                            @Override
                            public void onClick(View v) {
                                if(isExpanded) {
                                    tvCompanyDetails.getPaint().setShader(null);
                                    animation = ObjectAnimator.ofInt(tvCompanyDetails, "maxLines", 100);
                                    animation.setDuration(125).start();
                                }else{
                                    animation = ObjectAnimator.ofInt(tvCompanyDetails, "maxLines", 4);
                                    animation.setDuration(125).start();
                                    tvCompanyDetails.getPaint().setShader(textShader);
                                }
                                isExpanded = !isExpanded;
                            }
                        });
                    }

                } catch (JSONException e) {
                    Log.i(null, "JsonObjectRequest error");
                    e.printStackTrace();
                } catch (IOException e) {
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

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private JSONObject getRequestJSONObject() {

        JSONObject payload = new JSONObject();
        JSONObject object = new JSONObject();
        try {
            //jsonString = LoganSquare.serialize(locationData);
            //Log.i("jsonString = ", jsonString);
//            payload.put("lat", latitude);
//            payload.put("lon", longitude);
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
    public void onResume() {
        super.onResume();
        MainActivity.setToolbarTranslucent();
    }

    @Override
    public void onPause() {
        super.onPause();
        MainActivity.setToolbarOpaque();
    }
}
