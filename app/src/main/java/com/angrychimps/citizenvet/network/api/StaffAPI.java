package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.recieved.RecStaffAPIget;
import com.angrychimps.citizenvet.models.recieved.RecStaffAPIgetAll;
import com.angrychimps.citizenvet.models.send.SendStaffAPI;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

public interface StaffAPI {
    @GET("/staff") Observable<RecStaffAPIgetAll> getStaffMembers();

    @GET("/staff/{id}") Observable<RecStaffAPIget> getStaffMember(@Path("id") String staffId);

    @POST("/staff") Observable<RecStaffAPIget> postStaffMember(@Body SendStaffAPI staff);

    @PATCH("/staff/{id}") Observable<Response> patchStaffMember(@Path("id") String staffId, @Body SendStaffAPI staff);
}
