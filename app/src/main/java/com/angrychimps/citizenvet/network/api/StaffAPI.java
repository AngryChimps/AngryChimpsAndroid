package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.shared.StaffMember;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;

public interface StaffAPI {
    @GET("/staff") void get(Callback<List<StaffMember>> callback);

    @GET("/staff/{id}") void get(@Path("id") String staffId, Callback<StaffMember> callback);

    @POST("/staff") void post(@Body StaffMember staff, Callback<StaffMember> callback);

    @PATCH("/staff/{id}") void patch(@Path("id") String staffId, @Body StaffMember staff, Callback<Response> callback);
}
