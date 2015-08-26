package com.angrychimps.citizenvet;

import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.angrychimps.citizenvet.fragments.LocationManagerFragment;
import com.angrychimps.citizenvet.network.RestClient;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  {

    private static final String TAG_LOCATION_FRAGMENT = "location_fragment";
    @Bind(R.id.drawer_layout) DrawerLayout drawerLayout;
    @Bind(R.id.nav_view) NavigationView navigationView;
    private Location currentLocation, previousLocation; //Update only if the user has moved
    private FragmentManager fm;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RestClient api = RestClient.API;

        fm = getSupportFragmentManager();
        if (fm.findFragmentByTag(TAG_LOCATION_FRAGMENT) == null)
            fm.beginTransaction().add(new LocationManagerFragment(), TAG_LOCATION_FRAGMENT).commit();

        initiateNavigationDrawer();
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

    private void initiateNavigationDrawer(){
        navigationView.removeAllViews();
        //getLayoutInflater().inflate(serviceProviderMode ? R.layout.drawer_p : R.layout.drawer_c, navigationView);
    }

    public void onDrawerItemClicked(View view) {
        switch (view.getId()){
            //Shared
            case R.id.drawer_profile:
                break;
            case R.id.drawer_explore:
                break;
            case R.id.drawer_messages:
                break;
            case R.id.drawer_switch:
                drawerLayout.closeDrawer(navigationView);
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

//    @Subscribe public void sessionIdReceived(SessionIdReceivedEvent event){
//        Member member = new Member();
//        member.setFirst("Jim");
//        member.setLast("Pekarek");
//        member.setEmail("amagi82@gmail.com");
//        member.setPassword("pw");
//        member.setTitle("Android developer");
//        restClient.member().post(member, new Callback<Member>() {
//            @Override public void success(Member member, retrofit.client.Response response) {
//                Log.i(null, "Success! member = " + member.toString());
//
//                restClient.member().get(member.getId(), member.getId(), new Callback<Member>() {
//                    @Override public void success(Member member, retrofit.client.Response response) {
//                        Log.i(null, "Successfully retrieved member with id: " + member.getId() + " and name: " + member.getFirst() + " " + member.getLast());
//
//                        Member memberPatch = new Member();
//                        memberPatch.setFirst("James");
//                        memberPatch.setPassword("pass");
//                        restClient.member().patch(member.getId(), member.getId(), memberPatch, new Callback<Response>() {
//                            @Override public void success(Response response, Response response2) {
//                                Log.i(null, "Patch success!");
//                            }
//
//                            @Override public void failure(RetrofitError error) {
//
//                            }
//                        });
//                    }
//
//                    @Override public void failure(RetrofitError error) {
//                        Log.i(null, "Failure on GET " + error.toString());
//                    }
//                });
//            }
//
//            @Override public void failure(RetrofitError error) {
//                Log.i(null, "Failure on POST");
//            }
//        });
//    }
}