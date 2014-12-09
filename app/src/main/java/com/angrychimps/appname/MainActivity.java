package com.angrychimps.appname;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.angrychimps.appname.consumer.ConsumerMainFragment;
import com.angrychimps.appname.consumer.search.SearchResultFragment;
import com.angrychimps.appname.menu.NavigationDrawerAdapter;
import com.angrychimps.appname.menu.NavigationDrawerItem;
import com.angrychimps.appname.provider.ProviderMainFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private FrameLayout container;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean serviceProviderMode = false;
    NavigationDrawerAdapter adapter;
    List<NavigationDrawerItem> dataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initiateNavigationDrawer(serviceProviderMode);

        FrameLayout fragmentContainer = (FrameLayout) findViewById(R.id.container);
        container = new FrameLayout(this);
        container.setId(R.id.container_id);

        if(serviceProviderMode){
            ProviderMainFragment providerMainFragment = new ProviderMainFragment();
            replaceFragmentNoBackStack(providerMainFragment);
        }else {
            ConsumerMainFragment consumerMainFragment = new ConsumerMainFragment();
            replaceFragmentNoBackStack(consumerMainFragment);
        }
        fragmentContainer.addView(container);

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
        fragmentTransaction.replace(container.getId(), fragment).commit();
    }
    private void replaceFragmentAddBackStack(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(container.getId(), fragment).addToBackStack(null).commit();
    }


    private void initiateNavigationDrawer(boolean serviceProviderMode) {
        dataList = new ArrayList<NavigationDrawerItem>();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // Set up the drawer's list view with items and onclick listener
        dataList.add(new NavigationDrawerItem("Your Settings", R.drawable.ic_action_person));
        dataList.add(new NavigationDrawerItem("Messages", R.drawable.ic_action_email));
        if(serviceProviderMode) {
            dataList.add(new NavigationDrawerItem("Availability Manager", R.drawable.ic_action_go_to_today));
            dataList.add(new NavigationDrawerItem("Provider Ad Manager", R.drawable.ic_action_dock));
            dataList.add(new NavigationDrawerItem("Company Manager", R.drawable.ic_action_dock));
        } else{
            dataList.add(new NavigationDrawerItem("Consumer Ad Manager", R.drawable.ic_action_dock));
        }
        dataList.add(new NavigationDrawerItem("Rate this App", R.drawable.ic_action_important));
        dataList.add(new NavigationDrawerItem("Help/Offer Feedback", R.drawable.ic_action_help));
        dataList.add(new NavigationDrawerItem("Log Out", R.drawable.ic_action_cancel));

        adapter = new NavigationDrawerAdapter(this, R.layout.navigation_drawer_item, dataList);
        mDrawerList.setAdapter(adapter);
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

        if (id == R.id.action_settings) {
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
        //if (mDrawerLayout.isDrawerOpen()) {
        //    mDrawerLayout.closeDrawer();
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void buttonSearch(View view) {
        SearchResultFragment fragment = new SearchResultFragment();
        replaceFragmentAddBackStack(fragment);
    }

    // Item selected functionality
    private void selectItem(int position) {
        if(serviceProviderMode) {
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

    public void onClickSearchOptions(View view) {
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }


    // The click listener for the ListView in the navigation drawer
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (dataList.get(position).getTitle() == null) {
                selectItem(position);
            }
        }
    }
}
