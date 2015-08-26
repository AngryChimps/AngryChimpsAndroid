package com.angrychimps.citizenvet.models.shared;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/*
    Used in Auth API, received as part of login response
    Used in Company API??
    Used in Location API, both for send and inside LocationDetail in GET receive
 */
@AutoParcelGson
public abstract class CompanyLocation implements Parcelable {
    @Nullable public abstract String id(); //This may be named company_id in parts?
    @Nullable public abstract String name();
    @Nullable public abstract String description();
    @Nullable public abstract Address address();
    @SerializedName("is_mobile") public abstract boolean isMobile();
    @SerializedName("mobile_radius_miles") public abstract float mobileRadiusMiles();
    @Nullable public abstract List<String> photos();
    @Nullable public abstract int[] services();
    @SerializedName("walk_ins") public abstract boolean walkIns();
    public abstract boolean emergencies();
    @Nullable public abstract int[] animals();
    @Nullable @SerializedName("staff_ids") public abstract List<String> staffIds();
    @Nullable public abstract Hours hours();
    public abstract int status();

    CompanyLocation() {
    }

    @AutoParcelGson.Builder
    public abstract static class Builder{
        public abstract Builder id(String id);
        public abstract Builder name(String name);
        public abstract Builder description(String description);
        public abstract Builder address(Address address);
        public abstract Builder isMobile(boolean isMobile);
        public abstract Builder mobileRadiusMiles(float mobileRadiusMiles);
        public abstract Builder photos(List<String> photos);
        public abstract Builder services(int[] services);
        public abstract Builder walkIns(boolean walkIns);
        public abstract Builder emergencies(boolean emergencies);
        public abstract Builder animals(int[] animals);
        public abstract Builder staffIds(List<String> staffIds);
        public abstract Builder hours(Hours hours);
        public abstract Builder status(int status);
        public abstract CompanyLocation build();

        Builder(){
        }
    }

    public static Builder builder(){
        return new AutoParcelGson_CompanyLocation.Builder();
    }

}
