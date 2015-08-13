package com.angrychimps.citizenvet;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.angrychimps.citizenvet.events.LocationUpdatedEvent;
import com.angrychimps.citizenvet.events.SessionIdReceivedEvent;
import com.angrychimps.citizenvet.events.UpNavigationArrowEvent;
import com.angrychimps.citizenvet.events.UpNavigationBurgerEvent;
import com.angrychimps.citizenvet.fragments.CMainFragment;
import com.angrychimps.citizenvet.fragments.LocationManagerFragment;
import com.angrychimps.citizenvet.fragments.PMainFragment;
import com.angrychimps.citizenvet.models.shared.Member;
import com.angrychimps.citizenvet.network.RestClient;
import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;

import static com.angrychimps.citizenvet.utils.Otto.BUS;

public class MainActivity extends AppCompatActivity  {

    private static final String TAG_LOCATION_FRAGMENT = "location_fragment";
    @Bind(R.id.drawer_layout) DrawerLayout drawerLayout;
    @Bind(R.id.nav_view) NavigationView navigationView;
    private final Handler handler = new Handler();
    //private SortedList<Deal> deals;
    private Location currentLocation, previousLocation; //Update only if the user has moved
    private boolean serviceProviderMode = false;
    private FragmentManager fm;
    private RestClient restClient;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        restClient = RestClient.API;

        fm = getSupportFragmentManager();
        if (fm.findFragmentByTag(TAG_LOCATION_FRAGMENT) == null)
            fm.beginTransaction().add(new LocationManagerFragment(), TAG_LOCATION_FRAGMENT).commit();

//        deals = new SortedList<>(Deal.class, new SortedList.Callback<Deal>() {
//            @Override public int compare(Deal o1, Deal o2) {
//                if (o1.getDistance() < o2.getDistance()) return -1;
//                if (o1.getDistance() > o2.getDistance()) return 1;
//                else return 0;
//            }
//
//            @Override public void onInserted(int position, int count) {
//                BUS.getBus().post(new ResultInsertedEvent(position, count));
//            }
//
//            @Override public void onRemoved(int position, int count) {
//                BUS.getBus().post(new ResultRemovedEvent(position, count));
//            }
//
//            @Override public void onMoved(int fromPosition, int toPosition) {
//                BUS.getBus().post(new ResultMovedEvent(fromPosition, toPosition));
//            }
//
//            @Override public void onChanged(int position, int count) {
//                BUS.getBus().post(new ResultChangedEvent(position, count));
//            }
//
//            @Override public boolean areContentsTheSame(Deal oldItem, Deal newItem) {
//                return Math.abs(oldItem.getDistance() - newItem.getDistance()) < 0.1;
//            }
//
//            @Override public boolean areItemsTheSame(Deal item1, Deal item2) {
//                return item1.getProvider_ad_id().equals(item2.getProvider_ad_id());
//            }
//        });

        initiateNavigationDrawer();
        setMainFragment();
    }



    @Override protected void onStart() {
        super.onStart();
        BUS.getBus().register(this); //Register to receive events
        //updateIfNecessary();
    }

    @Override protected void onStop() {
        super.onStop();
        BUS.getBus().unregister(this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) drawerLayout.closeDrawer(navigationView);
        else super.onBackPressed();
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
        getLayoutInflater().inflate(serviceProviderMode ? R.layout.drawer_p : R.layout.drawer_c, navigationView);
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
        //if (currentLocation == null || App.getInstance().getSessionId() == null) return;

        //Update only if location has changed significantly (>250 meters)
//        if (previousLocation == null || deals.size() == 0 || previousLocation.distanceTo(currentLocation) > 250) {
////            VOLLEY.addToRequestQueue(new VolleyRequest(POST, "search", new JsonRequestObject.Builder()
////                    .setLatitude(currentLocation.getLatitude()).setLongitude(currentLocation.getLongitude()).setLimit(20).create(), this, this));
//            previousLocation = currentLocation;
//        }
    }

    @Subscribe public void sessionIdReceived(SessionIdReceivedEvent event){
        Member member = new Member();
        member.setFirst("Jim");
        member.setLast("Pekarek");
        member.setEmail("amagi82@gmail.com");
        member.setPassword("pw");
        member.setTitle("Android developer");
        restClient.member().post(member, new Callback<Member>() {
            @Override public void success(Member member, retrofit.client.Response response) {
                Log.i(null, "Success! member = " + member.toString());

                restClient.member().get(member.getId(), member.getId(), new Callback<Member>() {
                    @Override public void success(Member member, retrofit.client.Response response) {
                        Log.i(null, "Successfully retrieved member with id: " + member.getId() + " and name: " + member.getFirst() + " " + member.getLast());
                    }

                    @Override public void failure(RetrofitError error) {
                        Log.i(null, "Failure on GET " + error.toString());
                    }
                });
            }

            @Override public void failure(RetrofitError error) {
                Log.i(null, "Failure on POST");
            }
        });
    }

    @Subscribe public void upNavigationArrowPressed(UpNavigationArrowEvent event) {
        setMainFragment();
    }

    @Subscribe public void upNavigationBurgerPressed(UpNavigationBurgerEvent event) {
        drawerLayout.openDrawer(navigationView);
    }

    @Subscribe public void locationUpdated(LocationUpdatedEvent event) {
        currentLocation = event.location;
        //updateIfNecessary();
    }
}