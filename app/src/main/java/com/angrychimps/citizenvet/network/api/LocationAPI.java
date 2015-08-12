package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.receive.CompanyLocationDetail;
import com.angrychimps.citizenvet.models.Member;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface LocationAPI {
    @GET("/location/{id}") void get(@Path("id") String locationId, @Query("userId") String userId, Callback<CompanyLocationDetail> callback);

    @POST("/location") void post(@Body Member member, Callback<Member> callback);
}
