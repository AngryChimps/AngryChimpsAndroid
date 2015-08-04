package com.angrychimps.citizenvet.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.angrychimps.citizenvet.MainActivity;
import com.angrychimps.citizenvet.R;
import com.angrychimps.citizenvet.utils.Otto;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PMapFragment extends Fragment implements OnMapReadyCallback, Toolbar.OnMenuItemClickListener {

    @Bind(R.id.toolbar) Toolbar toolbar;
    private GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.toolbar_default, container, false);
        ButterKnife.bind(this, rootView);

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

        return rootView;
    }

    @Override public void onStart() {
        super.onStart();
        Otto.BUS.getBus().register(this);
    }

    @Override public void onStop() {
        super.onStop();
        Otto.BUS.getBus().unregister(this);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        setMarkers();
    }

    private void setMarkers(){
        Location location = ((MainActivity) getActivity()).getCurrentLocation();
        LatLng currentPosition = new LatLng(location.getLatitude(), location.getLongitude());
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 13));
        map.addMarker(new MarkerOptions().position(currentPosition));
    }
}