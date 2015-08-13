package com.angrychimps.citizenvet.models.shared;

import com.angrychimps.citizenvet.models.receive.Messages;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
    Used in Auth API, received as part of login response
    Used in Company API, for both send and receive
 */
public class Company {
    private String id; //receive only
    private String name;
    private String role;
    private String email;
    private String website;
    private Messages messages;
    @SerializedName("billing_address") private Address address;
    private List<CompanyLocation> locations;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    public List<CompanyLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<CompanyLocation> locations) {
        this.locations = locations;
    }
}
