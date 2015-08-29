package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.recieved.RecMessageAPI;
import com.angrychimps.citizenvet.models.send.SendMessageAPI;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

public interface MessageAPI {
    @GET("/message/{id}") Observable<RecMessageAPI> getMessage(@Path("id") String messageId);

    @POST("/message") Observable<Response> postMessage(@Body SendMessageAPI message);

    @DELETE("/message/{id}") Observable<Response> deleteMessage(@Path("id") String messageId);

    @PATCH("/message") Observable<Response> patchMessage(@Body SendMessageAPI message);
}
