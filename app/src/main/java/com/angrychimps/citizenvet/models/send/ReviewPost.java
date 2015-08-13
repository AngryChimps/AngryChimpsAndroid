package com.angrychimps.citizenvet.models.send;

import java.util.List;

/*
    Usedin Review API to post a new review
 */
public class ReviewPost {
    private String locationId;
    private float rating;
    private String body;
    private List<String> staffIds;

    public ReviewPost() {
    }

    public ReviewPost(String locationId, float rating, String body, List<String> staffIds) {
        this.locationId = locationId;
        this.rating = rating;
        this.body = body;
        this.staffIds = staffIds;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(List<String> staffIds) {
        this.staffIds = staffIds;
    }
}
