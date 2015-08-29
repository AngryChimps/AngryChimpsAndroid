package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.recieved.RecSessionAPI;
import com.angrychimps.citizenvet.models.send.SendSessionAPI;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

public interface SessionAPI {
    @POST("/session") Observable<RecSessionAPI> postSession(@Body SendSessionAPI device);
}
