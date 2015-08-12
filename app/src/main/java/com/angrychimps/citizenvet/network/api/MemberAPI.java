package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.Member;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface MemberAPI {
    @GET("/member/{id}") void get(@Path("id") String user, @Query("userId") String userId, Callback<Member> callback);

    @POST("/member") void post(@Body Member member, Callback<Member> callback);

    @PATCH("/member/{id}") void patch(@Path("id") String user, @Query("userId") String userId, @Body Member member);
}
