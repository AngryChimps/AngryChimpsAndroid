package com.angrychimps.appname.fragments;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.angrychimps.appname.R;
import com.angrychimps.appname.adapters.CAdDetailAdapter;
import com.angrychimps.appname.adapters.ViewPagerPhotoAdapter;
import com.angrychimps.appname.interfaces.OnVolleyResponseListener;
import com.angrychimps.appname.models.Address;
import com.angrychimps.appname.models.ProviderAdImmutableGetResponsePayload;
import com.angrychimps.appname.server.JsonRequestObjectBuilder;
import com.angrychimps.appname.server.VolleyRequest;
import com.angrychimps.appname.widgets.FlexibleRatingBar;
import com.bluelinelabs.logansquare.LoganSquare;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import me.relex.circleindicator.CircleIndicator;


public class CAdDetailFragment extends Fragment implements OnVolleyResponseListener{

    private Address address;
    private ViewPager pager;
    private CircleIndicator indicator;
    private TextView tvCompanyTagLine;
    private TextView tvCompanyDetails;
    private LinearLayout serviceItem;
    private TextView tvCompanyName;
    private TextView tvCompanyAddress;
    private TextView tvCompanyDistance;
    private FlexibleRatingBar ratingBar;
    private Button bReviews;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_c_ad_detail, container, false);

        //MainActivity.setMenu(R.menu.menu_ad_detail);

//        StaggeredGridView gridView = (StaggeredGridView) rootView.findViewById(R.id.gridViewAdDetail);
//        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header_c_ad_detail, gridView, false);
//        gridView.addHeaderView(header, null, false);

//        pager = (ViewPager) header.findViewById(R.id.viewPagerCompanyImages);
//        indicator = (CircleIndicator) header.findViewById(R.id.circleIndicator);
//        tvCompanyTagLine = (TextView) header.findViewById(R.id.tvCompanyTagLine);
//        tvCompanyDetails = (TextView) header.findViewById(R.id.tvCompanyDetails);
//        serviceItem = (LinearLayout) header.findViewById(R.id.serviceItem);
//        AnimatedNetworkImageView map = (AnimatedNetworkImageView) header.findViewById(R.id.map);
//        ImageButton bCallCompany = (ImageButton) header.findViewById(R.id.bCallCompany);
//        tvCompanyName = (TextView) header.findViewById(R.id.tvCompanyName);
//        tvCompanyAddress = (TextView) header.findViewById(R.id.tvCompanyAddress);
//        tvCompanyDistance = (TextView) header.findViewById(R.id.tvCompanyDistance);
//        ratingBar = (FlexibleRatingBar) header.findViewById(R.id.ratingBar);
//        bReviews = (Button) header.findViewById(R.id.bReviews);
//        TextView tvFlagListing = (TextView) header.findViewById(R.id.tvFlagListing);

        String coordinates = getArguments().getDouble("lat")+","+ getArguments().getDouble("lon");
        String color = "0x"+Integer.toHexString(getResources().getColor(R.color.primary)).substring(2);
//        map.setImageUrl("https://maps.googleapis.com/maps/api/staticmap?center=" + coordinates + "&zoom=15&size=340x200" + "&markers=color:"
//                + color + "%7C" + coordinates + "&scale=2&format=jpeg", VolleySingleton.getInstance().getImageLoader());
//        map.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                launchNavigation();
//            }
//        });

        new VolleyRequest(this).makeRequest(Request.Method.GET, "providerAdImmutable/" + this.getArguments().getString("id"));

        JsonRequestObjectBuilder object = new JsonRequestObjectBuilder(getActivity());
//        Deprecated_StaggeredGridViewBuilder builder = new Deprecated_StaggeredGridViewBuilder(getActivity(), getFragmentManager(), gridView, object.getJsonObject());
//        builder.getResults();

        return rootView;
    }

    @Override
    public void onVolleyResponse(JSONObject object) {
        try {
            ProviderAdImmutableGetResponsePayload result = LoganSquare.parse(object.getJSONObject("payload").getJSONObject("payload").toString(),
                    ProviderAdImmutableGetResponsePayload.class);

            //MainActivity.setToolbarTitle(result.getCompany().getName());

            pager.setAdapter(new ViewPagerPhotoAdapter(getActivity(), result.getPhotos()));
            indicator.setViewPager(pager);
            if (result.getPhotos().size() > 1) indicator.setVisibility(View.VISIBLE);
            tvCompanyTagLine.setText(result.getTitle());
            tvCompanyDetails.setText(result.getDescription());

            //Make text fade out if too long. Click to make visible
            if (tvCompanyDetails.length() > 280) {
                //Shader makes the text fade out toward the bottom
                final Shader textShader = new LinearGradient(0, tvCompanyDetails.getLineHeight() * 4, 0, 0, new int[]{Color.TRANSPARENT, Color.BLACK},
                        new float[]{0, 1}, Shader.TileMode.CLAMP);
                tvCompanyDetails.getPaint().setShader(textShader);
                tvCompanyDetails.setMaxLines(4);
                tvCompanyDetails.setOnClickListener(new View.OnClickListener() {
                    ObjectAnimator animation;
                    boolean isExpanded = false;

                    @Override
                    public void onClick(View v) {
                        if (isExpanded) {
                            tvCompanyDetails.getPaint().setShader(null);
                            animation = ObjectAnimator.ofInt(tvCompanyDetails, "maxLines", 30);
                            animation.setDuration(100).start();
                        } else {
                            animation = ObjectAnimator.ofInt(tvCompanyDetails, "maxLines", 4);
                            animation.setDuration(100).start();
                            tvCompanyDetails.getPaint().setShader(textShader);
                        }
                        isExpanded = !isExpanded;
                    }
                });
            }
            CAdDetailAdapter adapter = new CAdDetailAdapter(getActivity(), result.getServices());
            for (int i = 0; i < adapter.getCount(); i++) {
                View item = adapter.getView(i, null, null);
                serviceItem.addView(item);
            }
            address = result.getAddress();
            tvCompanyName.setText(result.getCompany().getName());
            tvCompanyName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    launchNavigation();
                }
            });

            String street2 = "";
            if (address.getStreet2() != null) street2 = address.getStreet2() + "\n";
            tvCompanyAddress.setText(address.getStreet1() + "\n" + street2 + address.getCity() +
                    ", " + address.getState() + " " + address.getZip());
            tvCompanyAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    launchNavigation();
                }
            });
            tvCompanyDistance.setText(String.format("%.1f", getArguments().getDouble("distance")) + " miles");

            ratingBar.setRating(result.getRating());
            bReviews.setText(result.getRating_count() + " Reviews");

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    private void launchNavigation() {
        if(address != null) {
            try {
                //If google maps is not installed on the device, throw exception
                getActivity().getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);

                // Create a Uri from an intent string. Use the result to create an Intent.
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + address.getStreet1() + " " + address.getCity() +
                        ", " + address.getState() + " " + address.getZip());

                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
            catch(PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
