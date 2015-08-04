package com.angrychimps.citizenvet.models;

import org.json.JSONObject;

public class MemberAPI {

    public Member getOutput(JSONObject object){
        return new Member(object);
    }

    public JSONObject postMember(Member member){
        return member.getJSONObject();
    }

    public JSONObject patchMember(Member member){
        return member.getJSONObject();
    }
}
