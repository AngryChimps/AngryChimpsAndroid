package com.angrychimps.appname.models;


import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class Member {

    @JsonField
    String id;

    @JsonField
    String name;

    @JsonField
    String photo;

    @JsonField
    String email;

    @JsonField
    String company_ids;

    public Member() {
    }


}
