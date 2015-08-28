package com.angrychimps.citizenvet.models.base;

import android.os.Parcelable;

import java.util.Date;
import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

@AutoParcelGson
public abstract class Review implements Parcelable {
    public abstract String id();
    @SerializedName("reviewer_name") public abstract String reviewerName();
    @SerializedName("reviewer_photo") public abstract String reviewerPhoto();
    public abstract float rating();
    public abstract String body();
    @SerializedName("staff_ids") public abstract List<String> staffIds();
    @SerializedName("created_at") public abstract Date createdAt();

    Review() {
    }

    public static Review create(String id, String reviewerName, String reviewerPhoto, float rating, String body, List<String> staffIds, Date createdAt){
        return new AutoParcelGson_Review(id, reviewerName, reviewerPhoto, rating, body, staffIds, createdAt);
    }
}
