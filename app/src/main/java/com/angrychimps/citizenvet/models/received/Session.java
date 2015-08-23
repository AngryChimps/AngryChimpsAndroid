package com.angrychimps.citizenvet.models.received;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class Session implements Parcelable {

    public abstract String id();

    Session() {
    }

    public static Session create(String id){
        return new AutoParcelGson_Session(id);
    }
}
