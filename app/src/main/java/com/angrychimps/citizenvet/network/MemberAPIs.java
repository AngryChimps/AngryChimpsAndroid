package com.angrychimps.citizenvet.network;

import com.angrychimps.citizenvet.models.Members;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface MemberAPIs {
    @GET("/member/{id}") Members getMember(@Path("id") String user, @Query("userId") String userId);

    @POST("/member") Members postMember(@Body Members member);

    @PATCH("/member/{id}") void patchMember(@Path("id") String user, @Query("userId") String userId, @Body Members member);
}
