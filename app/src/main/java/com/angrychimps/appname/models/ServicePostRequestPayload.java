package com.angrychimps.appname.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject

public class ServicePostRequestPayload {

    @JsonField
    private String name;

    @JsonField
    private String company_id;

    @JsonField
    private float discounted_price;

    @JsonField
    private float original_price;

    @JsonField
    private int mins_for_service;

    @JsonField
    private int mins_notice;

    public ServicePostRequestPayload() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public float getDiscounted_price() {
        return discounted_price;
    }

    public void setDiscounted_price(float discounted_price) {
        this.discounted_price = discounted_price;
    }

    public float getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(float original_price) {
        this.original_price = original_price;
    }

    public int getMins_for_service() {
        return mins_for_service;
    }

    public void setMins_for_service(int mins_for_service) {
        this.mins_for_service = mins_for_service;
    }

    public int getMins_notice() {
        return mins_notice;
    }

    public void setMins_notice(int mins_notice) {
        this.mins_notice = mins_notice;
    }
}
