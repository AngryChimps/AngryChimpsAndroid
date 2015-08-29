package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.recieved.RecReviewAPIget;
import com.angrychimps.citizenvet.models.recieved.RecReviewAPIgetAll;
import com.angrychimps.citizenvet.models.send.SendReviewAPI;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

public interface ReviewAPI {
    @GET("/review") Observable<RecReviewAPIgetAll> getReviews();

    @GET("/review{id}") Observable<RecReviewAPIget> getReview(@Path("id") String reviewId);

    @POST("/review") Observable<Response> postReview(@Body SendReviewAPI review);
}
