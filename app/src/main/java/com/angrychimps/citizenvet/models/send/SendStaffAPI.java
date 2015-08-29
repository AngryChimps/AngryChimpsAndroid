package com.angrychimps.citizenvet.models.send;

import com.angrychimps.citizenvet.models.base.Staff;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class SendStaffAPI {
    abstract Staff payload();

    SendStaffAPI(){}

    public static SendStaffAPI create(Staff staff){
        return new AutoParcelGson_SendStaffAPI(staff);
    }
}
