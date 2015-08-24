package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.received.Messages;
import com.angrychimps.citizenvet.models.send.MessagesRequest;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

public interface MessagesAPI {
    @POST("/messages") Observable<Messages> postMessages(@Body MessagesRequest messagesRequest);
}
