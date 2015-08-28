package com.angrychimps.citizenvet.models.recieved;

import com.angrychimps.citizenvet.models.base.Location;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class RecLocationAPIpost {
    abstract PayloadLocation payload();

    public Location location(){
        return payload().location();
    }

    @AutoParcelGson
    static abstract class PayloadLocation{
        abstract Location location();
    }
}
