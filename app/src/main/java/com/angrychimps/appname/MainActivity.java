package com.angrychimps.appname;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
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
import com.angrychimps.appname.menu.NavigationDrawerAdapter;
import com.angrychimps.appname.menu.NavigationDrawerItem;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    public static FrameLayout mContainer;
    public static MaterialMenuIconToolbar materialMenu;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private boolean mServiceProviderMode = false;
    private List<NavigationDrawerItem> mDataList;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Using Toolbar in place of ActionBar lets us place the Navigation Drawer over the top, as Material Design recommends
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack();
                }else {
                    mDrawerLayout.openDrawer(mDrawerList);
                }
            }
        });
        materialMenu = new MaterialMenuIconToolbar(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN) {
            @Override public int getToolbarViewId() {
                return R.id.toolbar;
            }
        };
        materialMenu.setNeverDrawTouch(true);

        initiateNavigationDrawer();


        //Set up the main fragment
        FrameLayout fragmentContainer = (FrameLayout) findViewById(R.id.container);
        mContainer = new FrameLayout(this);
        mContainer.setId(R.id.container_id);

        setMode();
        setTitle();

        fragmentContainer.addView(mContainer);

        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getFragmentManager().getBackStackEntryCount() == 0) materialMenu.animateState(MaterialMenuDrawable.IconState.BURGER);
            }
        });
    }

    private void setMode() {
        if(mServiceProviderMode){
            CompanyMainFragment companyMainFragment = new CompanyMainFragment();
            replaceFragmentNoBackStack(companyMainFragment);
        }else {
            CustomerMainFragment customerMainFragment = new CustomerMainFragment();
            replaceFragmentNoBackStack(customerMainFragment);
        }
    }

    private void setTitle() {
        if(mServiceProviderMode){
            getSupportActionBar().setTitle("Provider Mode");
        }else {
            getSupportActionBar().setTitle("Customer Mode");
        }
    }

    private void replaceFragmentNoBackStack(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(mContainer.getId(), fragment).commit();
    }
    private void replaceFragmentAddBackStack(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(mContainer.getId(), fragment).addToBackStack(null).commit();
        materialMenu.animateState(MaterialMenuDrawable.IconState.ARROW);
    }


    private void initiateNavigationDrawer() {
        mDataList = new ArrayList<>();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // Set up the drawer's list view with items and onclick listener
        mDataList.add(new NavigationDrawerItem(R.drawable.photo, "Name Nameson", "example@email.com",
                null, R.layout.navigation_drawer_profile));
        mDataList.add(new NavigationDrawerItem(R.drawable.ic_explore_blue,"Explore deals near you", false, true, R.layout.navigation_drawer_item));
        mDataList.add(new NavigationDrawerItem(R.drawable.ic_messages_blue, "Messages", "2",
                R.layout.navigation_drawer_messages_item));
        if(mServiceProviderMode) {
            mDataList.add(new NavigationDrawerItem("Provider Mode", R.layout.navigation_drawer_switch_item));
            mDataList.add(new NavigationDrawerItem(R.drawable.ic_add_dkblue,"Create your 1st Ad", true, true, R.layout.navigation_drawer_item));
            mDataList.add(new NavigationDrawerItem(R.drawable.ic_avail_dkblue,"Availability Manager", true, true, R.layout.navigation_drawer_item));
            mDataList.add(new NavigationDrawerItem(R.drawable.ic_company_dkblue,"Company Profile Manager", true, true, R.layout.navigation_drawer_item));
        } else{
            mDataList.add(new NavigationDrawerItem("Consumer Mode", R.layout.navigation_drawer_switch_item));
            mDataList.add(new NavigationDrawerItem(R.drawable.ic_request_blue,"Request a Service", true, true, R.layout.navigation_drawer_item));
            mDataList.add(new NavigationDrawerItem(R.drawable.ic_notification_blue,"Notification Manager", true, true, R.layout.navigation_drawer_item));
        }
        mDataList.add(new NavigationDrawerItem(R.drawable.ic_star_grey,"Rate this App", false, false,
                R.layout.navigation_drawer_item));
        mDataList.add(new NavigationDrawerItem(R.drawable.ic_help_grey,"Help!", false, false,
                R.layout.navigation_drawer_item));
        mDataList.add(new NavigationDrawerItem(R.drawable.ic_logout_grey,"Log Out", false, false,
                R.layout.navigation_drawer_item));

        NavigationDrawerAdapter mAdapter = new NavigationDrawerAdapter(this, mDataList, mServiceProviderMode);
        mDrawerList.setAdapter(mAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
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
                return true;
            case R.id.action_filter:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) ft.remove(prev);
                ft.addToBackStack(null);

                // Create and show the dialog.
                CustomerSearchFragment fragment = new CustomerSearchFragment();
                fragment.show(ft, "dialog");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){

        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
            if(getFragmentManager().getBackStackEntryCount() < 2) setTitle();
        } else if(mDrawerLayout.isDrawerOpen(mDrawerList)){
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            super.onBackPressed();
        }
    }



    // Item selected functionality
    private void selectItem(int position) {
        getFragmentManager().popBackStack();
        if(mServiceProviderMode) {
            switch (position) {
                case 0:
                    break;
                case 1:
                    break;
                case 3:
                    mServiceProviderMode = false;
                    initiateNavigationDrawer();
                    setMode();
                    return;
                case 4:
                    CompanyCreateAdFragment companyCreateAdFragment = new CompanyCreateAdFragment();
                    replaceFragmentAddBackStack(companyCreateAdFragment);
                    getSupportActionBar().setTitle("Create Ad");
                    break;
                default:
                    break;
            }
        }else{
            switch (position) {
                case 0:
                    break;
                case 1:
                    break;
                case 3:
                    mServiceProviderMode = true;
                    initiateNavigationDrawer();
                    setMode();
                    return;
                case 4:
                    CustomerCreateAdFragment customerCreateAdFragment = new CustomerCreateAdFragment();
                    replaceFragmentAddBackStack(customerCreateAdFragment);
                    getSupportActionBar().setTitle("Request Service");
                    break;
                default:
                    break;
            }
        }
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        materialMenu.syncState(savedInstanceState);
    }

    public void onCancel(View view) {
        getFragmentManager().popBackStack();
    }

    // The click listener for the ListView in the navigation drawer
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
       }
    }
}