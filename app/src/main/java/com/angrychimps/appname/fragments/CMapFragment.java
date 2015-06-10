package com.angrychimps.appname.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.angrychimps.appname.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CMapFragment extends Fragment implements OnMapReadyCallback, Toolbar.OnMenuItemClickListener {

    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.fab) FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.toolbar_with_fab, container, false);
        ButterKnife.inject(this, rootView);

        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.innerContainer, mapFragment).commit();
        mapFragment.getMapAsync(this);

        toolbar.setTitle("Map");
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        toolbar.inflateMenu(R.menu.menu_map);
        toolbar.setOnMenuItemClickListener(this);

        fab.setImageResource(R.drawable.ic_request_white_24dp);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap map) {
//        LatLng currentPosition = new LatLng(App.getLatitude(), App.getLongitude());
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 13));
//        map.addMarker(new MarkerOptions().position(currentPosition));

//        for (int i = 0; i < App.searchResults.size(); i++) {
//            map.addMarker(new MarkerOptions().position(new LatLng(App.searchResults.get(i).getLat(),
//                    App.searchResults.get(i).getLon())).icon(BitmapDescriptorFactory.defaultMarker(207)));
//        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        // Handle action bar item clicks here.
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case R.id.action_filter:
//                FragmentTransaction ft = fm.beginTransaction();
//                Fragment prev = fm.findFragmentByTag("dialog");
//                if (prev != null) ft.remove(prev);
//                ft.addToBackStack(null);
//
//                // Create and show the dialog.
//                CFilterFragment fragment = new CFilterFragment();
//                fragment.show(ft, "dialog");
                return true;
        }
        return false;
    }
}
