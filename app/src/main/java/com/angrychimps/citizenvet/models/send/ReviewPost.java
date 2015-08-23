package com.angrychimps.citizenvet.models.send;


import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/*
    Usedin Review API to post a new review
 */
@AutoParcelGson
public abstract class ReviewPost {
    @SerializedName("location_id") public abstract String locationId();
    public abstract float rating();
    public abstract String body();
    @SerializedName("staff_ids") public abstract List<String> staffIds();

    ReviewPost() {
    }

    public static ReviewPost create(String locationId, float rating, String body, List<String> staffIds){
        return new AutoParcelGson_ReviewPost(locationId, rating, body, staffIds);
    }
}
