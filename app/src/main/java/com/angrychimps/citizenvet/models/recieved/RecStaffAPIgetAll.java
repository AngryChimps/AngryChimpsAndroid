package com.angrychimps.citizenvet.models.recieved;

import com.angrychimps.citizenvet.models.base.Staff;

import java.util.List;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class RecStaffAPIgetAll {
    abstract PayloadStaff payload();

    public int count(){
        return payload().count();
    }

    public List<Staff> staffList(){
        return payload().staff();
    }

    @AutoParcelGson
    static abstract class PayloadStaff{
        abstract int count();
        abstract List<Staff> staff();
    }
}
