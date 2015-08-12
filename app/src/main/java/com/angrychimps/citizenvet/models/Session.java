package com.angrychimps.citizenvet.models;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class Session {
    @SerializedName("device_type") private int deviceType;
    @SerializedName("push_token") private String pushToken;
    private String description;
    private JsonObject session;

    public Session() {
    }

    public JsonObject getSession() {
        return session;
    }

    public void setSession(JsonObject session) {
        this.session = session;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSessionId(){
        return session.get("id").toString();
    }
}
