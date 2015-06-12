package com.angrychimps.appname.models;

public class CompanyDetails {
    private String companyName;
    private String companyTagline;
    private String companyDescription;
    private Address address;
    private String distance;
    private String markerColor;
    private float rating;
    private int ratingCount;

    public CompanyDetails() {
    }

    public CompanyDetails(CAdDetail result, String distance, String markerColor){
        companyName = result.getCompany().getName();
        companyTagline = result.getTitle();
        companyDescription = result.getDescription();
        address = result.getAddress();
        this.distance = distance;
        this.markerColor = markerColor;
        rating = result.getRating();
        ratingCount = result.getRating_count();
    }

    public CompanyDetails(String companyName, String companyTagline, String companyDescription, Address address, String distance, String markerColor, float rating, int ratingCount) {
        this.companyName = companyName;
        this.companyTagline = companyTagline;
        this.companyDescription = companyDescription;
        this.address = address;
        this.distance = distance;
        this.markerColor = markerColor;
        this.rating = rating;
        this.ratingCount = ratingCount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyTagline() {
        return companyTagline;
    }

    public void setCompanyTagline(String companyTagline) {
        this.companyTagline = companyTagline;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getMarkerColor() {
        return markerColor;
    }

    public void setMarkerColor(String markerColor) {
        this.markerColor = markerColor;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
