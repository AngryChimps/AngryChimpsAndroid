package com.angrychimps.citizenvet.models.received;

import android.os.Parcelable;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/*
    Used in Search API
 */
@AutoParcelGson
public abstract class SearchResults implements Parcelable {
    public abstract int count();
    public abstract List<SearchResult> results();

    SearchResults() {
    }

    public static SearchResults create(int count, List<SearchResult> results){
        return new AutoParcelGson_SearchResults(count, results);
    }

    @AutoParcelGson
    public abstract static class SearchResult {
        @SerializedName("location_id") public abstract String locationId();
        @SerializedName("company_name") public abstract String companyName();
        public abstract String photo();
        public abstract float rating();
        public abstract double distance();
        public abstract int lat();
        public abstract int lon();

        SearchResult() {
        }

        public static SearchResult create(String locationId, String companyName, String photo, float rating, double distance, int lat, int lon){
            return new AutoParcelGson_SearchResults_SearchResult(locationId, companyName, photo, rating, distance, lat, lon);
        }
    }
}
