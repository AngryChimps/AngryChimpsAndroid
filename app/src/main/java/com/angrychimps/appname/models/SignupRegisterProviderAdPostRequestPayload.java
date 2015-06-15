package com.angrychimps.appname.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@SuppressWarnings("WeakerAccess") @JsonObject
public class SignupRegisterProviderAdPostRequestPayload {

    @JsonField
    Availabilities availabilities;

    @JsonField
    Service services;

    @JsonField
    private String ad_title;

    @JsonField
    private String ad_description;

    @JsonField
    private String category_id;

    public SignupRegisterProviderAdPostRequestPayload() {
    }

    public String getAd_title() {
        return ad_title;
    }

    public void setAd_title(String ad_title) {
        this.ad_title = ad_title;
    }

    public String getAd_description() {
        return ad_description;
    }

    public void setAd_description(String ad_description) {
        this.ad_description = ad_description;
    }

    public Availabilities getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(Availabilities availabilities) {
        this.availabilities = availabilities;
    }

    public Service getServices() {
        return services;
    }

    public void setServices(Service services) {
        this.services = services;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
}
