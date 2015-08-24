package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.received.SearchResults;
import com.angrychimps.citizenvet.models.send.SearchRequest;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

public interface SearchAPI {
    @POST("/search") Observable<SearchResults> postSearch(@Body SearchRequest request);
}
