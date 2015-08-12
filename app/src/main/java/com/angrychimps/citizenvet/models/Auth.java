package com.angrychimps.citizenvet.models;

import com.google.gson.annotations.SerializedName;

public class Auth {
    private String email;
    private String password;
    @SerializedName("fb_id") private int facebookId;
    @SerializedName("fb_auth_token") private String facebookAuthToken;

    public Auth() {
    }

    public Auth(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Auth(int facebookId, String facebookAuthToken) {
        this.facebookId = facebookId;
        this.facebookAuthToken = facebookAuthToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(int facebookId) {
        this.facebookId = facebookId;
    }

    public String getFacebookAuthToken() {
        return facebookAuthToken;
    }

    public void setFacebookAuthToken(String facebookAuthToken) {
        this.facebookAuthToken = facebookAuthToken;
    }
}
