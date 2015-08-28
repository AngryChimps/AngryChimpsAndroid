package com.angrychimps.citizenvet.models.base;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class Address implements Parcelable {
    public abstract String street1();
    @Nullable public abstract String street2();
    public abstract String city();
    public abstract String state();
    public abstract int zip();
    public abstract String phone();
    public abstract LatLon location();

    Address() {
    }

    public static Address create(String street1, String street2, String city, String state, int zip, String phone, LatLon location){
        return new AutoParcelGson_Address(street1, street2, city, state, zip, phone, location);
    }
}
