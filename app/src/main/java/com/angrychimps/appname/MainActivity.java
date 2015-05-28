package com.angrychimps.appname;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.angrychimps.appname.company.CompanyCreateAdFragment;
import com.angrychimps.appname.company.CompanyMainFragment;
import com.angrychimps.appname.customer.CustomerCreateAdFragment;
import com.angrychimps.appname.customer.CustomerMainFragment;
import com.angrychimps.appname.customer.search.CustomerSearchFragment;
import com.angrychimps.appname.interfaces.OnVolleyResponseListener;
import com.angrychimps.appname.menu.NavDrawerAdapter;
import com.angrychimps.appname.menu.NavDrawerItem;
import com.angrychimps.appname.models.SearchPostResponseResults;
import com.angrychimps.appname.models.SessionGetResponsePayload;
import com.angrychimps.appname.utils.DeviceLocation;
import com.angrychimps.appname.utils.VolleyRequest;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar;
import com.bluelinelabs.logansquare.LoganSquare;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnVolleyResponseListener {

    public static final String url = "http://devvy3.angrychimps.com/api/v1/";
    public static final String mediaUrl = "http://devvy3.angrychimps.com/media/";
    public static FrameLayout container;
    public static MaterialMenuIconToolbar materialMenu; //Manually control the Up Navigation button
    public static String sessionId; //Session ID required for all server calls
    public static List<SearchPostResponseResults> searchResults = new ArrayList<>();
    public static JSONObject currentRequest = new JSONObject();
    public static Location currentLocation;
    private static Toolbar toolbar;
    private static View toolbarPadding;
    private FragmentManager fm;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private boolean serviceProviderMode = false;
    private boolean isFavorite = false;

    public static void setToolbarTranslucent() {
        toolbarPadding.setVisibility(View.GONE);
        toolbar.setBackgroundResource(R.color.primary_translucent);
    }

    public static void setToolbarOpaque() {
        toolbarPadding.setVisibility(View.INVISIBLE);
        toolbar.setBackgroundResource(R.color.primary);
    }

    public static void clearMenu() {
        toolbar.getMenu().clear();
    }

    public static void setMenu(int resId) {
        clearMenu();
        toolbar.inflateMenu(resId);
    }

    public static void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();

        //Get current location then grab the sessionId
        DeviceLocation.LocationResult locationResult = new DeviceLocation.LocationResult() {
            @Override
            public void gotLocation(Location location) {
                currentLocation = location;
                Log.i(null, "currentLocation latitude == " + currentLocation.getLatitude() + "and longitude == " + currentLocation.getLongitude());
                new VolleyRequest(MainActivity.this).getSessionId();
            }
        };
        new DeviceLocation().getLocation(this, locationResult);

        // Using Toolbar in place of ActionBar lets us place the Navigation Drawer over the top, as Material Design recommends
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarPadding = findViewById(R.id.toolbarPadding); //Empty space that is removable to allow toolbar transparency
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fm.getBackStackEntryCount() != 0) onNavigateUp();
                else drawerLayout.openDrawer(drawerList);
            }
        });
        materialMenu = new MaterialMenuIconToolbar(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN) {
            @Override
            public int getToolbarViewId() {
                return R.id.toolbar;
            }
        };

        initiateNavigationDrawer();

        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.i(null, "back stack contains " + fm.getBackStackEntryCount() + " items");
                if (fm.getBackStackEntryCount() == 0) materialMenu.animateState(MaterialMenuDrawable.IconState.BURGER);
            }
        });
    }

    private void setMainFragment() {
        //Set up the main fragment
        //App waits until sessionId has been acquired, because that is required for further communication with the server
        FrameLayout fragmentContainer = (FrameLayout) findViewById(R.id.container);
        container = new FrameLayout(this);
        container.setId(R.id.container_id);

        setMode();
        setMainPageTitle();

        fragmentContainer.addView(container);
    }

    @Override
    public void onVolleyResponse(JSONObject object) {
        sessionId = "";
        try {
            SessionGetResponsePayload session = LoganSquare.parse(object.getString("payload"), SessionGetResponsePayload.class);
            sessionId = session.getSession_id();
            Log.i("sessionId = ", "" + sessionId);
            setMainFragment();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void setMode() {
        if (serviceProviderMode) {
            CompanyMainFragment companyMainFragment = new CompanyMainFragment();
            replaceFragmentNoBackStack(companyMainFragment);
        } else {
            CustomerMainFragment customerMainFragment = new CustomerMainFragment();
            replaceFragmentNoBackStack(customerMainFragment);
        }
    }

    private void setMainPageTitle() {
        setTitle(serviceProviderMode ? "Provider Mode" : "Customer Mode");
    }

    private void replaceFragmentNoBackStack(Fragment fragment) {
        fm.beginTransaction().replace(container.getId(), fragment).commit();
    }

    private void replaceFragmentAddBackStack(Fragment fragment) {
        fm.beginTransaction().replace(container.getId(), fragment).addToBackStack(null).commit();
        materialMenu.animateState(MaterialMenuDrawable.IconState.ARROW);
    }

    private void initiateNavigationDrawer() {
        List<NavDrawerItem> mDataList = new ArrayList<>();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        // Set a custom shadow that overlays the main content when the drawer opens
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // Set up the drawer's list view with items and onclick listener
        mDataList.add(new NavDrawerItem(R.drawable.photo, "Name Nameson", "example@email.com", null, R.layout.navigation_drawer_profile));
        mDataList.add(new NavDrawerItem(R.drawable.ic_explore_blue_24dp, "Explore deals near you", false, true, R.layout.navigation_drawer_item));
        mDataList.add(new NavDrawerItem(R.drawable.ic_messages_blue_24dp, "Messages", "2", R.layout.navigation_drawer_messages_item));
        if (serviceProviderMode) {
            mDataList.add(new NavDrawerItem("Provider Mode", R.layout.navigation_drawer_switch_item));
            mDataList.add(new NavDrawerItem(R.drawable.ic_add_dkblue_24dp, "Create your 1st Ad", true, true, R.layout.navigation_drawer_item));
            mDataList.add(new NavDrawerItem(R.drawable.ic_avail_dkblue_24dp, "Availability Manager", true, true, R.layout.navigation_drawer_item));
            mDataList.add(new NavDrawerItem(R.drawable.ic_company_dkblue_24dp, "Company Profile Manager", true, true, R.layout.navigation_drawer_item));
        } else {
            mDataList.add(new NavDrawerItem("Consumer Mode", R.layout.navigation_drawer_switch_item));
            mDataList.add(new NavDrawerItem(R.drawable.ic_request_blue_24dp, "Request a Service", true, true, R.layout.navigation_drawer_item));
            mDataList.add(new NavDrawerItem(R.drawable.ic_notification_blue_24dp, "Notification Manager", true, true, R.layout.navigation_drawer_item));
        }
        mDataList.add(new NavDrawerItem(R.drawable.ic_star_grey600_24dp, "Rate this App", false, false, R.layout.navigation_drawer_item));
        mDataList.add(new NavDrawerItem(R.drawable.ic_help_grey600_24dp, "Help!", false, false, R.layout.navigation_drawer_item));
        mDataList.add(new NavDrawerItem(R.drawable.ic_settings_power_grey600_24dp, "Log Out", false, false, R.layout.navigation_drawer_item));

        NavDrawerAdapter mAdapter = new NavDrawerAdapter(this, mDataList, serviceProviderMode);
        drawerList.setAdapter(mAdapter);
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here.
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case R.id.action_map:
                materialMenu.animateState(MaterialMenuDrawable.IconState.ARROW);
                final SupportMapFragment mapFragment = SupportMapFragment.newInstance();
                fm.beginTransaction().replace(container.getId(), mapFragment).addToBackStack(null).commit();
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(final GoogleMap map) {
                        setToolbarTitle("Map");
                        setMenu(R.menu.menu_map);

                        LatLng currentPosition = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 13));
                        map.addMarker(new MarkerOptions().position(currentPosition));

                        for (int i = 0; i < searchResults.size(); i++) {
                            map.addMarker(new MarkerOptions().position(new LatLng(searchResults.get(i).getLat(),
                                    searchResults.get(i).getLon())).icon(BitmapDescriptorFactory.defaultMarker(207)));
                        }
                    }
                });
                return true;
            case R.id.action_filter:
                FragmentTransaction ft = fm.beginTransaction();
                Fragment prev = fm.findFragmentByTag("dialog");
                if (prev != null) ft.remove(prev);
                ft.addToBackStack(null);

                // Create and show the dialog.
                CustomerSearchFragment fragment = new CustomerSearchFragment();
                fragment.show(ft, "dialog");
                return true;
            case R.id.action_favorite:
                if (!isFavorite) {
                    item.setIcon(R.drawable.ic_favorite_white_24dp);
                } else item.setIcon(R.drawable.ic_favorite_outline_white_24dp);
                isFavorite = !isFavorite;
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigateUp() {
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        setMenu(R.menu.menu_main);
        setMainPageTitle();
        return super.onNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
            setMenu(R.menu.menu_main);
            if (fm.getBackStackEntryCount() < 2) setMainPageTitle();
        } else if (drawerLayout.isDrawerOpen(drawerList)) {
            drawerLayout.closeDrawer(drawerList);
        } else {
            super.onBackPressed();
        }
    }

    // Navigation Drawer item selected
    private void selectItem(int position) {
        fm.popBackStack();
        if (serviceProviderMode) {
            switch (position) {
                case 0:
                    break;
                case 1:
                    break;
                case 3:
                    serviceProviderMode = false;
                    initiateNavigationDrawer();
                    onNavigateUp();
                    setMode();
                    return;
                case 4:
                    replaceFragmentAddBackStack(new CompanyCreateAdFragment());
                    setTitle("Create Ad");
                    break;
                default:
                    break;
            }
        } else {
            switch (position) {
                case 0:
                    break;
                case 1:
                    break;
                case 3:
                    serviceProviderMode = true;
                    initiateNavigationDrawer();
                    onNavigateUp();
                    setMode();
                    return;
                case 4:
                    replaceFragmentAddBackStack(new CustomerCreateAdFragment());
                    setTitle("Request Service");
                    break;
                default:
                    break;
            }
        }
        drawerLayout.closeDrawer(drawerList);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        materialMenu.syncState(savedInstanceState);
    }

    public void onCancel(View view) {
        onBackPressed();
    }

    // The click listener for the ListView in the navigation drawer
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
}