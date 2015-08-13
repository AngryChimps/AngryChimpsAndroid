package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.receive.CompanyLocationDetail;
import com.angrychimps.citizenvet.models.shared.Member;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface LocationAPI {
    @GET("/location/{id}") void get(@Path("id") String locationId, Callback<CompanyLocationDetail> callback);

    @POST("/location") void post(@Body Member member, Callback<Member> callback);
}
