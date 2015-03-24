package com.angrychimps.appname.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class CompanyPutRequestPayload {

    @JsonField
    String name;

    public CompanyPutRequestPayload() {
    }
}
