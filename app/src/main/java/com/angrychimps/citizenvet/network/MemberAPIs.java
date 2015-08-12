package com.angrychimps.citizenvet.network;

import com.angrychimps.citizenvet.models.Members;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface MemberAPIs {
    @GET("/member/{id}") void get(@Path("id") String user, @Query("userId") String userId, Callback<Members> callback);

    @POST("/member") void post(@Body Members member, Callback<Members> callback);

    @PATCH("/member/{id}") void patch(@Path("id") String user, @Query("userId") String userId, @Body Members member);
}
