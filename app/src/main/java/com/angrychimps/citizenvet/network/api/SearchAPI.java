package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.receive.SearchResults;
import com.angrychimps.citizenvet.models.send.SearchRequest;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface SearchAPI {
    @POST("/search") void post(@Body SearchRequest request, Callback<SearchResults> callback);
}
