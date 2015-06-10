package com.angrychimps.appname.events;

import android.location.Location;

public class LocationUpdatedEvent {
    public double latitude;
    public double longitude;

    public LocationUpdatedEvent(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }
}
