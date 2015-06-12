package com.angrychimps.appname.fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.angrychimps.appname.MainActivity;
import com.angrychimps.appname.R;
import com.angrychimps.appname.adapters.CAdDetailRecyclerViewAdapter;
import com.angrychimps.appname.adapters.ViewPagerPhotoAdapter;
import com.angrychimps.appname.callbacks.OnVolleyResponseListener;
import com.angrychimps.appname.events.CallCompanyEvent;
import com.angrychimps.appname.events.DealClickedEvent;
import com.angrychimps.appname.events.FlagListingEvent;
import com.angrychimps.appname.events.ResultChangedEvent;
import com.angrychimps.appname.events.ResultInsertedEvent;
import com.angrychimps.appname.events.ResultMovedEvent;
import com.angrychimps.appname.events.ResultRemovedEvent;
import com.angrychimps.appname.events.ServiceClickedEvent;
import com.angrychimps.appname.events.ShowReviewsEvent;
import com.angrychimps.appname.events.StartNavigationEvent;
import com.angrychimps.appname.events.UpNavigationArrowEvent;
import com.angrychimps.appname.models.Address;
import com.angrychimps.appname.models.CAdDetail;
import com.angrychimps.appname.models.CompanyDetails;
import com.angrychimps.appname.models.Service;
import com.angrychimps.appname.server.VolleyRequest;
import com.angrychimps.appname.utils.Otto;
import com.bluelinelabs.logansquare.LoganSquare;
import com.squareup.otto.Subscribe;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.relex.circleindicator.CircleIndicator;


public class CAdDetailFragment extends Fragment implements OnVolleyResponseListener {

    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.collapsing_toolbar) CollapsingToolbarLayout cToolbar;
    @InjectView(R.id.viewPagerCompanyImages) ViewPager pager;
    @InjectView(R.id.circleIndicator) CircleIndicator indicator;
    @InjectView(R.id.recycler_view) RecyclerView recyclerView;
    SortedList<Service> services;
    RecyclerView.Adapter adapter;
    Address address;
    boolean isFavorite;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_c_ad_detail_and_toolbar, container, false);
        ButterKnife.inject(this, rootView);

        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Otto.BUS.getBus().post(new UpNavigationArrowEvent());
            }
        });
        toolbar.inflateMenu(R.menu.menu_ad_detail);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_favorite) {
                    item.setIcon(isFavorite ? R.drawable.ic_favorite_outline_white_24dp : R.drawable.ic_favorite_white_24dp);
                    isFavorite = !isFavorite;
                    return true;
                }
                return false;
            }
        });
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL); //columns,orientation
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemViewCacheSize(0);

        services = new SortedList<>(Service.class, new SortedList.Callback<Service>() {
            @Override public int compare(Service o1, Service o2) {
                return 0;
            }

            @Override public void onInserted(int position, int count) {

            }

            @Override public void onRemoved(int position, int count) {

            }

            @Override public void onMoved(int fromPosition, int toPosition) {

            }

            @Override public void onChanged(int position, int count) {

            }

            @Override public boolean areContentsTheSame(Service oldItem, Service newItem) {
                return false;
            }

            @Override public boolean areItemsTheSame(Service item1, Service item2) {
                return false;
            }
        });

        new VolleyRequest(this).makeRequest(Request.Method.GET, "providerAdImmutable/" + this.getArguments().getString("id"));

        return rootView;
    }

    @Override public void onStart() {
        super.onStart();
        Otto.BUS.getBus().register(this);
    }

    @Override public void onStop() {
        super.onStop();
        Otto.BUS.getBus().unregister(this);
    }

    @Override
    public void onVolleyResponse(JSONObject object) {
        try {
            CAdDetail result = LoganSquare.parse(object.getJSONObject("payload").getJSONObject("payload").toString(), CAdDetail.class);
            cToolbar.setTitle(result.getCompany().getName());
            pager.setAdapter(new ViewPagerPhotoAdapter(getActivity(), result.getPhotos()));
            indicator.setViewPager(pager);
            if(result.getPhotos().size() < 2) indicator.setVisibility(View.GONE);
            for (Service service : result.getServices()) services.add(service);
            address = result.getAddress();
            adapter = new CAdDetailRecyclerViewAdapter(new CompanyDetails(result, getArguments().getString("distance"), String.format("0x%06X",
                    (0xFFFFFF & getResources().getColor(R.color.primary)))), services, ((MainActivity) getActivity()).getDeals());
            recyclerView.setAdapter(adapter);

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    @Subscribe public void onCallCompany(CallCompanyEvent event) {

    }

    @Subscribe public void onDealClicked(DealClickedEvent event) {

    }

    @Subscribe public void onFlagListing(FlagListingEvent event) {

    }

    @Subscribe public void onServiceClicked(ServiceClickedEvent event) {

    }

    @Subscribe public void onShowReviews(ShowReviewsEvent event) {

    }

    @Subscribe public void onStartNavigation(StartNavigationEvent event) {
        if (address != null) {
            try {
                //If google maps is not installed on the device, throw exception
                getActivity().getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);

                // Build Uri from the address and create the intent
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + address.getStreet1() + " " + address.getCity() +
                        ", " + address.getState() + " " + address.getZip()));

                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace(); //No need to do anything- there is no promise that clicking will launch navigation.
            }
        }
    }

    @Subscribe public void onResultChanged(ResultChangedEvent event) {
        adapter.notifyItemRangeChanged(event.position, event.count);
    }

    @Subscribe public void onResultInserted(ResultInsertedEvent event) {
        adapter.notifyItemRangeInserted(event.position, event.count);
    }

    @Subscribe public void onResultMoved(ResultMovedEvent event) {
        adapter.notifyItemMoved(event.fromPosition, event.toPosition);
    }

    @Subscribe public void onResultRemoved(ResultRemovedEvent event) {
        adapter.notifyItemRangeRemoved(event.position, event.count);
    }
}