package com.angrychimps.appname.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class LocationGetResponsePayload {

    @JsonField
    Location location;

    public LocationGetResponsePayload() {
    }
}
