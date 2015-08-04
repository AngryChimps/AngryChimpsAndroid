package com.angrychimps.citizenvet.models_old;


import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class SessionGetResponsePayload {

    @JsonField
    private String session_id;

    public SessionGetResponsePayload() {
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
}
