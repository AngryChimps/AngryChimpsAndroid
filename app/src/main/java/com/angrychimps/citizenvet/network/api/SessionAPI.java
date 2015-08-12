package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.Session;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface SessionAPI {
    @POST("/session") void post(@Body Session session, Callback<Session> callback);
}
