package com.angrychimps.citizenvet.models.recieved;

import com.angrychimps.citizenvet.models.base.Company;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class RecCompanyAPI {
    abstract PayloadCompany payload();

    public Company company(){
        return payload().company();
    }

    @AutoParcelGson
    static abstract class PayloadCompany{
        abstract Company company();
    }
}
