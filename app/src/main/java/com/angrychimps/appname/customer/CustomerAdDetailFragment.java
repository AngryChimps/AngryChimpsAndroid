package com.angrychimps.appname.customer;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.R;
import com.angrychimps.appname.VolleySingleton;
import com.angrychimps.appname.models.Address;
import com.angrychimps.appname.models.ProviderAdImmutableGetResponsePayload;
import com.angrychimps.appname.widgets.AnimatedNetworkImageView;
import com.bluelinelabs.logansquare.LoganSquare;
import com.etsy.android.grid.StaggeredGridView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.relex.circleindicator.CircleIndicator;


public class CustomerAdDetailFragment extends Fragment {

    private JsonObjectRequest request;
    private Address address;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_ad_detail, container, false);

        MainActivity.setMenu(R.menu.menu_ad_detail);

        final StaggeredGridView gridViewAdDetail = (StaggeredGridView) rootView.findViewById(R.id.gridViewAdDetail);
        final ViewGroup header = (ViewGroup) inflater.inflate(R.layout.ad_detail_header, gridViewAdDetail, false);
        gridViewAdDetail.addHeaderView(header, null, false);

        final ViewPager pager = (ViewPager) header.findViewById(R.id.viewPagerAdDetailImage);
        final TextView tvAdDetailCompanyTagLine = (TextView) header.findViewById(R.id.tvAdDetailCompanyTagLine);
        final TextView tvAdDetailCompanyDetails = (TextView) header.findViewById(R.id.tvAdDetailCompanyDetails);
        final AnimatedNetworkImageView mapAdDetail = (AnimatedNetworkImageView) header.findViewById(R.id.mapAdDetail);

        String coordinates = getArguments().getDouble("lat")+","+ getArguments().getDouble("lon");
        String color = "0x"+Integer.toHexString(getResources().getColor(R.color.primary)).substring(2);
        mapAdDetail.setImageUrl("https://maps.googleapis.com/maps/api/staticmap?center="+coordinates+
                "&zoom=15&size=340x200"+"&markers=color:"+color+"%7C"+coordinates+
                "&scale=2&format=jpeg",VolleySingleton.getInstance().getImageLoader());
        mapAdDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNavigation();
            }
        });

        final ImageButton bAdDetailCall = (ImageButton) header.findViewById(R.id.bAdDetailCall);
        final TextView tvAdDetailCompanyName = (TextView) header.findViewById(R.id.tvAdDetailCompanyName);
        final TextView tvAdDetailCompanyAddress = (TextView) header.findViewById(R.id.tvAdDetailCompanyAddress);
        final TextView tvAdDetailCompanyDistance = (TextView) header.findViewById(R.id.tvAdDetailCompanyDistance);
        final RatingBar ratingBarAdDetail = (RatingBar) header.findViewById(R.id.ratingBarAdDetail);
        final Button bAdDetailReviews = (Button) header.findViewById(R.id.bAdDetailReviews);
        final TextView tvAdDetailFlagListing = (TextView) header.findViewById(R.id.tvAdDetailFlagListing);

        request = new JsonObjectRequest(Request.Method.GET, MainActivity.url + "providerAdImmutable/" +
                this.getArguments().getString("id"), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject object) {
                Log.i(null, "Response received");

                try {
                    JSONObject payload = new JSONObject(object.getJSONObject("payload").toString());

                    Log.i("ad detail payload = ", payload.toString());
                    Log.i("inner payload = ", payload.getJSONObject("payload").toString());
                    ProviderAdImmutableGetResponsePayload result = LoganSquare.parse(payload.getJSONObject("payload").toString(), ProviderAdImmutableGetResponsePayload.class);

                    MainActivity.setToolbarTitle(result.getCompany().getName());

                    pager.setAdapter(new ViewPagerAdapter(getActivity(), result.getPhotos()));
                    CircleIndicator indicator = (CircleIndicator) header.findViewById(R.id.circleIndicatorAdDetail);
                    indicator.setViewPager(pager);
                    if (result.getPhotos().size() > 1) indicator.setVisibility(View.VISIBLE);

                    tvAdDetailCompanyTagLine.setText(result.getTitle());
                    tvAdDetailCompanyDetails.setText(result.getDescription());

                    //Make text fade out if too long. Click to make visible
                    if (tvAdDetailCompanyDetails.length() > 280) {
                        //Shader makes the text fade out toward the bottom
                        final Shader textShader = new LinearGradient(0, tvAdDetailCompanyDetails.getLineHeight() * 4, 0, 0, new int[]{Color.TRANSPARENT, Color.BLACK},
                                new float[]{0, 1}, Shader.TileMode.CLAMP);
                        tvAdDetailCompanyDetails.getPaint().setShader(textShader);
                        tvAdDetailCompanyDetails.setMaxLines(4);
                        tvAdDetailCompanyDetails.setOnClickListener(new View.OnClickListener() {
                            ObjectAnimator animation;
                            boolean isExpanded = false;

                            @Override
                            public void onClick(View v) {
                                if (isExpanded) {
                                    tvAdDetailCompanyDetails.getPaint().setShader(null);
                                    animation = ObjectAnimator.ofInt(tvAdDetailCompanyDetails, "maxLines", 30);
                                    animation.setDuration(100).start();
                                } else {
                                    animation = ObjectAnimator.ofInt(tvAdDetailCompanyDetails, "maxLines", 4);
                                    animation.setDuration(100).start();
                                    tvAdDetailCompanyDetails.getPaint().setShader(textShader);
                                }
                                isExpanded = !isExpanded;
                            }
                        });
                    }

                    LinearLayout serviceItem = (LinearLayout) header.findViewById(R.id.serviceItem);
                    CustomerAdDetailAdapter adapter = new CustomerAdDetailAdapter(getActivity(), result.getServices());
                    for (int i = 0; i < adapter.getCount(); i++) {
                        View item = adapter.getView(i, null, null);
                        serviceItem.addView(item);
                    }
                    address = result.getAddress();
                    tvAdDetailCompanyName.setText(result.getCompany().getName());
                    tvAdDetailCompanyName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            launchNavigation();
                        }
                    });

                    String street2 = "";
                    if (address.getStreet2() != null) street2 = address.getStreet2() + "\n";
                    tvAdDetailCompanyAddress.setText(address.getStreet1() + "\n" + street2 + address.getCity() +
                            ", " + address.getState() + " " + address.getZip());
                    tvAdDetailCompanyAddress.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            launchNavigation();
                        }
                    });
                    tvAdDetailCompanyDistance.setText(String.format("%.1f", getArguments().getDouble("distance")) + " miles");

                    ratingBarAdDetail.setRating(result.getRating());
                    bAdDetailReviews.setText(result.getRating_count() + " Reviews");

                } catch (JSONException e) {
                    Log.i(null, "JsonObjectRequest error");
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VOLLEY ERROR", "error => " + error.toString());
                    }
                }
        ) {
            // Attach headers
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("angrychimps-api-session-token", MainActivity.sessionId);
                return params;
            }
        };
        VolleySingleton.getInstance().addToRequestQueue(request);

        JsonRequestObjectBuilder object = new JsonRequestObjectBuilder(getActivity());
        StaggeredGridViewBuilder builder = new StaggeredGridViewBuilder(getActivity(), getFragmentManager(), gridViewAdDetail, object.getJsonObject());
        builder.getResults();

        return rootView;
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
        } catch (JSONException e) {
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

    @Override
    public void onStop() {
        super.onStop();
        request.cancel();
    }

    private void launchNavigation() {
        if(address != null) {
            // Create a Uri from an intent string. Use the result to create an Intent.
            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + address.getStreet1() + " " + address.getCity() +
                    ", " + address.getState() + " " + address.getZip());

            // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

            // Make the Intent explicit by setting the Google Maps package
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }
    }
}
