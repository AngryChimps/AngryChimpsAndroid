package com.angrychimps.citizenvet.network.api;

import com.angrychimps.citizenvet.models.shared.Company;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

public interface CompanyAPI {
    @GET("/company/{id}") Observable<Company> getCompany(@Path("id") String companyId);

    @POST("/company") Observable<Company> postCompany(@Body Company company);

    @PATCH("/company/{id}") Observable<Response> patchCompany(@Path("id") String companyId, @Body Company company);
}
