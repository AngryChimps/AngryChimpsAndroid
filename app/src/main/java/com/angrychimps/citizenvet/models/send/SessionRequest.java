package com.angrychimps.citizenvet.models.send;

public class SessionRequest {
    private int deviceType = 3;
    private String pushToken;
    private String description;

    public SessionRequest() {
    }

    public SessionRequest(String pushToken, String description) {
        this.pushToken = pushToken;
        this.description = description;
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
}
