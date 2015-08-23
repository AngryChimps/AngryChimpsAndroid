package com.angrychimps.citizenvet.models.send;

import android.support.annotation.Nullable;

import com.angrychimps.citizenvet.models.shared.Address;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/*
    Used by Search API to request relevant search results
 */
@AutoParcelGson
public abstract class SearchRequest {
    @Nullable public abstract Address address();
    public abstract float lat();
    public abstract float lon();
    public abstract int animal();
    @SerializedName("mobile_location") public abstract boolean mobileLocation();
    public abstract boolean emergency();
    @SerializedName("walk_in") public abstract boolean walkIn();
    public abstract int limit();
    public abstract int offset();

    SearchRequest() {
    }

    @AutoParcelGson.Builder
    public abstract static class Builder {
        public abstract Builder address(Address address);
        public abstract Builder lat(float lat);
        public abstract Builder lon(float lon);
        public abstract Builder animal(int animal);
        public abstract Builder mobileLocation(boolean mobileLocation);
        public abstract Builder emergency(boolean emergency);
        public abstract Builder walkIn(boolean walkIn);
        public abstract Builder limit(int limit);
        public abstract Builder offset(int offset);
        public abstract SearchRequest build();

        Builder() {
        }
    }

    public static Builder builder() {
        return new AutoParcelGson_SearchRequest.Builder()
                .mobileLocation(false)
                .emergency(false)
                .walkIn(false)
                .limit(20)
                .offset(0);
    }
}
