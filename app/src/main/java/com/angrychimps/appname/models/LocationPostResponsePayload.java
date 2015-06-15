package com.angrychimps.appname.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@SuppressWarnings("WeakerAccess") @JsonObject
public class LocationPostResponsePayload {

    @JsonField
    LocationId location;

    public LocationPostResponsePayload() {
    }

    public LocationId getLocation() {
        return location;
    }

    public void setLocation(LocationId location) {
        this.location = location;
    }
}
