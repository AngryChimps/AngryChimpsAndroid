package com.angrychimps.citizenvet.models.received;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
    Used in Search API
 */
public class SearchResults {
    private int count;
    private List<SearchResult> results;

    public SearchResults() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<SearchResult> getResults() {
        return results;
    }

    public void setResults(List<SearchResult> results) {
        this.results = results;
    }

    public static class SearchResult{
        @SerializedName("location_id") private String locationId;
        @SerializedName("company_name") private String companyName;
        private String photo;
        private float rating;
        private double distance;
        private int lat;
        private int lon;

        public SearchResult() {
        }

        public String getLocationId() {
            return locationId;
        }

        public void setLocationId(String locationId) {
            this.locationId = locationId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public float getRating() {
            return rating;
        }

        public void setRating(float rating) {
            this.rating = rating;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public int getLat() {
            return lat;
        }

        public void setLat(int lat) {
            this.lat = lat;
        }

        public int getLon() {
            return lon;
        }

        public void setLon(int lon) {
            this.lon = lon;
        }
    }
}
