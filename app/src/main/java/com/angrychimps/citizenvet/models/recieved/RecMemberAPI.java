package com.angrychimps.citizenvet.models.recieved;

import com.angrychimps.citizenvet.models.base.Member;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class RecMemberAPI {
    abstract PayloadMember payload();

    public Member member(){
        return payload().member();
    }

    @AutoParcelGson
    static abstract class PayloadMember{
        abstract Member member();
    }
}
