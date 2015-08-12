package com.angrychimps.citizenvet.models.shared;

import com.google.gson.annotations.SerializedName;

/*
    Used in Company API, for both send and receive
 */
public class Company {
    private String id; //receive only
    private String name;
    private String email;
    private String website;
    @SerializedName("billing_address") private Address address;
    private int plan; //Received in GET and sent with PATCH only, plan levels- 1: Basic, 2: Premium

    public Company() {
    }

    //Send with POST
    public Company(String name, String email, String website, Address address) {
        this(name, email, website, address, 0);
    }

    //Send with PATCH
    public Company(String name, String email, String website, Address address, int plan) {
        this.name = name;
        this.email = email;
        this.website = website;
        this.address = address;
        this.plan = plan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }
}
