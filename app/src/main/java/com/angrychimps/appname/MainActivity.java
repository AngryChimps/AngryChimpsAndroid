package com.angrychimps.appname;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.angrychimps.appname.consumer.ConsumerMainFragment;
import com.angrychimps.appname.consumer.search.SearchActivity;
import com.angrychimps.appname.menu.NavigationDrawerAdapter;
import com.angrychimps.appname.menu.NavigationDrawerItem;
import com.angrychimps.appname.provider.ProviderMainFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private FrameLayout mContainer;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean mServiceProviderMode = false;
    private NavigationDrawerAdapter mAdapter;
    private List<NavigationDrawerItem> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setStatusBarColor(findViewById(R.id.statusBarBackground),getResources().getColor(R.color.primary_dark));

        initiateNavigationDrawer(mServiceProviderMode);

        FrameLayout fragmentContainer = (FrameLayout) findViewById(R.id.container);
        mContainer = new FrameLayout(this);
        mContainer.setId(R.id.container_id);

        if(mServiceProviderMode){
            ProviderMainFragment providerMainFragment = new ProviderMainFragment();
            replaceFragmentNoBackStack(providerMainFragment);
        }else {
            ConsumerMainFragment consumerMainFragment = new ConsumerMainFragment();
            replaceFragmentNoBackStack(consumerMainFragment);
        }
        fragmentContainer.addView(mContainer);

        // ActionBarDrawerToggle ties together the interactions between the sliding drawer and the app icon in the action bar
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.closed) {
            @Override
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu(); // Creates call to onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu(); // Creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //Enable ActionBar app icon to behave as action to toggle nav drawer
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setHomeButtonEnabled(true);
        }
    }

    private void replaceFragmentNoBackStack(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(mContainer.getId(), fragment).commit();
    }
    private void replaceFragmentAddBackStack(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(mContainer.getId(), fragment).addToBackStack(null).commit();
    }


    private void initiateNavigationDrawer(boolean serviceProviderMode) {
        mDataList = new ArrayList<NavigationDrawerItem>();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // Set up the drawer's list view with items and onclick listener
        mDataList.add(new NavigationDrawerItem("Your Settings", R.drawable.ic_action_person));
        mDataList.add(new NavigationDrawerItem("Messages", R.drawable.ic_action_email));
        if(serviceProviderMode) {
            mDataList.add(new NavigationDrawerItem("Availability Manager", R.drawable.ic_action_go_to_today));
            mDataList.add(new NavigationDrawerItem("Provider Ad Manager", R.drawable.ic_action_dock));
            mDataList.add(new NavigationDrawerItem("Company Manager", R.drawable.ic_action_dock));
        } else{
            mDataList.add(new NavigationDrawerItem("Consumer Ad Manager", R.drawable.ic_action_dock));
        }
        mDataList.add(new NavigationDrawerItem("Rate this App", R.drawable.ic_action_important));
        mDataList.add(new NavigationDrawerItem("Help/Offer Feedback", R.drawable.ic_action_help));
        mDataList.add(new NavigationDrawerItem("Log Out", R.drawable.ic_action_cancel));

        mAdapter = new NavigationDrawerAdapter(this, R.layout.navigation_drawer_item, mDataList);
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
        //TODO add menu options, connect them, and clean this code
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        }
        if (id == R.id.action_map) {
            return true;
        }
        if (id == R.id.action_filter) {
            return true;
        }

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (id == android.R.id.home) return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    // Item selected functionality
    private void selectItem(int position) {
        if(mServiceProviderMode) {
            switch (position) {
                case 0:
                    break;
                case 1:
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
                default:
                    break;
            }
        }
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }


    public void onClickSearchOptions(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }


    public void setStatusBarColor(View statusBar,int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //status bar height
            int actionBarHeight = getActionBarHeight();
            int statusBarHeight = getStatusBarHeight();
            //action bar height
            statusBar.getLayoutParams().height = actionBarHeight + statusBarHeight;
            statusBar.setBackgroundColor(color);
        }
    }

    public int getActionBarHeight() {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    // The click listener for the ListView in the navigation drawer
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (mDataList.get(position).getTitle() == null) {
                selectItem(position);
            }
        }
    }
}
