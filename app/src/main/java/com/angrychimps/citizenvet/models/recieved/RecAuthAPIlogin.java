package com.angrychimps.citizenvet.models.recieved;

import com.angrychimps.citizenvet.models.base.Company;
import com.angrychimps.citizenvet.models.base.Member;

import java.util.List;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class RecAuthAPIlogin {
    abstract PayloadLogin payload();

    public Member member(){
        return payload().member();
    }

    public List<Company> companies(){
        return payload().companies();
    }

    @AutoParcelGson
    static abstract class PayloadLogin{
        abstract Member member();
        abstract List<Company> companies();
    }
}