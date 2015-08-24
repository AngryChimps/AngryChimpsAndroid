package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.send.Inquiry;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

public interface InquiryAPI {
    @POST("/inquiry") Observable<Response> postInquiry(@Body Inquiry inquiry);
}
