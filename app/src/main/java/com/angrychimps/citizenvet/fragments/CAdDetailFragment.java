package com.angrychimps.citizenvet.fragments;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.angrychimps.citizenvet.R;
import com.angrychimps.citizenvet.events.CallCompanyEvent;
import com.angrychimps.citizenvet.events.DealClickedEvent;
import com.angrychimps.citizenvet.events.FlagListingEvent;
import com.angrychimps.citizenvet.events.ServiceClickedEvent;
import com.angrychimps.citizenvet.events.ShowReviewsEvent;
import com.angrychimps.citizenvet.events.StartNavigationEvent;
import com.angrychimps.citizenvet.events.UpNavigationArrowEvent;
import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

import static com.angrychimps.citizenvet.utils.Otto.BUS;


public class CAdDetailFragment extends Fragment   {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout cToolbar;
    @Bind(R.id.viewPagerCompanyImages) ViewPager pager;
    @Bind(R.id.circleIndicator) CircleIndicator indicator;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
//    private SortedList<Service> services;
//    private RecyclerView.Adapter adapter;
//    private Address address;
    private boolean isFavorite;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_c_ad_detail_and_toolbar, container, false);
        ButterKnife.bind(this, rootView);

        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BUS.getBus().post(new UpNavigationArrowEvent());
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

//        services = new SortedList<>(Service.class, new SortedList.Callback<Service>() {
//            @Override public int compare(Service o1, Service o2) {
//                return 0;
//            }
//
//            @Override public void onInserted(int position, int count) {
//
//            }
//
//            @Override public void onRemoved(int position, int count) {
//
//            }
//
//            @Override public void onMoved(int fromPosition, int toPosition) {
//
//            }
//
//            @Override public void onChanged(int position, int count) {
//
//            }
//
//            @Override public boolean areContentsTheSame(Service oldItem, Service newItem) {
//                return false;
//            }
//
//            @Override public boolean areItemsTheSame(Service item1, Service item2) {
//                return false;
//            }
//        });
//
//        VOLLEY.addToRequestQueue(new VolleyRequest(GET, "providerAdImmutable/" + this.getArguments().getString("id"), this, this));

        return rootView;
    }

    @Override public void onStart() {
        super.onStart();
        BUS.getBus().register(this);
    }

    @Override public void onStop() {
        super.onStop();
        BUS.getBus().unregister(this);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
//        if (address != null) {
//            try {
//                //If google maps is not installed on the device, throw exception
//                getActivity().getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);
//
//                // Build Uri from the address and create the intent
////                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + address.getStreet1() + " " + address.getCity() +
////                        ", " + address.getState() + " " + address.getZip()));
//
//                // Make the Intent explicit by setting the Google Maps package
//                mapIntent.setPackage("com.google.android.apps.maps");
//                startActivity(mapIntent);
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace(); //No need to do anything- there is no promise that clicking will launch navigation.
//            }
//        }
    }

//    @Subscribe public void onResultChanged(ResultChangedEvent event) {
//        adapter.notifyItemRangeChanged(event.position, event.count);
//    }
//
//    @Subscribe public void onResultInserted(ResultInsertedEvent event) {
//        adapter.notifyItemRangeInserted(event.position, event.count);
//    }
//
//    @Subscribe public void onResultMoved(ResultMovedEvent event) {
//        adapter.notifyItemMoved(event.fromPosition, event.toPosition);
//    }
//
//    @Subscribe public void onResultRemoved(ResultRemovedEvent event) {
//        adapter.notifyItemRangeRemoved(event.position, event.count);
//    }
//
//    @Override public void onErrorResponse(VolleyError error) {

//    }
//
//    @Override public void onResponse(JSONObject response) {
//        try {
//            CAdDetail result = LoganSquare.parse(response.getJSONObject("payload").getJSONObject("payload").toString(), CAdDetail.class);
//            cToolbar.setTitle(result.getCompany().getName());
//            pager.setAdapter(new ViewPagerPhotoAdapter(getActivity(), result.getPhotos()));
//            indicator.setViewPager(pager);
//            if(result.getPhotos().size() < 2) indicator.setVisibility(View.GONE);
//
//            Service example = new Service();
//            example.setName("Example second service");
//            example.setDescription("Description of service looks like this, and can go on to multiple lines.");
//            example.setDiscounted_price(49.99);
//            example.setOriginal_price(69.99);
//            services.add(example);
//
//            for (Service service : result.getServices()) services.add(service);
//            address = result.getAddress();
//            adapter = new CAdDetailRecyclerViewAdapter(getResources(), new CompanyDetails(result, getArguments().getString("distance"),
//                    String.format("0x%06X", (0xFFFFFF & getResources().getColor(R.color.primary)))), services, ((MainActivity) getActivity()).getDeals());
//            recyclerView.setAdapter(adapter);
//
//        } catch (JSONException | IOException e) {
//            e.printStackTrace();
//        }
//    }
}