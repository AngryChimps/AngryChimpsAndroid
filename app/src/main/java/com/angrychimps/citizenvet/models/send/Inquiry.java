package com.angrychimps.citizenvet.models.send;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/*
    This is NOT ready on the server side. Expect changes.
 */
public class Inquiry {
    private String locationId;
    private String first;
    private String last;
    private String phone;
    private String bestTime;
    private String email;
    private String password;
    @SerializedName("fb_id") private String facebookId;
    @SerializedName("fb_auth_token") private String facebookAuthToken;
    private String petName;
    private String preferredProviderStaffId;
    private String description;
    private Date firstTime;
    private Date secondTime;
    private Date thirdTime;
    private boolean newPatient;

    public Inquiry() {
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBestTime() {
        return bestTime;
    }

    public void setBestTime(String bestTime) {
        this.bestTime = bestTime;
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

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPreferredProviderStaffId() {
        return preferredProviderStaffId;
    }

    public void setPreferredProviderStaffId(String preferredProviderStaffId) {
        this.preferredProviderStaffId = preferredProviderStaffId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }

    public Date getSecondTime() {
        return secondTime;
    }

    public void setSecondTime(Date secondTime) {
        this.secondTime = secondTime;
    }

    public Date getThirdTime() {
        return thirdTime;
    }

    public void setThirdTime(Date thirdTime) {
        this.thirdTime = thirdTime;
    }

    public boolean isNewPatient() {
        return newPatient;
    }

    public void setNewPatient(boolean newPatient) {
        this.newPatient = newPatient;
    }
}
