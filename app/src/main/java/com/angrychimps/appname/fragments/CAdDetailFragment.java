package com.angrychimps.appname.fragments;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.angrychimps.appname.R;
import com.angrychimps.appname.VolleySingleton;
import com.angrychimps.appname.adapters.CAdDetailAdapter;
import com.angrychimps.appname.adapters.ViewPagerPhotoAdapter;
import com.angrychimps.appname.events.UpNavigationBurgerEvent;
import com.angrychimps.appname.interfaces.OnVolleyResponseListener;
import com.angrychimps.appname.models.Address;
import com.angrychimps.appname.models.ProviderAdImmutableGetResponsePayload;
import com.angrychimps.appname.server.JsonRequestObject;
import com.angrychimps.appname.server.VolleyRequest;
import com.angrychimps.appname.utils.Otto;
import com.angrychimps.appname.widgets.AnimatedNetworkImageView;
import com.angrychimps.appname.widgets.FlexibleRatingBar;
import com.bluelinelabs.logansquare.LoganSquare;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.relex.circleindicator.CircleIndicator;


public class CAdDetailFragment extends Fragment implements Toolbar.OnMenuItemClickListener, OnVolleyResponseListener{

    private Address address;
    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.collapsing_toolbar) CollapsingToolbarLayout cToolbar;
    @InjectView(R.id.viewPagerCompanyImages) ViewPager pager;
    @InjectView(R.id.circleIndicator) CircleIndicator indicator;
    @InjectView(R.id.tvCompanyTagLine) TextView tvCompanyTagLine;
    @InjectView(R.id.tvCompanyDetails) TextView tvCompanyDetails;
    @InjectView(R.id.map) AnimatedNetworkImageView map;
    @InjectView(R.id.bCallCompany) ImageButton bCallCompany;
    @InjectView(R.id.tvCompanyName) TextView tvCompanyName;
    @InjectView(R.id.tvCompanyAddress) TextView tvCompanyAddress;
    @InjectView(R.id.tvCompanyDistance) TextView tvCompanyDistance;
    @InjectView(R.id.ratingBar) FlexibleRatingBar ratingBar;
    @InjectView(R.id.bReviews) Button bReviews;
    @InjectView(R.id.tvFlagListing) TextView tvFlagListing;
    RecyclerView.Adapter adapter;
    FragmentManager fm;
    boolean isFavorite;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.toolbar_collapsing, container, false);

        //Add RecyclerView to container before injecting views
        FrameLayout innerContainer = (FrameLayout) rootView.findViewById(R.id.innerContainer);
        inflater.inflate(R.layout.recycler_view, innerContainer);
        ButterKnife.inject(this, rootView);
        fm = getFragmentManager();

        toolbar.getMenu().clear();
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Otto.BUS.getBus().post(new UpNavigationBurgerEvent());
            }
        });
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);
//        serviceItem = (LinearLayout) header.findViewById(R.id.serviceItem);

        String coordinates = getArguments().getDouble("lat")+","+ getArguments().getDouble("lon");
        String color = "0x"+Integer.toHexString(getResources().getColor(R.color.primary)).substring(2);
        map.setImageUrl("https://maps.googleapis.com/maps/api/staticmap?center=" + coordinates + "&zoom=15&size=340x200" + "&markers=color:"
                + color + "%7C" + coordinates + "&scale=2&format=jpeg", VolleySingleton.INSTANCE.getImageLoader());
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNavigation();
            }
        });

        new VolleyRequest(getActivity()).makeRequest(Request.Method.GET, "providerAdImmutable/" + this.getArguments().getString("id"));

        return rootView;
    }

    @Override
    public void onVolleyResponse(JSONObject object) {
        try {
            ProviderAdImmutableGetResponsePayload result = LoganSquare.parse(object.getJSONObject("payload").getJSONObject("payload").toString(),
                    ProviderAdImmutableGetResponsePayload.class);

            cToolbar.setTitle(result.getCompany().getName());

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

    @Override public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId() == R.id.action_favorite){
            if (!isFavorite) {
                item.setIcon(R.drawable.ic_favorite_white_24dp);
            } else item.setIcon(R.drawable.ic_favorite_outline_white_24dp);
            isFavorite = !isFavorite;
            return true;
        }
    }
}
