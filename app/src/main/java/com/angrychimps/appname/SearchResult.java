package com.angrychimps.appname;

import java.util.ArrayList;

class SearchResult {

    private String providerAdImmutableId, providerAdId, companyName, title, photo;

    private Address address;
    private ArrayList<Availability> availList;


    public SearchResult(String providerAdImmutableId, String providerAdId, String companyName, String title, String photo, Address address, ArrayList<Availability> availList) {
        this.providerAdImmutableId = providerAdImmutableId;
        this.providerAdId = providerAdId;
        this.companyName = companyName;
        this.title = title;
        this.photo = photo;
        this.address = address;
        this.availList = availList;
    }

    public String getProviderAdImmutableId() {
        return providerAdImmutableId;
    }

    public void setProviderAdImmutableId(String providerAdImmutableId) {
        this.providerAdImmutableId = providerAdImmutableId;
    }

    public String getProviderAdId() {
        return providerAdId;
    }

    public void setProviderAdId(String providerAdId) {
        this.providerAdId = providerAdId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<Availability> getAvailList() {
        return availList;
    }

    public void setAvailList(ArrayList<Availability> availList) {
        this.availList = availList;
    }
}
