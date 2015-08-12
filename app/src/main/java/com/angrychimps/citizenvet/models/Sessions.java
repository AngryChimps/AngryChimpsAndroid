package com.angrychimps.citizenvet.models;

import org.parceler.Parcel;

@Parcel
public class Sessions {
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
