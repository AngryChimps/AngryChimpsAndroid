package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.shared.StaffMember;

import java.util.List;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

public interface StaffAPI {
    @GET("/staff") Observable<List<StaffMember>> getStaffMembers();

    @GET("/staff/{id}") Observable<StaffMember> getStaffMember(@Path("id") String staffId);

    @POST("/staff") Observable<StaffMember> postStaffMember(@Body StaffMember staff);

    @PATCH("/staff/{id}") Observable<Response> patchStaffMember(@Path("id") String staffId, @Body StaffMember staff);
}
