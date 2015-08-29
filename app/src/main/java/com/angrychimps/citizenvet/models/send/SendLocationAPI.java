package com.angrychimps.citizenvet.models.send;

import com.angrychimps.citizenvet.models.base.Location;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class SendLocationAPI {
    abstract Location payload();

    SendLocationAPI(){
    }

    public static SendLocationAPI create(Location location){
        return new AutoParcelGson_SendLocationAPI(location);
    }
}
