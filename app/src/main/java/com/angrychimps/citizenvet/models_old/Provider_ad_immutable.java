package com.angrychimps.citizenvet.models_old;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@SuppressWarnings("WeakerAccess") @JsonObject
public class Provider_ad_immutable {

    @JsonField
    Id id;

    public Provider_ad_immutable() {
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }
}