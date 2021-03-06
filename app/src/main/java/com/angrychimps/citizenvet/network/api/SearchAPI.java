package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.recieved.RecSearchAPI;
import com.angrychimps.citizenvet.models.send.SendSearchAPI;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

public interface SearchAPI {
    @POST("/search") Observable<RecSearchAPI> postSearch(@Body SendSearchAPI request);
}
