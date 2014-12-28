package com.angrychimps.appname.customer.search;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.angrychimps.appname.R;


public class SearchActivity extends FragmentActivity {

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    static int sortMode = 0; //0 = relevance, 1 = location, 2 = price low high, 3 = price high low, 4 = discount
    //SectionsPagerAdapter mSectionsPagerAdapter; // Loads fragments into memory
    private ViewPager mViewPager; // Hosts section contents and allows swiping between pages

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter, passing in the appropriate page
        mViewPager = (ViewPager) findViewById(R.id.pager);
        //mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            // onPageSelected finds the current page. invalidateOptionsMenu() resets the action bar for each page.
            @Override
            public void onPageSelected(int position) {
                invalidateOptionsMenu();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
        });

    }

    // Sets up the action bar for each page and provides the variables for MapActivity.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    // Home is the "Up" functionality. The others load a new MapActivity, passing in a map.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);

    }
/*
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {



        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a CelestialBodyFragment (defined as a static inner class below) with the page number as its lone argument.
            Fragment fragment = new CelestialBodyFragment();
            Bundle args = new Bundle();
            args.putInt(CelestialBodyFragment.ARG_SECTION_NUMBER, position);
            fragment.setArguments(args);
            return fragment;
        }

        // Total number of pages
        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return planetList[position];
        }
    }

    // Fragment that contains the individual planets/moons to display, and all of their relevant data
    public static class CelestialBodyFragment extends Fragment {
        // Grabs the section number for the fragment
        public static final String ARG_SECTION_NUMBER = "section_number";

        public CelestialBodyFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_celestial_body, container, false);
            savedInstanceState = this.getArguments();
            ImageView mPlanetImage = (ImageView) rootView.findViewById(R.id.imageMain);
            TextView mTextDescription = (TextView) rootView.findViewById(R.id.textViewDescription);


            Resources res = getResources();
            ArrayList<String> listDataHeader = new ArrayList<String>(Arrays.asList(res.getStringArray(R.array.titles)));
            HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();
            HashMap<String, List<String>> listValues = new HashMap<String, List<String>>();



            }
            // Send all the data above through the ExpandableListAdapter, and return it to getItem() above
            ExpandableListAdapter listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild, listValues);
            ExpandableListView expListView = (ExpandableListView) rootView.findViewById(R.id.expListView);
            expListView.setAdapter(listAdapter);
            return rootView;
        }


    // Define a DialogFragment that displays the error dialog
    public static class ErrorDialogFragment extends DialogFragment {
        // Global field to contain the error dialog
        private Dialog mDialog;
        // Default constructor. Sets the dialog field to null
        public ErrorDialogFragment() {
            super();
            mDialog = null;
        }
        // Set the dialog to display
        public void setDialog(Dialog dialog) {
            mDialog = dialog;
        }
        // Return a Dialog to the DialogFragment.
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return mDialog;
        }
    }

    // Handle results returned to the FragmentActivity by Google Play services

    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        // Decide what to do based on the original request code
        switch (requestCode) {
            case CONNECTION_FAILURE_RESOLUTION_REQUEST :
            //If the result code is Activity.RESULT_OK, try to connect again

                switch (resultCode) {
                    case Activity.RESULT_OK :
                    //Try the request again


                        break;
                }

        }
    }

    private boolean servicesConnected() {
        // Check that Google Play services is available
        int resultCode =
                GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {
            // In debug mode, log the status
            Log.d("Location Updates",
                    "Google Play services is available.");
            // Continue
            return true;
            // Google Play services was not available for some reason.
            // resultCode holds the error code.
        } else {
            // Get the error dialog from Google Play services
            Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
                    resultCode,
                    this,
                    CONNECTION_FAILURE_RESOLUTION_REQUEST);

            // If Google Play services can provide an error dialog
            if (errorDialog != null) {
                // Create a new DialogFragment for the error dialog
                ErrorDialogFragment errorFragment =
                        new ErrorDialogFragment();
                // Set the dialog in the DialogFragment
                errorFragment.setDialog(errorDialog);
                // Show the error dialog in the DialogFragment
                errorFragment.show(getSupportFragmentManager(),
                        "Location Updates");
            }
        }
    }
    }
*/
}