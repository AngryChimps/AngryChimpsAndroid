package com.angrychimps.citizenvet.models.received;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;

/*
    Used in Service API
 */
@AutoParcelGson
public abstract class Service implements Parcelable {
    public abstract int id();
    public abstract String name();

    Service() {
    }

    public static Service create(int id, String name){
        return new AutoParcelGson_Service(id, name);
    }
}
