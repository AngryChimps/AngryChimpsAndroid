package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.send.Inquiry;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface InquiryAPI {
    @POST("/inquiry") void post(@Body Inquiry inquiry, Callback callback);
}
