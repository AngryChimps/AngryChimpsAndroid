package com.angrychimps.appname.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class ServicePostResponsePayload {

    @JsonField
    public ServiceId service;

    public ServicePostResponsePayload() {
    }

    public ServiceId getService() {
        return service;
    }

    public void setService(ServiceId service) {
        this.service = service;
    }
}