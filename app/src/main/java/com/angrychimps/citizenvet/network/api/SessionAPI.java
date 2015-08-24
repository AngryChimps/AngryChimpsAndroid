package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.received.Session;
import com.angrychimps.citizenvet.models.send.SessionRequest;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

public interface SessionAPI {
    @POST("/session") Observable<Session> postSession(@Body SessionRequest session);
}
