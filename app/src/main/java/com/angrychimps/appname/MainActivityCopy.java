package com.angrychimps.appname;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.angrychimps.appname.customer.CustomerCreateAdFragment;
import com.angrychimps.appname.menu.NavigationDrawerAdapter;
import com.angrychimps.appname.menu.NavigationDrawerItem;

import java.util.ArrayList;
import java.util.List;

import it.neokree.materialnavigationdrawer.MaterialAccount;
import it.neokree.materialnavigationdrawer.MaterialAccountListener;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

public class MainActivityCopy extends MaterialNavigationDrawer implements MaterialAccountListener {

    private FrameLayout mContainer;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean mServiceProviderMode = false;
    private NavigationDrawerAdapter mAdapter;
    private List<NavigationDrawerItem> mDataList;
    //private MaterialMenuIcon materialMenu;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        setStatusBarColor(findViewById(R.id.statusBarBackground),getResources().getColor(R.color.primary_dark));
//
//        materialMenu = new MaterialMenuIcon(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
//
//        //initiateNavigationDrawer(mServiceProviderMode);
//
//        FrameLayout fragmentContainer = (FrameLayout) findViewById(R.id.container);
//        mContainer = new FrameLayout(this);
//        mContainer.setId(R.id.container_id);
//
//        if(mServiceProviderMode){
//            ProviderMainFragment providerMainFragment = new ProviderMainFragment();
//            replaceFragmentNoBackStack(providerMainFragment);
//        }else {
//            CustomerMainFragment customerMainFragment = new CustomerMainFragment();
//            replaceFragmentNoBackStack(customerMainFragment);
//        }
//        fragmentContainer.addView(mContainer);
//
//        // ActionBarDrawerToggle ties together the interactions between the sliding drawer and the app icon in the action bar
////        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.closed) {
////            @Override
////            public void onDrawerClosed(View view) {
////                invalidateOptionsMenu(); // Creates call to onPrepareOptionsMenu()
////            }
////
////            @Override
////            public void onDrawerOpened(View drawerView) {
////                invalidateOptionsMenu(); // Creates call to onPrepareOptionsMenu()
////            }
////        };
////        mDrawerLayout.setDrawerListener(mDrawerToggle);
//
//
//        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//            @Override
//            public void onBackStackChanged() {
//                int stackHeight = getFragmentManager().getBackStackEntryCount();
//                Log.i(null, stackHeight + " Items in the stack");
//                if (stackHeight == 0) materialMenu.animateState(MaterialMenuDrawable.IconState.BURGER);
////                if (stackHeight > 0) { // if we have something on the stack (doesn't include the current shown fragment)
////                    getActionBar().setDisplayHomeAsUpEnabled(true);
////                } else {
////                    //getActionBar().setDisplayHomeAsUpEnabled(false);
////                }
//            }
//        });
//    }

    private void replaceFragmentNoBackStack(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(mContainer.getId(), fragment).commit();
    }
    private void replaceFragmentAddBackStack(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(mContainer.getId(), fragment).addToBackStack(null).commit();
        //materialMenu.animateState(MaterialMenuDrawable.IconState.ARROW);
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        // Associate searchable configuration with the SearchView
////        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
////        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
////        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        //if (mDrawerToggle.onOptionsItemSelected(item)) return true;
//        // Handle action bar item clicks here.
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                getFragmentManager().popBackStack();
//                return true;
//            case R.id.action_search:
//                return true;
//            case R.id.action_map:
//                return true;
//            case R.id.action_filter:
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
//                if (prev != null) ft.remove(prev);
//                ft.addToBackStack(null);
//
//                // Create and show the dialog.
//                CustomerSearchFragment fragment = new CustomerSearchFragment();
//                fragment.show(ft, "dialog");
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onBackPressed(){
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
        //mDrawerToggle.syncState();
        //materialMenu.syncState(savedInstanceState);

    }

    @Override
    public void init(Bundle savedInstanceState) {

//        setContentView(R.layout.navigation_drawer_layout);

        //setStatusBarColor(findViewById(R.id.statusBarBackground),getResources().getColor(R.color.primary_dark));

        //materialMenu = new MaterialMenuIcon(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);

        //initiateNavigationDrawer(mServiceProviderMode);

//        FrameLayout fragmentContainer = (FrameLayout) findViewById(R.id.frame_container);
//        mContainer = new FrameLayout(this);
//        mContainer.setId(R.id.container_id);
//
//        if(mServiceProviderMode){
//            ProviderMainFragment providerMainFragment = new ProviderMainFragment();
//            replaceFragmentNoBackStack(providerMainFragment);
//        }else {
//            CustomerMainFragment customerMainFragment = new CustomerMainFragment();
//            replaceFragmentNoBackStack(customerMainFragment);
//        }
//        fragmentContainer.addView(mContainer);


        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int stackHeight = getFragmentManager().getBackStackEntryCount();
                Log.i(null, stackHeight + " Items in the stack");
                //if (stackHeight == 0) materialMenu.animateState(MaterialMenuDrawable.IconState.BURGER);
//                if (stackHeight > 0) { // if we have something on the stack (doesn't include the current shown fragment)
//                    getActionBar().setDisplayHomeAsUpEnabled(true);
//                } else {
//                    //getActionBar().setDisplayHomeAsUpEnabled(false);
//                }
            }
        });

        // add first account
        MaterialAccount account = new MaterialAccount("Jim","amagi82@gmail.com",this.getResources().getDrawable(R.drawable.photo),this.getResources().getDrawable(R.drawable.bamboo));
        this.addAccount(account);

        // set listener
        this.setAccountListener(this);

        // add your sections
        //this.addSection(this.newSection("Main Fragment", new CustomerMainFragment().setSectionColor(R.color.primary)));
        this.addSection(this.newSection("Section 2",new FragmentIndex()));
        this.addDivisor();
        this.addSection(this.newSection("Recorder",this.getResources().getDrawable(R.drawable.bamboo),new FragmentIndex()).setNotifications(10));

        //add custom colored section with icon
        this.addSection(this.newSection("Night Section", this.getResources().getDrawable(R.drawable.bamboo), new FragmentIndex())
                .setSectionColor(Color.parseColor("#2196f3")).setNotifications(150)); // material blue 500

        this.addDivisor();
        //add custom colored section with only text
        this.addSection(this.newSection("Last Section", new FragmentIndex()).setSectionColor((Color.parseColor("#ff9800")))); // material orange 500

//        Intent i = new Intent(this,Settings.class);
//        this.addSection(this.newSection("Settings",this.getResources().getDrawable(R.drawable.ic_settings_black_24dp),i));

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
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
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

    public void onClickCreateNewCustomerAd(View view) {
        CustomerCreateAdFragment fragment = new CustomerCreateAdFragment();
        replaceFragmentAddBackStack(fragment);
    }

    @Override
    public void onAccountOpening(MaterialAccount materialAccount) {

    }

    @Override
    public void onChangeAccount(MaterialAccount materialAccount) {

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
