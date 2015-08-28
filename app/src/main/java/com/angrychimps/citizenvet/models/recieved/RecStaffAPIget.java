package com.angrychimps.citizenvet.models.recieved;

import com.angrychimps.citizenvet.models.base.Staff;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class RecStaffAPIget {
    abstract PayloadStaff payload();

    public Staff staff(){
        return payload().staff();
    }

    @AutoParcelGson
    static abstract class PayloadStaff{
        abstract Staff staff();
    }
}
