package com.angrychimps.citizenvet.models.base;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

@AutoParcelGson
public abstract class SearchResult implements Parcelable{
    @SerializedName("location_id") public abstract String locationId();
    @SerializedName("company_name") public abstract String companyName();
    public abstract String photo();
    public abstract float rating();
    @SerializedName("rating_count") public abstract int ratingCount();
    @SerializedName("distance_miles") public abstract float distanceMiles();
    @SerializedName("location") public abstract LatLon latLon();

    SearchResult(){
    }
}
