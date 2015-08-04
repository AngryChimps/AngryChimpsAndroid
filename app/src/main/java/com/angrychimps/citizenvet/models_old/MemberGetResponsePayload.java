package com.angrychimps.citizenvet.models_old;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@SuppressWarnings("WeakerAccess") @JsonObject
public class MemberGetResponsePayload {

    @JsonField
    Member member;

    public MemberGetResponsePayload() {
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}