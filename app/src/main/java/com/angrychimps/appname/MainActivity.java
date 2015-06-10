package com.angrychimps.appname;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.angrychimps.appname.adapters.DrawerAdapter;
import com.angrychimps.appname.events.LocationUpdatedEvent;
import com.angrychimps.appname.events.SearchResultsUpdatedEvent;
import com.angrychimps.appname.events.SessionIdReceivedEvent;
import com.angrychimps.appname.events.UpNavigationArrowEvent;
import com.angrychimps.appname.events.UpNavigationBurgerEvent;
import com.angrychimps.appname.fragments.CMainFragment;
import com.angrychimps.appname.fragments.LocationManagerFragment;
import com.angrychimps.appname.fragments.PCreateAdFragment;
import com.angrychimps.appname.fragments.PMainFragment;
import com.angrychimps.appname.interfaces.OnVolleyResponseListener;
import com.angrychimps.appname.models.DrawerItem;
import com.angrychimps.appname.models.SearchPostResponseResults;
import com.angrychimps.appname.server.JsonRequestObject;
import com.angrychimps.appname.server.VolleyRequest;
import com.angrychimps.appname.utils.Otto;
import com.bluelinelabs.logansquare.LoganSquare;
import com.squareup.otto.Subscribe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements OnVolleyResponseListener {

    private static final String TAG_LOCATION_FRAGMENT = "location_fragment";
    @InjectView(R.id.drawer) ListView drawerListView;
    @InjectView(R.id.drawer_layout) DrawerLayout drawerLayout;
    private List<SearchPostResponseResults> searchResults = new ArrayList<>();
    private Location currentLocation, previousLocation; //Update only if the user has moved
    private boolean serviceProviderMode = false;
    private FragmentManager fm;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        fm = getSupportFragmentManager();
        if (fm.findFragmentByTag(TAG_LOCATION_FRAGMENT) == null) fm.beginTransaction().add(new LocationManagerFragment(), TAG_LOCATION_FRAGMENT).commit();

        setMainFragment();

        initiateNavigationDrawer();
    }

    @Override protected void onStart() {
        super.onStart();
        Otto.BUS.getBus().register(this); //Register to receive events
    }

    @Override protected void onStop() {
        super.onStop();
        Otto.BUS.getBus().unregister(this); //Always unregister when an object no longer should be on the bus.
    }

    @Override public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(drawerListView)) drawerLayout.closeDrawer(drawerListView);
        else super.onBackPressed();
    }

    public List<SearchPostResponseResults> getSearchResults(){
        return searchResults;
    }

    private void setMainFragment() {
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        replaceFragmentNoBackStack(serviceProviderMode ? new PMainFragment() : new CMainFragment());
    }

    public void replaceFragmentNoBackStack(Fragment fragment) {
        fm.beginTransaction().replace(R.id.container, fragment).commit();
    }

    public void replaceFragmentAddBackStack(Fragment fragment) {
        fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
    }

    private void initiateNavigationDrawer() {
        List<DrawerItem> drawerItems = new ArrayList<>();
        // Set a custom shadow that overlays the main content when the drawer opens
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // Set up the drawer's list view with items and onclick listener
        drawerItems.add(new DrawerItem(R.drawable.photo, "Name Nameson", "example@email.com", null, R.layout.drawer_profile));
        drawerItems.add(new DrawerItem(R.drawable.ic_explore_blue_24dp, "Explore deals near you", false, true, R.layout.drawer_item));
        drawerItems.add(new DrawerItem(R.drawable.ic_messages_blue_24dp, "Messages", "2", R.layout.drawer_item_messages));
        if (serviceProviderMode) {
            drawerItems.add(new DrawerItem("Provider Mode", R.layout.drawer_item_switch));
            drawerItems.add(new DrawerItem(R.drawable.ic_add_dkblue_24dp, "Create your 1st Ad", true, true, R.layout.drawer_item));
            drawerItems.add(new DrawerItem(R.drawable.ic_avail_dkblue_24dp, "Availability Manager", true, true, R.layout.drawer_item));
            drawerItems.add(new DrawerItem(R.drawable.ic_company_dkblue_24dp, "Company Profile Manager", true, true, R.layout.drawer_item));
        } else {
            drawerItems.add(new DrawerItem("Consumer Mode", R.layout.drawer_item_switch));
            drawerItems.add(new DrawerItem(R.drawable.ic_request_blue_24dp, "Request a Service", true, true, R.layout.drawer_item));
            drawerItems.add(new DrawerItem(R.drawable.ic_notification_blue_24dp, "Notification Manager", true, true, R.layout.drawer_item));
        }
        drawerItems.add(new DrawerItem(R.drawable.ic_star_grey600_24dp, "Rate this App", false, false, R.layout.drawer_item));
        drawerItems.add(new DrawerItem(R.drawable.ic_help_grey600_24dp, "Help!", false, false, R.layout.drawer_item));
        drawerItems.add(new DrawerItem(R.drawable.ic_settings_power_grey600_24dp, "Log Out", false, false, R.layout.drawer_item));

        drawerListView.setAdapter(new DrawerAdapter(this, drawerItems, serviceProviderMode));
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClickDrawerItem(position);
            }
        });
    }

    private void onClickDrawerItem(int position) {
        if (serviceProviderMode) {
            switch (position) {
                case 0: //Profile
                    break;
                case 1: //Explore Deals Near You
                    setMainFragment();
                    break;
                case 3: //Customer/Provider Switch
                    serviceProviderMode = false;
                    initiateNavigationDrawer();
                    setMainFragment();
                    return;
                case 4: //Create your 1st Ad
                    replaceFragmentAddBackStack(new PCreateAdFragment());
                    break;
                default:
                    break;
            }
        } else {
            switch (position) {
                case 0: //Profile
                    break;
                case 1: //Explore Deals Near You
                    setMainFragment();
                    break;
                case 3:  //Customer/Provider Switch
                    serviceProviderMode = true;
                    initiateNavigationDrawer();
                    setMainFragment();
                    return;
                case 4: //Request a Service
                    //replaceFragmentAddBackStack(new Deprecated_CRequestServiceFragment());
                    break;
                default:
                    break;
            }
        }
        drawerLayout.closeDrawer(drawerListView);
    }

    public void onCancel(View view) {
        onBackPressed();
    }

    @Subscribe public void upNavigationArrowPressed(UpNavigationArrowEvent event) {
        setMainFragment();
    }

    @Subscribe public void upNavigationBurgerPressed(UpNavigationBurgerEvent event) {
        drawerLayout.openDrawer(drawerListView);
    }

    @Subscribe public void sessionIdReceived(SessionIdReceivedEvent event) {
        updateIfNecessary();
    }

    @Subscribe public void locationUpdated(LocationUpdatedEvent event) {
        currentLocation = event.location;
        updateIfNecessary();
    }

    private void updateIfNecessary(){
        //SessionId and location are required.
        if(currentLocation == null || App.getInstance().getSessionId() == null) return;

        //Update only if location has changed significantly (>500 meters)
        if (previousLocation == null || previousLocation.distanceTo(currentLocation) > 500) {
            new VolleyRequest(this).makeRequest(Request.Method.POST, "search", new JsonRequestObject.Builder()
                    .setLatitude(currentLocation.getLatitude()).setLongitude(currentLocation.getLongitude()).setLimit(20).create());
            previousLocation = currentLocation;
        }
    }

    @Override public void onVolleyResponse(JSONObject object) {
        try {
            JSONArray jArray = object.getJSONObject("payload").getJSONArray("results");
            searchResults.clear();
            for (int i = 0; i < jArray.length(); i++) {
                searchResults.add(LoganSquare.parse(jArray.get(i).toString(), SearchPostResponseResults.class));
            }
            Otto.BUS.getBus().post(new SearchResultsUpdatedEvent());

        } catch (IOException | JSONException e) {
            Log.i(null, "JsonObjectRequest error");
            e.printStackTrace();
        }
    }
}