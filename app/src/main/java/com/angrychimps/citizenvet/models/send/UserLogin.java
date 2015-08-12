package com.angrychimps.citizenvet.models.send;

import com.google.gson.annotations.SerializedName;

/*
    Used in Auth API. Supply either email+password, OR facebookId+facebookAuthToken, not both.
 */
public class UserLogin {
    private String email;
    private String password;
    @SerializedName("fb_id") private String facebookId;
    @SerializedName("fb_auth_token") private String facebookAuthToken;

    public UserLogin() {
    }

    public void email(String email, String password){
        this.email = email;
        this.password = password;
    }

    public void facebook(String facebookId, String facebookAuthToken){
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

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getFacebookAuthToken() {
        return facebookAuthToken;
    }

    public void setFacebookAuthToken(String facebookAuthToken) {
        this.facebookAuthToken = facebookAuthToken;
    }
}
