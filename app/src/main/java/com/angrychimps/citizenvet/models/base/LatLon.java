package com.angrychimps.citizenvet.models.base;

import android.os.Parcelable;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class LatLon  implements Parcelable {
    public abstract float lat();
    public abstract float lon();

    public static LatLon create(float lat, float lon){
        return new AutoParcelGson_LatLon(lat, lon);
    }

    LatLon(){
    }
}
