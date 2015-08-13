package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.received.Session;
import com.angrychimps.citizenvet.models.send.SessionRequest;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface SessionAPI {
    @POST("/session") void post(@Body SessionRequest session, Callback<Session> callback);
}
