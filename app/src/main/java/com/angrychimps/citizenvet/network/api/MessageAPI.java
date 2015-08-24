package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.received.MessageDetail;
import com.angrychimps.citizenvet.models.send.MessageSend;
import com.angrychimps.citizenvet.models.send.MessageStatus;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

public interface MessageAPI {
    @GET("/message/{id}") Observable<MessageDetail> getMessage(@Path("id") String messageId);

    @POST("/message") Observable<Response> postMessage(@Body MessageSend message);

    @DELETE("/message/{id}") Observable<Response> deleteMessage(@Path("id") String messageId);

    @PATCH("/message") Observable<Response> patchMessage(@Body MessageStatus message);
}
