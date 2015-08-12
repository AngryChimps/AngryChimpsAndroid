package com.angrychimps.citizenvet.network;

import com.angrychimps.citizenvet.models.Sessions;

import retrofit.http.GET;

public interface SessionAPIs {
    @GET("/session") Sessions getSessionId();
}
