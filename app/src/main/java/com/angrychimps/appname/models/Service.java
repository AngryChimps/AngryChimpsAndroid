package com.angrychimps.appname.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class Service {

    @JsonField
    private String id;

    @JsonField
    private String name;

    @JsonField
    private String description;

    @JsonField
    private double discounted_price;

    @JsonField
    private double original_price;

    @JsonField
    private int mins_for_service;

    @JsonField
    private int mins_notice;

    private int discounted_price_decimal;

    public Service() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscounted_price() {
        return discounted_price;
    }

    public void setDiscounted_price(double discounted_price) {
        double x = discounted_price - Math.floor(discounted_price);
        discounted_price_decimal = (int) Math.round(x*100);
        this.discounted_price = (int) discounted_price;
    }

    public int getDiscounted_price_decimal() {
        return discounted_price_decimal;
    }

    public double getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(double original_price) {
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

    public int getDiscount(){
        return (int) ((discounted_price/original_price) * 100);
    }
}
