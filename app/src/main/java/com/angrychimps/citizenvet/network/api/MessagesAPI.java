package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.recieved.RecMessagesAPI;
import com.angrychimps.citizenvet.models.send.SendMessagesAPI;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

public interface MessagesAPI {
    @POST("/messages") Observable<RecMessagesAPI> postMessages(@Body SendMessagesAPI messagesRequest);
}
