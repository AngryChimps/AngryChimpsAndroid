package com.angrychimps.citizenvet.models.shared;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import auto.parcelgson.AutoParcelGson;

/*
    Used in Company API, inside Company
    Used in Location API, inside CompanyLocation
 */
@AutoParcelGson
public abstract class Address implements Parcelable {
    public abstract String street1();
    @Nullable public abstract String street2();
    public abstract String city();
    public abstract String state();
    public abstract int zip();
    public abstract String phone();
    public abstract Location location();

    Address() {
    }

    public static Address create(String street1, String street2, String city, String state, int zip, String phone, Location location){
        return new AutoParcelGson_Address(street1, street2, city, state, zip, phone, location);
    }

    @AutoParcelGson
    public abstract static class Location{
        public abstract float lat();
        public abstract float lon();

        Location() {
        }

        public static Location create(float lat, float lon){
            return new AutoParcelGson_Address_Location(lat, lon);
        }
    }
}
