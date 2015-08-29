package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.recieved.RecMemberAPI;
import com.angrychimps.citizenvet.models.send.SendMemberAPI;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface MemberAPI {
    @GET("/member/{id}") Observable<RecMemberAPI> getMember(@Path("id") String user, @Query("userId") String userId);

    @POST("/member") Observable<RecMemberAPI> postMember(@Body SendMemberAPI member);

    @PATCH("/member/{id}") Observable<Response> patchMember(@Path("id") String user, @Query("userId") String userId, @Body SendMemberAPI member);
}
