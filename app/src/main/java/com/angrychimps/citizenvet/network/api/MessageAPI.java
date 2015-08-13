package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.receive.MessageDetail;
import com.angrychimps.citizenvet.models.send.MessageSend;
import com.angrychimps.citizenvet.models.send.MessageStatus;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;

public interface MessageAPI {
    @GET("/message/{id}") void get(@Path("id") String messageId, Callback<MessageDetail> callback);

    @POST("/message") void post(@Body MessageSend message, Callback<Response> callback);

    @DELETE("/message/{id}") void delete(@Path("id") String messageId, Callback<Response> callback);

    @PATCH("/message") void patch(@Body MessageStatus message, Callback<Response> callback);
}
