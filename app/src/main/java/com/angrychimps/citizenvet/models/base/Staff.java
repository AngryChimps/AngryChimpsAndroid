package com.angrychimps.citizenvet.models.base;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

@AutoParcelGson
public abstract class Staff implements Parcelable{
    @Nullable public abstract String id(); //receive only
    @Nullable @SerializedName("company_id") public abstract String companyId(); //send only
    @Nullable public abstract String first();
    @Nullable public abstract String last();
    @Nullable public abstract String title();
    @Nullable public abstract String position();
    @Nullable public abstract String education();
    @Nullable public abstract String email();
    @Nullable public abstract String description();
    @Nullable public abstract String photo();
    @Nullable @SerializedName("location_ids") public abstract List<String> locationIds();
    public abstract int role();

    Staff() {
    }
}
