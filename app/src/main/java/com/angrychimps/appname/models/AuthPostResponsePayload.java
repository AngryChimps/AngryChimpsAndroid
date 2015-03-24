package com.angrychimps.appname.models;


import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class AuthPostResponsePayload {

    @JsonField
    Member member;

    public AuthPostResponsePayload() {
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
