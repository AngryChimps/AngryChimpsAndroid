package com.angrychimps.citizenvet.models.received;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;

/*
    Received from Animal API GET
 */
@AutoParcelGson
public abstract class Animal implements Parcelable {
    public abstract int id();
    public abstract String name();

    Animal() {
    }

    public static Animal create(int id, String name){
        return new AutoParcelGson_Animal(id, name);
    }
}
