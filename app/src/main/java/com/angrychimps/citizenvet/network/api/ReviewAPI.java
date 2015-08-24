package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.received.ReviewDetail;
import com.angrychimps.citizenvet.models.send.ReviewPost;

import java.util.List;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

public interface ReviewAPI {
    @GET("/review") Observable<List<ReviewDetail>> getReviews();

    @GET("/review{id}") Observable<ReviewDetail> getReview(@Path("id") String reviewId);

    @POST("/review") Observable<Response> postReview(@Body ReviewPost review);
}
