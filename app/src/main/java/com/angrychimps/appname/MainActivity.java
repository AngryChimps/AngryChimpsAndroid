package com.angrychimps.appname;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.angrychimps.appname.adapters.DrawerAdapter;
import com.angrychimps.appname.fragments.CFilterFragment;
import com.angrychimps.appname.fragments.CMainFragment;
import com.angrychimps.appname.fragments.Deprecated_CRequestServiceFragment;
import com.angrychimps.appname.fragments.PCreateAdFragment;
import com.angrychimps.appname.fragments.PMainFragment;
import com.angrychimps.appname.models.DrawerItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FrameLayout container;
    private FragmentManager fm;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private boolean serviceProviderMode = false;
    private boolean isFavorite = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();

//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (fm.getBackStackEntryCount() != 0) onNavigateUp();
//                else drawerLayout.openDrawer(drawerList);
//            }
//        });

        initiateNavigationDrawer();

        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.i(null, "back stack contains " + fm.getBackStackEntryCount() + " items");
                //if (fm.getBackStackEntryCount() == 0) materialMenu.animateState(MaterialMenuDrawable.IconState.BURGER);
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
        fragmentContainer.addView(container);
    }

    private void setMode() {
        replaceFragmentNoBackStack(serviceProviderMode? new PMainFragment() : new CMainFragment());
    }

    public void replaceFragmentNoBackStack(Fragment fragment) {
        fm.beginTransaction().replace(container.getId(), fragment).commit();
    }

    public void replaceFragmentAddBackStack(Fragment fragment) {
        fm.beginTransaction().replace(container.getId(), fragment).addToBackStack(null).commit();
    }

    private void initiateNavigationDrawer() {
        List<DrawerItem> drawerList = new ArrayList<>();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.drawerList = (ListView) findViewById(R.id.left_drawer);

        // Set a custom shadow that overlays the main content when the drawer opens
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // Set up the drawer's list view with items and onclick listener
        drawerList.add(new DrawerItem(R.drawable.photo, "Name Nameson", "example@email.com", null, R.layout.drawer_profile));
        drawerList.add(new DrawerItem(R.drawable.ic_explore_blue_24dp, "Explore deals near you", false, true, R.layout.drawer_item));
        drawerList.add(new DrawerItem(R.drawable.ic_messages_blue_24dp, "Messages", "2", R.layout.drawer_item_messages));
        if (serviceProviderMode) {
            drawerList.add(new DrawerItem("Provider Mode", R.layout.drawer_item_switch));
            drawerList.add(new DrawerItem(R.drawable.ic_add_dkblue_24dp, "Create your 1st Ad", true, true, R.layout.drawer_item));
            drawerList.add(new DrawerItem(R.drawable.ic_avail_dkblue_24dp, "Availability Manager", true, true, R.layout.drawer_item));
            drawerList.add(new DrawerItem(R.drawable.ic_company_dkblue_24dp, "Company Profile Manager", true, true, R.layout.drawer_item));
        } else {
            drawerList.add(new DrawerItem("Consumer Mode", R.layout.drawer_item_switch));
            drawerList.add(new DrawerItem(R.drawable.ic_request_blue_24dp, "Request a Service", true, true, R.layout.drawer_item));
            drawerList.add(new DrawerItem(R.drawable.ic_notification_blue_24dp, "Notification Manager", true, true, R.layout.drawer_item));
        }
        drawerList.add(new DrawerItem(R.drawable.ic_star_grey600_24dp, "Rate this App", false, false, R.layout.drawer_item));
        drawerList.add(new DrawerItem(R.drawable.ic_help_grey600_24dp, "Help!", false, false, R.layout.drawer_item));
        drawerList.add(new DrawerItem(R.drawable.ic_settings_power_grey600_24dp, "Log Out", false, false, R.layout.drawer_item));

        DrawerAdapter mAdapter = new DrawerAdapter(this, drawerList, serviceProviderMode);
        this.drawerList.setAdapter(mAdapter);
        this.drawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here.
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case R.id.action_map:
                //materialMenu.animateState(MaterialMenuDrawable.IconState.ARROW);
                final SupportMapFragment mapFragment = SupportMapFragment.newInstance();
                fm.beginTransaction().replace(container.getId(), mapFragment).addToBackStack(null).commit();
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(final GoogleMap map) {
//                        setToolbarTitle("Map");
//                        setMenu(R.menu.menu_map);

                        LatLng currentPosition = new LatLng(App.getLatitude(), App.getLongitude());
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 13));
                        map.addMarker(new MarkerOptions().position(currentPosition));

                        for (int i = 0; i < App.searchResults.size(); i++) {
                            map.addMarker(new MarkerOptions().position(new LatLng(App.searchResults.get(i).getLat(),
                                    App.searchResults.get(i).getLon())).icon(BitmapDescriptorFactory.defaultMarker(207)));
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
                CFilterFragment fragment = new CFilterFragment();
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
        //setMenu(R.menu.menu_main);
        //setMainPageTitle();
        return super.onNavigateUp();
    }

//    @Override
//    public void onBackPressed() {
//        if (fm.getBackStackEntryCount() > 0) {
//            fm.popBackStack();
//            //setMenu(R.menu.menu_main);
//            if (fm.getBackStackEntryCount() < 2) setMainPageTitle();
//        } else if (drawerLayout.isDrawerOpen(drawerList)) {
//            drawerLayout.closeDrawer(drawerList);
//        } else {
//            super.onBackPressed();
//        }
//    }

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
                    replaceFragmentAddBackStack(new PCreateAdFragment());
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
                    replaceFragmentAddBackStack(new Deprecated_CRequestServiceFragment());
                    setTitle("Request Service");
                    break;
                default:
                    break;
            }
        }
        drawerLayout.closeDrawer(drawerList);
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