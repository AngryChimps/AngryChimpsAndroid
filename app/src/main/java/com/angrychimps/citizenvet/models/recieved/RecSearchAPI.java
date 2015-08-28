package com.angrychimps.citizenvet.models.recieved;

import com.angrychimps.citizenvet.models.base.SearchResult;

import java.util.List;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

@AutoParcelGson
public abstract class RecSearchAPI {
    abstract PayloadSearch payload();

    public int count(){
        return payload().count();
    }

    public List<SearchResult> searchResults(){
        return payload().searchResults();
    }

    @AutoParcelGson
    static abstract class PayloadSearch{
        abstract int count();
        @SerializedName("search_results") abstract List<SearchResult> searchResults();
    }
}
