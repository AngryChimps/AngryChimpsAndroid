package com.angrychimps.citizenvet.models.shared;

/*
    Used in Auth API
    send: start: provide only email  finish: provide all
    receive resetCode after start
 */
public class UserLoginReset {
    private String email;
    private String password;
    private String resetCode;

    public UserLoginReset() {
    }

    public UserLoginReset(String email) {
        this.email = email;
    }

    public UserLoginReset(String email, String password, String resetCode) {
        this.email = email;
        this.password = password;
        this.resetCode = resetCode;
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

    public String getResetCode() {
        return resetCode;
    }

    public void setResetCode(String resetCode) {
        this.resetCode = resetCode;
    }
}
