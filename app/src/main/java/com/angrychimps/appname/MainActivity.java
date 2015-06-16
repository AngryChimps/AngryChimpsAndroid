package com.angrychimps.appname;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.SortedList;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.angrychimps.appname.callbacks.OnVolleyResponseListener;
import com.angrychimps.appname.events.LocationUpdatedEvent;
import com.angrychimps.appname.events.ResultChangedEvent;
import com.angrychimps.appname.events.ResultInsertedEvent;
import com.angrychimps.appname.events.ResultMovedEvent;
import com.angrychimps.appname.events.ResultRemovedEvent;
import com.angrychimps.appname.events.SessionIdReceivedEvent;
import com.angrychimps.appname.events.UpNavigationArrowEvent;
import com.angrychimps.appname.events.UpNavigationBurgerEvent;
import com.angrychimps.appname.fragments.CMainFragment;
import com.angrychimps.appname.fragments.LocationManagerFragment;
import com.angrychimps.appname.fragments.PMainFragment;
import com.angrychimps.appname.models.Deal;
import com.angrychimps.appname.server.JsonRequestObject;
import com.angrychimps.appname.server.VolleyRequest;
import com.angrychimps.appname.utils.Otto;
import com.bluelinelabs.logansquare.LoganSquare;
import com.squareup.otto.Subscribe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements OnVolleyResponseListener {

    private static final String TAG_LOCATION_FRAGMENT = "location_fragment";
    @InjectView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @InjectView(R.id.nav_view) NavigationView navigationView;
    private final Handler handler = new Handler();
    private SortedList<Deal> deals;
    private Location currentLocation, previousLocation; //Update only if the user has moved
    private boolean serviceProviderMode = false;
    private FragmentManager fm;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        fm = getSupportFragmentManager();
        if (fm.findFragmentByTag(TAG_LOCATION_FRAGMENT) == null)
            fm.beginTransaction().add(new LocationManagerFragment(), TAG_LOCATION_FRAGMENT).commit();

        deals = new SortedList<>(Deal.class, new SortedList.Callback<Deal>() {
            @Override public int compare(Deal o1, Deal o2) {
                if (o1.getDistance() < o2.getDistance()) return -1;
                if (o1.getDistance() > o2.getDistance()) return 1;
                else return 0;
            }

            @Override public void onInserted(int position, int count) {
                Otto.BUS.getBus().post(new ResultInsertedEvent(position, count));
            }

            @Override public void onRemoved(int position, int count) {
                Otto.BUS.getBus().post(new ResultRemovedEvent(position, count));
            }

            @Override public void onMoved(int fromPosition, int toPosition) {
                Otto.BUS.getBus().post(new ResultMovedEvent(fromPosition, toPosition));
            }

            @Override public void onChanged(int position, int count) {
                Otto.BUS.getBus().post(new ResultChangedEvent(position, count));
            }

            @Override public boolean areContentsTheSame(Deal oldItem, Deal newItem) {
                return Math.abs(oldItem.getDistance() - newItem.getDistance()) < 0.1;
            }

            @Override public boolean areItemsTheSame(Deal item1, Deal item2) {
                return item1.getProvider_ad_id().equals(item2.getProvider_ad_id());
            }
        });

        initiateNavigationDrawer();
        setMainFragment();
    }

    @Override protected void onStart() {
        super.onStart();
        Otto.BUS.getBus().register(this); //Register to receive events
        updateIfNecessary();
    }

    @Override protected void onStop() {
        super.onStop();
        Otto.BUS.getBus().unregister(this); //Always unregister when an object no longer should be on the bus.
    }

    @Override public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) drawerLayout.closeDrawer(navigationView);
        else super.onBackPressed();
    }

    @Override public void onVolleyResponse(JSONObject object) {
        try {
            JSONArray jArray = object.getJSONObject("payload").getJSONArray("results");
            deals.beginBatchedUpdates();
            for (int i = 0; i < jArray.length(); i++) deals.add(LoganSquare.parse(jArray.get(i).toString(), Deal.class));
            deals.endBatchedUpdates();
        } catch (IOException | JSONException e) {
            Log.i(null, "JsonObjectRequest error");
            e.printStackTrace();
        }
    }

    public SortedList<Deal> getDeals() {
        return deals;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    private void replaceFragmentNoBackStack(Fragment fragment) {
        fm.beginTransaction().replace(R.id.container, fragment).commit();
    }

    public void replaceFragmentAddBackStack(Fragment fragment) {
        fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
    }

    private void setMainFragment() {
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        replaceFragmentNoBackStack(serviceProviderMode ? new PMainFragment() : new CMainFragment());
    }

    private void initiateNavigationDrawer(){
        navigationView.removeAllViews();
        getLayoutInflater().inflate(serviceProviderMode? R.layout.drawer_p : R.layout.drawer_c, navigationView);
    }

    public void onDrawerItemClicked(View view) {
        switch (view.getId()){
            //Shared
            case R.id.drawer_profile:
                break;
            case R.id.drawer_explore:
                if(fm.getBackStackEntryCount() != 0) setMainFragment();
                break;
            case R.id.drawer_messages:
                break;
            case R.id.drawer_switch:
                serviceProviderMode = !serviceProviderMode;
                //Wait until the drawer is closed to change its contents and the main fragment, otherwise you get stuttering
                drawerLayout.closeDrawer(navigationView);
                handler.postDelayed(new Runnable() {
                    @Override public void run() {
                        initiateNavigationDrawer();
                        setMainFragment();
                    }
                }, 280);
                break;
            //Customer mode only
            case R.id.drawer_request:
                break;
            case R.id.drawer_notifications:
                break;
            //Provider mode only
            case R.id.drawer_create_ad:
                //replaceFragmentAddBackStack(new PCreateAdFragment());
                break;
            case R.id.drawer_availability:
                break;
            case R.id.drawer_company_profile:
                break;
            //Shared
            case R.id.drawer_rate:
                break;
            case R.id.drawer_help:
                break;
            case R.id.drawer_logout:
                break;
        }
        drawerLayout.closeDrawer(navigationView);
    }

    private void updateIfNecessary() {
        //SessionId and location are required.
        if (currentLocation == null || App.getInstance().getSessionId() == null) return;

        //Update only if location has changed significantly (>250 meters)
        if (previousLocation == null || deals.size() == 0 || previousLocation.distanceTo(currentLocation) > 250) {
            new VolleyRequest(this).makeRequest(Request.Method.POST, "search", new JsonRequestObject.Builder()
                    .setLatitude(currentLocation.getLatitude()).setLongitude(currentLocation.getLongitude()).setLimit(20).create());
            previousLocation = currentLocation;
        }
    }

    @Subscribe public void upNavigationArrowPressed(UpNavigationArrowEvent event) {
        setMainFragment();
    }

    @Subscribe public void upNavigationBurgerPressed(UpNavigationBurgerEvent event) {
        drawerLayout.openDrawer(navigationView);
    }

    @Subscribe public void sessionIdReceived(SessionIdReceivedEvent event) {
        updateIfNecessary();
    }

    @Subscribe public void locationUpdated(LocationUpdatedEvent event) {
        currentLocation = event.location;
        updateIfNecessary();
    }

}