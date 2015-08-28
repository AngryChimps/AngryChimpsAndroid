package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.received_deprecated.Messages;
import com.angrychimps.citizenvet.models.recieved.RecMessagesAPI;
import com.angrychimps.citizenvet.models.send_deprecated.MessagesRequest;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

public interface MessagesAPI {
    @POST("/messages") Observable<RecMessagesAPI> postMessages(@Body MessagesRequest messagesRequest);
}
