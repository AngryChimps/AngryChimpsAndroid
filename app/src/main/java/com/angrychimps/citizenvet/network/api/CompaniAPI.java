package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.shared.Company;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;

public interface CompaniAPI {
    @GET("/company/{id}") void get(@Path("id") String companyId, Callback<Company> callback);

    @POST("/company") void post(@Body Company company, Callback<Company> callback);

    @PATCH("/company/{id}") void patch(@Path("id") String companyId, @Body Company company, Callback callback);
}
