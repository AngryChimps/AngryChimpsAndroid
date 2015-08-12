package com.angrychimps.citizenvet.models;

import com.google.gson.annotations.SerializedName;

public class Password {
    private String email;
    private String password;
    @SerializedName("reset_code") private int resetCode;

    public Password() {
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

    public int getResetCode() {
        return resetCode;
    }

    public void setResetCode(int resetCode) {
        this.resetCode = resetCode;
    }
}
