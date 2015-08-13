package com.angrychimps.citizenvet.models.shared;

/*
    Used in Company API, inside Company
    Used in Location API, inside CompanyLocation
 */
public class Address {
    private String street1;
    private String street2;
    private String city;
    private String state;
    private int zip;
    private String phone;
    private Location location;

    public Address() {
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public static class Location{
        private float lat;
        private float lon;

        public Location() {
        }

        public Location(float lat, float lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public float getLat() {
            return lat;
        }

        public void setLat(float lat) {
            this.lat = lat;
        }

        public float getLon() {
            return lon;
        }

        public void setLon(float lon) {
            this.lon = lon;
        }
    }
}