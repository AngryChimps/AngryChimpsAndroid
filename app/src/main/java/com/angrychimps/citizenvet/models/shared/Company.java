package com.angrychimps.citizenvet.models.shared;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.angrychimps.citizenvet.models.received.Messages;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/*
    Used in Auth API, received as part of login response
    Used in Company API, for both send and receive
 */
@AutoParcelGson
public abstract class Company implements Parcelable {
    @Nullable public abstract String id(); //receive only
    @Nullable public abstract String name();
    @Nullable public abstract String role();
    @Nullable public abstract String email();
    @Nullable public abstract String website();
    @Nullable public abstract Messages messages();
    @Nullable @SerializedName("billing_address") public abstract Address address();
    @Nullable public abstract List<CompanyLocation> locations();
    public abstract int plan(); //Received in GET and sent with PATCH only, plan levels- 1: Basic, 2: Premium

    Company() {
    }

    @AutoParcelGson.Builder
    public abstract static class Builder{
        public abstract Builder id(String id);
        public abstract Builder name(String name);
        public abstract Builder role(String role);
        public abstract Builder email(String email);
        public abstract Builder website(String website);
        public abstract Builder messages(Messages messages);
        public abstract Builder address(Address address);
        public abstract Builder locations(List<CompanyLocation> locations);
        public abstract Builder plan(int plan);
        public abstract Company build();
    }

    public static Builder builder(){
        return new AutoParcelGson_Company.Builder();
    }
}
