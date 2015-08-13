package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.receive.ReviewDetail;
import com.angrychimps.citizenvet.models.send.ReviewPost;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface ReviewAPI {
    @GET("/review") void get(Callback<List<ReviewDetail>> callback);

    @GET("/review{id}") void get(@Path("id") String reviewId, Callback<ReviewDetail> callback);

    @POST("/review") void post(@Body ReviewPost review, Callback callback);
}
