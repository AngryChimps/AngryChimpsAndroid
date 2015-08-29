package com.angrychimps.citizenvet.models.send;

import com.angrychimps.citizenvet.models.base.Company;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class SendCompanyAPI {
    abstract Company payload();

    SendCompanyAPI(){
    }

    public static SendCompanyAPI create(Company company){
        return new AutoParcelGson_SendCompanyAPI(company);
    }
}
