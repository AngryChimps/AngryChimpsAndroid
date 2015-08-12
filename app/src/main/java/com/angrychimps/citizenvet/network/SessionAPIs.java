package com.angrychimps.citizenvet.network;

import com.angrychimps.citizenvet.models.Sessions;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface SessionAPIs {
    @POST("/session") void post(@Body Sessions session, Callback<Sessions> callback);
}
