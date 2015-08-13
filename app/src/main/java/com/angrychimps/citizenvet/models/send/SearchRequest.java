package com.angrychimps.citizenvet.models.send;

import com.angrychimps.citizenvet.models.shared.Address;
import com.google.gson.annotations.SerializedName;

/*
    Used by Search API to request relevant search results
 */
public class SearchRequest {
    private Address address;
    private float lat;
    private float lon;
    private int animal;
    @SerializedName("mobile_location") private boolean mobileLocation;
    private boolean emergency;
    @SerializedName("walk_in") private boolean walkIn;
    private int limit;
    private int offset;

    public SearchRequest() {
    }

    public SearchRequest(Address address) {
        this.address = address;
    }

    public SearchRequest(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public int getAnimal() {
        return animal;
    }

    public void setAnimal(int animal) {
        this.animal = animal;
    }

    public boolean isMobileLocation() {
        return mobileLocation;
    }

    public void setMobileLocation(boolean mobileLocation) {
        this.mobileLocation = mobileLocation;
    }

    public boolean isEmergency() {
        return emergency;
    }

    public void setEmergency(boolean emergency) {
        this.emergency = emergency;
    }

    public boolean isWalkIn() {
        return walkIn;
    }

    public void setWalkIn(boolean walkIn) {
        this.walkIn = walkIn;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
