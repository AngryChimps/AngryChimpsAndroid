package com.angrychimps.citizenvet.events;

import android.location.Location;

public class LocationUpdatedEvent {
    public Location location;

    public LocationUpdatedEvent(Location location) {
        this.location = location;
    }
}