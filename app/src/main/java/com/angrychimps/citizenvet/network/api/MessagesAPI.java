package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.receive.Messages;
import com.angrychimps.citizenvet.models.send.MessagesRequest;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface MessagesAPI {
    @POST("/messages") void post(@Body MessagesRequest messagesRequest, Callback<Messages> callback);
}
