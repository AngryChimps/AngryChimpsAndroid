package com.angrychimps.appname.events;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class LocationUpdatedEvent {
    public double latitude;
    public double longitude;
    public LatLng latLng;

    public LocationUpdatedEvent(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        latLng = new LatLng(latitude, longitude);
    }
}
