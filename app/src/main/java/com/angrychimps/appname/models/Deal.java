package com.angrychimps.appname.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class Deal {

    @JsonField
    private String provider_ad_immutable_id;

    @JsonField
    private String provider_ad_id;

    @JsonField
    private String title;

    @JsonField
    private String photo;

    @JsonField
    private String rating_count;

    @JsonField
    private int discounted_price;

    @JsonField
    private int discount_percentage;

    @JsonField
    private double distance;

    @JsonField
    private double lat;

    @JsonField(name = "long")
    private double lon;

    @JsonField
    private float rating;

    private int discounted_price_decimal;


    public Deal() {
    }

    public String getProvider_ad_immutable_id() {
        return provider_ad_immutable_id;
    }

    public void setProvider_ad_immutable_id(String provider_ad_immutable_id) {
        this.provider_ad_immutable_id = provider_ad_immutable_id;
    }

    public String getProvider_ad_id() {
        return provider_ad_id;
    }

    public void setProvider_ad_id(String provider_ad_id) {
        this.provider_ad_id = provider_ad_id;
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

    public String getRating_count() {
        return rating_count;
    }

    public void setRating_count(String rating_count) {
        this.rating_count = rating_count;
    }

    public int getDiscounted_price() {
        return discounted_price;
    }

    public void setDiscounted_price(double discounted_price) {

        double x = discounted_price - Math.floor(discounted_price);
        discounted_price_decimal = (int) Math.round(x*100);
        this.discounted_price = (int) discounted_price;
    }

    public int getDiscounted_price_decimal() {
        return discounted_price_decimal;
    }

    public int getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(double discount_percentage) {
        this.discount_percentage = (int) discount_percentage;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDistanceMiles(){
        return String.format("%.1f miles", distance);
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

}
