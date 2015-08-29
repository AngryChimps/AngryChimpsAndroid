package com.angrychimps.citizenvet.models.send;

import com.angrychimps.citizenvet.models.base.Location;
import com.angrychimps.citizenvet.models.base.Member;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class SendMemberAPI {
    abstract Member payload();

    SendMemberAPI(){
    }

    public static SendMemberAPI create(Member member){
        return new AutoParcelGson_SendMemberAPI(member);
    }
}
