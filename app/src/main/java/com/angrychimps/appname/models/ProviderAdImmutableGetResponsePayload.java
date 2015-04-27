package com.angrychimps.appname.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.ArrayList;

@JsonObject
public class ProviderAdImmutableGetResponsePayload {

    @JsonField
    Provider_ad_immutable provider_ad_immutable;

    @JsonField
    Provider_ad provider_ad;

    @JsonField
    Company company;
    @JsonField
    Address address;
    @JsonField
    ArrayList<Service> services;
    @JsonField
    ArrayList<String> photos;
    @JsonField
    private String title;
    @JsonField
    private String description;
    @JsonField
    private float rating;

    @JsonField
    private int rating_count;

    public ProviderAdImmutableGetResponsePayload() {
    }

    public Provider_ad_immutable getProvider_ad_immutable() {
        return provider_ad_immutable;
    }

    public void setProvider_ad_immutable(Provider_ad_immutable provider_ad_immutable) {
        this.provider_ad_immutable = provider_ad_immutable;
    }

    public Provider_ad getProvider_ad() {
        return provider_ad;
    }

    public void setProvider_ad(Provider_ad provider_ad) {
        this.provider_ad = provider_ad;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getRating_count() {
        return rating_count;
    }

    public void setRating_count(int rating_count) {
        this.rating_count = rating_count;
    }
}
