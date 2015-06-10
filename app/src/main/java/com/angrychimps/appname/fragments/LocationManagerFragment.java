package com.angrychimps.appname.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.angrychimps.appname.events.LocationUpdateImmediatelyEvent;
import com.angrychimps.appname.events.LocationUpdatedEvent;
import com.angrychimps.appname.utils.Otto;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.DateFormat;
import java.util.Date;


/*
    Retained fragment which manages current location and broadcasts updates to anyone receiving LocationUpdatedEvent
 */
public class LocationManagerFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private Location currentLocation;
    private LocationRequest locationRequest;
    private GoogleApiClient googleApiClient;
    private String lastUpdateTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        googleApiClient = new GoogleApiClient.Builder(getActivity()).addConnectionCallbacks(this).addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        locationRequest = new LocationRequest().setInterval(120000).setFastestInterval(10000).setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        // Retain this fragment across configuration changes.
        setRetainInstance(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (googleApiClient.isConnected()) startLocationUpdates();
        else googleApiClient.connect();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        googleApiClient.disconnect();
    }

    public Location getCurrentLocation(){
        return currentLocation;
    }

    public String getLastUpdateTime(){
        return lastUpdateTime;
    }

    private void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onConnected(Bundle bundle) {
        currentLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if(currentLocation != null) Otto.BUS.getBus().post(new LocationUpdatedEvent(currentLocation));
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {
        Otto.BUS.getBus().post(new LocationUpdatedEvent(location));
        if(currentLocation.distanceTo(location) > 500) Otto.BUS.getBus().post(new LocationUpdateImmediatelyEvent()); //The location is over 500 meters off- force a refresh
        currentLocation = location;
        lastUpdateTime = DateFormat.getTimeInstance().format(new Date());
    }
}