package com.angrychimps.appname.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class CalendarGetResponsePayload {

    @JsonField
    String name;

    @JsonField
    Availabilities availabilities;

    public CalendarGetResponsePayload() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Availabilities getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(Availabilities availabilities) {
        this.availabilities = availabilities;
    }
}